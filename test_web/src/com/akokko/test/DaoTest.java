package com.akokko.test;

import com.akokko.dao.UserDao;
import com.akokko.domain.User;
import org.junit.Test;

public class DaoTest {
    @Test
    public void testDao() {
        UserDao dao = new UserDao();
        User loginUser = new User();

        loginUser.setUsername("wawa");
        loginUser.setPassword("123");

        User user = dao.login(loginUser);

        if (user == null) {
            System.out.println("登录失败，账号或密码错误！");
        } else {
            System.out.println("登录成功！");
        }
    }
}
