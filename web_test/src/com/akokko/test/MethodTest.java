package com.akokko.test;

import com.akokko.dao.UserDao;
import com.akokko.domain.User;
import org.junit.Test;
public class MethodTest {

    @Test
    public void test1() {
        UserDao dao = new UserDao();
        User loginUser = new User();

        loginUser.setUsername("wawa");
        loginUser.setPassword("123");

        User user = dao.login(loginUser);
        System.out.println(user);
    }
}
