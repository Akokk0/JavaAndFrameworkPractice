package com.akokko.test;

import com.akokko.domain.User;
import com.akokko.utils.DruidUtil;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TemplateTest {
    private JdbcTemplate template = new JdbcTemplate(DruidUtil.getDataSource());

    @Test
    public void test1() {
        String sql = "update user set username = ? where id = 5";
        int count = template.update(sql,"hunter");
        System.out.println(count);
    }

    @Test
    public void test2() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test3() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return null;
            }
        });
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test4() {
        String sql = "select count(id) from user";
        Integer i = template.queryForObject(sql, Integer.class);
        System.out.println(i);
    }

    @Test
    public void test5() {
        String sql = "select * from user where id = 1";
        Map<String, Object> map = template.queryForMap(sql);
        System.out.println(map);
    }

    @Test
    public void test6() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = template.queryForList(sql);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }
}
