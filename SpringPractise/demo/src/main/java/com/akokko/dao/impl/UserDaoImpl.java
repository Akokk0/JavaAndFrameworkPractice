package com.akokko.dao.impl;

import com.akokko.dao.UserDao;
import com.akokko.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<User> findAll() {
        String sql = "select * from sys_user";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Long save(User user) {
        String sql = "insert into sys_user values(?,?,?,?,?)";
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1, null);
                preparedStatement.setObject(2, user.getUsername());
                preparedStatement.setObject(3, user.getEmail());
                preparedStatement.setObject(4, user.getPassword());
                preparedStatement.setObject(5, user.getPhoneNum());
                return preparedStatement;
            }
        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(creator, keyHolder);
        long userId = keyHolder.getKey().longValue();
        return userId;
    }

    @Override
    public void saveUserIdRoleIdReletion(Long userId, Long[] roleId) {
        String sql = "insert into sys_user_role values(?,?)";
        for (Long aLong : roleId) {
            template.update(sql, userId, aLong);
        }
    }

    @Override
    public void delUserRole(Long userId) {
        String sql = "delete from sys_user_role where userId = ?";
        template.update(sql, userId);
    }

    @Override
    public void delUser(Long userId) {
        String sql = "delete from sys_user where id = ?";
        template.update(sql, userId);
    }

    @Override
    public User login(String username, String password) throws EmptyResultDataAccessException {
        String sql = "select * from sys_user where username = ? and password = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        return user;
    }
}
