package com.akokko.dao;

import com.akokko.domain.User;
import com.akokko.utils.JdbcUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    JdbcTemplate template = new JdbcTemplate(JdbcUtil.getDataSource());

    public User login(User loginUser) {
        //获取登录用户的账号密码
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        //定义sql语句
        String sql = "select * from user where username = ? and password = ?";

        //使用template对数据库进行查询并返回User对象
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return user;
        }
    }
}
