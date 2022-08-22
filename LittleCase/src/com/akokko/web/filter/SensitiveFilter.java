package com.akokko.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class SensitiveFilter implements Filter {
    List<String> sensitiveWords = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String realPath = filterConfig.getServletContext().getRealPath("/WEB-INF/classes/sensitiveWords.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line;
            while ((line = br.readLine()) != null){
                sensitiveWords.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sensitiveWords);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest proxyReq = (HttpServletRequest) Proxy.newProxyInstance(servletRequest.getClass().getClassLoader(), servletRequest.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (method.getName().contains("getParameter") && !method.getName().contains("getParameterMap")) {
                    System.out.println(method.getName());
                    String value = (String) method.invoke(servletRequest, args);
                    if (value != null) {
                        for (String sensitiveWord : sensitiveWords) {
                            if (value.contains(sensitiveWord)) {
                                value = value.replaceAll(value,"**");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(servletRequest, args);
            }
        });

        filterChain.doFilter(proxyReq,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
