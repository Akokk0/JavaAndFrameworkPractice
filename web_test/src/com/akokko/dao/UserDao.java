package com.akokko.dao;

import com.akokko.domain.User;
import com.akokko.utils.JdbcUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    //创建JDBCTemplate对象
    private JdbcTemplate template = new JdbcTemplate(JdbcUtil.getDataSource());

    public User login(User loginUser) {
        //获取loginUser的username和password
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        //定义sql语句
        String sql = "select * from user where username = ? and password = ?";

        //执行sql语句,并传入两个?的值
        User user = null;
        try {
            return user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
