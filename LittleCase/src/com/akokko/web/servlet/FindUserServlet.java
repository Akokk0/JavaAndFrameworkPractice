package com.akokko.web.servlet;

import com.akokko.domain.User;
import com.akokko.service.UserService;
import com.akokko.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String id = req.getParameter("id");

        UserService service = new UserServiceImpl();
        User user = service.findUser(id);
        req.setAttribute("user",user);

        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }
}
