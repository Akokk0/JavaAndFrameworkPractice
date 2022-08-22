package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/baseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取访问路径
        String requestURI = req.getRequestURI();
        //获取请求方法
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        //获取方法对象
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将对象序列化为Json
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public void returnJson(Object obj ,HttpServletResponse response) throws IOException {
        //创建序列化对象
        ObjectMapper mapper = new ObjectMapper();
        //将对象序列化
        String json = mapper.writeValueAsString(obj);
        //设置页面编码
        response.setContentType("application/json;charset=utf-8");
        //返回json
        response.getWriter().write(json);
    }
}
