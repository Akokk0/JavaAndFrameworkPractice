package com.akokko.web.servLet;

import com.akokko.dao.UserDao;
import com.akokko.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        User loginUser = new User();
        //获取从页面传来的数据
        Map<String, String[]> parameterMap = req.getParameterMap();
        String checkcode = req.getParameter("checkcode");

        //将获取的数据打包为一个User
        try {
            BeanUtils.populate(loginUser,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用UserDao中的check方法与数据库进行对比,并返回一个真实的User对象
        UserDao dao = new UserDao();
        User user = dao.checkUser(loginUser);

        //判断验证码是否正确
        HttpSession session = req.getSession();
        String checkcode_session = (String) session.getAttribute("checkcode_session");
        session.removeAttribute("checkcode_session");

        if (checkcode_session == null) {
            //说明验证码被删除了
            req.setAttribute("cc_nullerror","请刷新验证码");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);

        } else if (checkcode_session.equalsIgnoreCase(checkcode)) {

            //验证码输入正确，验证用户名密码是否正确
            if (user != null) {

                //输入正确，跳转到成功页面
                req.getSession().setAttribute("username", user.getUsername());
                resp.sendRedirect(req.getContextPath() + "/success.jsp");

            } else {

                //用户名或密码错误，输出用户名或密码输入错误
                req.setAttribute("user_error","用户名或密码不正确");
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }

        } else {

            //验证码错误，输出验证码输入错误
            req.setAttribute("cc_error","验证码输入错误");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }
}
