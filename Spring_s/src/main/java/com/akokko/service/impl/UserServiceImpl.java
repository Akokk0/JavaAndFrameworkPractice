package com.akokko.service.impl;

import com.akokko.service.UserService;
import com.akokko.userdao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public void save() {
        userDao.save();
    }
}
