package com.akokko.service;

import com.akokko.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void save(User user, Long[] roleId);

    void del(Long userId);

    User login(String username, String password);
}
