package com.akokko.dao;

import com.akokko.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User> findAll();

    User checkLogin(User loginUser);

    void addUser(User addUser);

    void deleteUser(int id);

    User findUser(int id);

    void updateUser(User updateUser);

    List<User> findUserByPage(int start, int rows);

    int findTotalCount();

    int findTotalCountByCondition(Map<String, String[]> condition);

    List<User> findUserByConditionWithPage(int start, int i_rows, Map<String, String[]> condition);
}
