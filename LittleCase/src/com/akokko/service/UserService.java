package com.akokko.service;

import com.akokko.domain.PageBean;
import com.akokko.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();

    User checkLogin(User loginUser);

    void addUser(User addUser);

    void deleteUser(String userId);

    User findUser(String id);

    void updateUser(User updateUser);

    void deleteSelectedUsers(String[] ids);

    PageBean<User> findUserByPage(String currentPage, String rows);

    PageBean<User> findUserByConditionWithPage(String currentPage, String rows, Map<String, String[]> condition);
}
