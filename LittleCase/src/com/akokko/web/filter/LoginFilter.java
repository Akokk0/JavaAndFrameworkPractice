package com.akokko.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        if (requestURI.contains("/login.jsp") || requestURI.contains("/loginServlet") || requestURI.contains("/checkCode") || requestURI.contains("/css/") || requestURI.contains("/fonts/") || requestURI.contains("/js/")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Object user = req.getSession().getAttribute("user");
            if (user != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                req.setAttribute("login_msg","您尚未登錄，請先進行登錄");
                req.getRequestDispatcher("/login.jsp").forward(req,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
