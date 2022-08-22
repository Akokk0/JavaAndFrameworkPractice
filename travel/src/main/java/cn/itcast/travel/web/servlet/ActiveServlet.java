package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/activeServlet")
public class ActiveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
