package com.akokko.web.servlet;

import com.akokko.domain.PageBean;
import com.akokko.domain.User;
import com.akokko.service.UserService;
import com.akokko.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByConditionWithPageServlet")
public class FindUserByConditionWithPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");

        if(currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }

        if(rows == null || "".equals(rows)) {
            rows = "10";
        }
        Map<String, String[]> condition = req.getParameterMap();



        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByConditionWithPage(currentPage,rows,condition);

        req.setAttribute("condition",condition);
        req.setAttribute("pb",pb);
        req.getRequestDispatcher("/listbycondition.jsp").forward(req,resp);
    }
}
