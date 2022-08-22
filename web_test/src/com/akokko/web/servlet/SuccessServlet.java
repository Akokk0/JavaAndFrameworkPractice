package com.akokko.web.servlet;

import com.akokko.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置页面编码字符集
        resp.setContentType("text/html;charset=utf-8");

        //在页面上输出登录成功
        User user = (User) req.getAttribute("user");
        resp.getWriter().write("欢迎回来，" + user.getUsername() + "!");
    }
}
