package cn.itcast.travel.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/exitServlet")
public class ExitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁Session
        request.getSession().invalidate();
        //跳转登陆页面
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
}
