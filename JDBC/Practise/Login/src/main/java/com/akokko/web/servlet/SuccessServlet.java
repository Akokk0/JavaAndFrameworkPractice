package com.akokko.web.servlet;

import com.akokko.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "failServlet", value = "/failServlet")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置页面编码
        response.setContentType("text/html;charset=utf-8");

        //获取用户名
        User user = (User)request.getAttribute("user");
        String username = user.getUsername();

        //在页面写一句话
        response.getWriter().write("登录成功，" + username + "欢迎回来！");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}