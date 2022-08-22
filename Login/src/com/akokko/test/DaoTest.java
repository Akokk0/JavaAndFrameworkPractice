package com.akokko.test;

import com.akokko.dao.UserDao;
import com.akokko.domain.User;
import org.junit.Test;

import java.util.ArrayList;

public class DaoTest {
    @Test
    public void test1() {
        UserDao dao = new UserDao();
        User loginUser = new User("wawa","123");
        User user = dao.checkUser(loginUser);
        System.out.println(user);
    }

    @Test
    public void test2() {
        ArrayList<String> arr = new ArrayList<>();
    }
}
