package com.akokko.service.impl;

import com.akokko.dao.UserDao;
import com.akokko.service.UserService;

public class UserServiceImpl implements UserService {

    //从Spring容器中注入UserDao
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        userDao.save();
    }
}
