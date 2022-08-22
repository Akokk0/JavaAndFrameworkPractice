package com.akokko;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTest {

    @Autowired
    private JdbcTemplate template;

    @Test
    public void test() {
        String sql = "insert into account values(?,?)";
        template.update(sql, "tom", 5000);
    }
}
