package com.akokko.web.servlet;

import com.akokko.dao.UserDao;
import com.akokko.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //把用户名密码封装成User对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        //使用UserDao里的Login方法
        User user = UserDao.login(loginUser);

        //判断user是否为空，空则转发到登录失败页面，成功则转发到成功页面
        if (user == null) {
            request.getRequestDispatcher("/failServlet").forward(request,response);
        } else {
            request.setAttribute("user",user);
            request.getRequestDispatcher("/successServlet").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
