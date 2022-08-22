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

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    /**
     * 登陆方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * 注册方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * 查找用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取存在Session中的User
        User user = (User) request.getSession().getAttribute("user");

        //创建序列化对象
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        //设置页面编码
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 用户登出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁Session
        request.getSession().invalidate();
        //跳转登陆页面
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 用户激活
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        response.setContentType("text/html;charset=utf-8");

        //获取激活码
        String code = request.getParameter("code");

        //判断激活码是真实的
        if (code == null || code.length() == 0) {
            //激活码不正确，向页面输出一段话
            response.getWriter().write("您的链接不正确，请稍后重试或联系管理员");
            return;
        }

        //创建service对象，调用其active方法
        UserService service = new UserServiceImpl();
        boolean flag = service.active(code);

        //判断flag的真假
        if (flag) {
            //激活码正确，将用户跳转到成功页面
            response.sendRedirect(request.getContextPath() + "/active_ok.html");
        } else {
            response.getWriter().write("您的链接不正确，请稍后重试或联系管理员");
        }
    }
}
