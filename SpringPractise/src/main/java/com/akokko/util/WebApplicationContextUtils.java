package com.akokko.util;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequest;

public class WebApplicationContextUtils {
    public static ApplicationContext getApp(ServletRequest sr) {
        return (ApplicationContext) sr.getServletContext().getAttribute("app");
    }
}
