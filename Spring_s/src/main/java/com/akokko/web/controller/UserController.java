package com.akokko.web.controller;

import com.akokko.config.SpringConfiguration;
import com.akokko.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UserController {
    public static void main(String[] args) throws SQLException {
        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserServiceImpl userService = (UserServiceImpl) app.getBean("userService");
        DataSource druidDataSource = (DataSource) app.getBean("druidDataSource");
        System.out.println(druidDataSource.getConnection());
        userService.save();
    }
}
