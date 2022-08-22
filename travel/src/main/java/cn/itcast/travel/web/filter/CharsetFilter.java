package cn.itcast.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CharsetFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //将父接口转换为子接口
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //获取方法名称
        String method = req.getMethod();

        //判断是否为POST请求
        if (method.equalsIgnoreCase("POST")) request.setCharacterEncoding("utf-8");

        //设置相应消息体为text/html;charset=utf-8
        resp.setContentType("text/html;charset=utf-8");

        //放行
        chain.doFilter(req, resp);
    }
}
