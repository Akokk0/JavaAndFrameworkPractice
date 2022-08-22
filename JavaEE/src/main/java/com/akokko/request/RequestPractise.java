package com.akokko.request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/RequestPractise")
public class RequestPractise extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*System.out.println("doGet方法调用了!");
        String header = request.getHeader("user-agent");
        if (header.contains("Chrome")) {
            System.out.println("谷歌来了！！！");
        } else if (header.contains("Firefox")) {
            System.out.println("火狐来了！！！");
        }*/

        String contextPath = request.getContextPath();
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        System.out.println(contextPath);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
