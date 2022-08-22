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

@WebServlet("/registServlet")
public class RegistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页面填写的验证码
        String check = request.getParameter("check");
        //获取服务器生成的验证码
        String checkcode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");//删除之前保存的验证码，避免安全问题
        //进行对比是否一致
        if (checkcode == null || !checkcode.equalsIgnoreCase(check)) {
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");

            //用Jackson将结果信息对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);

            //将json返回给页面
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //验证码输入正确，进行下一步

        //获取页面提交的参数
        Map<String, String[]> map = request.getParameterMap();
        //将拿到的数据封装为User对象
        User registUser = new User();
        //使用BeanUtils将map集合数据封装为User对象
        try {
            BeanUtils.populate(registUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //创建Service调用regist方法
        UserService service = new UserServiceImpl();
        boolean flag = service.regist(registUser);

        //创建结果信息对象，并进行封装
        ResultInfo info = new ResultInfo();
        if (flag) {
            info.setFlag(flag);
        } else {
            info.setFlag(flag);
            info.setErrorMsg("注册失败！");
        }

        //用Jackson将结果信息对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json返回给页面
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }
}
