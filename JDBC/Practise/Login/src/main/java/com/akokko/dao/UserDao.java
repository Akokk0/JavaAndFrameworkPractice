package com.akokko.dao;

import com.akokko.domain.User;
import com.akokko.utils.DruidUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

    public static User login(User loginUser) {
        try {
            JdbcTemplate template = new JdbcTemplate(DruidUtil.getDataSource());

            String sql = "select * from user where username = ? and password = ?";

            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());

            return user;
        } catch (DataAccessException e) {
            System.out.println(e);
            return null;
        }
    }
}
