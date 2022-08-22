package com.akokko.dao;

import com.akokko.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    Long save(User user);

    void saveUserIdRoleIdReletion(Long userId, Long[] roleId);

    void delUserRole(Long userId);

    void delUser(Long userId);

    User login(String username, String password);
}
