package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置页面编码
        response.setContentType("application/json;charset=utf-8");
        //创建结果信息对象
        ResultInfo info = new ResultInfo();
        //获取验证码
        String check = request.getParameter("check");
        //获取服务器生成的验证码
        String checkCode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        //判断验证码是否一致
        if (checkCode == null || !checkCode.equalsIgnoreCase(check)){
            //设置登录失败信息
            info.setFlag(false);
            info.setErrorMsg("验证码错误，请刷新后重试");
            //创建对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //将json写到页面上
            response.getWriter().write(json);
            return;
        }
        //获取登录用户名密码,创建loginUser对象
        Map<String, String[]> map = request.getParameterMap();
        User loginUser = new User();
        //使用BeanUtils将Map封装为User对象
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //创建Service对象调用login方法,传入loginUser，返回真实的User对象
        User user = new UserServiceImpl().login(loginUser);
        //判断user对象是否存在，若不存在则用户名或密码错误
        if (user == null) {
            //设置登录失败信息
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误，请检查后重试");
        }
        //判断user对象是否激活
        if (user != null && !"Y".equalsIgnoreCase(user.getStatus())) {
            //对象未激活，设置登录失败信息
            info.setFlag(false);
            info.setErrorMsg("你的账户尚未激活，请激活后重试");
        }
        if (user != null && "Y".equalsIgnoreCase(user.getStatus())) {
            //以上条件均已满足，则登录成功
            info.setFlag(true);
            request.getSession().setAttribute("user",user);
        }
        //创建对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //将json写到页面上
        response.getWriter().write(json);
    }
}
