package com.akokko.web.servlet;

import com.akokko.dao.UserDao;
import com.akokko.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符编码集
        req.setCharacterEncoding("utf-8");

        //获取页面传来的用户名和密码并封装为loginUser对象
        Map<String, String[]> parameterMap = req.getParameterMap();
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用Dao中login方法判断用户名和密码是否存在
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        //判断user对象是否为空，若为空则转发到失败页面，若存在则转发到成功页面
        if (user == null) {
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        } else {
            req.setAttribute("user",user);
            req.getRequestDispatcher("successServlet").forward(req,resp);
        }
    }
}
