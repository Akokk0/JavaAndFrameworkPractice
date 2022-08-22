package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取存在Session中的User
        User user = (User) request.getSession().getAttribute("user");

        //创建序列化对象
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        //设置页面编码
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
