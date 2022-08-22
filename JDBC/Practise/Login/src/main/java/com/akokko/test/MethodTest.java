package com.akokko.test;

import com.akokko.domain.User;
import org.junit.Test;
import com.akokko.dao.UserDao;

public class MethodTest {

    @Test
    public void test1() {
        UserDao ud = new UserDao();
        User loginUser = new User(1,"hunter","788");
        User user = ud.login(loginUser);
        System.out.println(user);
    }
}
