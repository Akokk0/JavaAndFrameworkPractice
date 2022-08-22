package com.akokko;

import com.akokko.config.SpringConfiguration;
import com.akokko.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class test {

    @Resource(name = "druidDataSource")
    private DataSource dataSource;

    @Resource(name = "userService")
    private UserServiceImpl userService;

    @Test
    public void test1() throws SQLException {
        System.out.println(dataSource.getConnection());
        System.out.println(userService);
    }

    @Test
    public void test2() {

    }
}
