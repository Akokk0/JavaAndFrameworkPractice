package com.akokko.dao;

import com.akokko.domain.User;
import com.akokko.util.JdbcUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    //获取template对象
    JdbcTemplate template = new JdbcTemplate(JdbcUtil.getDataSource());

    public User checkUser(User loginUser) {
        //获取用户输入的用户名和密码
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        //定义sql语句查询数据库
        String sql = "select * from user where username = ? and password = ?";

        //使用template对象查询数据并返回User对象,如果能查询到返回User对象，如果不能查询到则返回Null;
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
