package com.akokko.userdao.impl;

import com.akokko.userdao.UserDao;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("save method running.....");
    }

}
