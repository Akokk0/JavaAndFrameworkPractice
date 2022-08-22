package com.akokko.test;

import com.akokko.dao.UserDao;
import com.akokko.dao.impl.UserDaoImpl;
import com.akokko.domain.User;
import com.akokko.service.UserService;
import com.akokko.service.impl.UserServiceImpl;
import org.junit.Test;

public class MethodTest {
    @Test
    public void test1() {
        UserDao dao = new UserDaoImpl();
        User login = new User();
        login.setUsername("wawa");
        login.setPassword("123");
        User user = dao.checkLogin(login);
        System.out.println(user);
    }

    @Test
    public void test2() {
        UserService service = new UserServiceImpl();
        User login = new User();
        login.setUsername("wawa");
        login.setPassword("123");
        User user = service.checkLogin(login);
        System.out.println(user);
    }
}
