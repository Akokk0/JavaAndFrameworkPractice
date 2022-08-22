package com.akokko.service.impl;

import com.akokko.dao.RoleDao;
import com.akokko.dao.UserDao;
import com.akokko.domain.Role;
import com.akokko.domain.User;
import com.akokko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            Long uid = user.getId();
            List<Role> roles = roleDao.findAllByUid(uid);
            user.setRoles(roles);
        }
        return users;
    }

    @Override
    public void save(User user, Long[] roleId) {
        Long userId = userDao.save(user);
        userDao.saveUserIdRoleIdReletion(userId, roleId);
    }

    @Override
    public void del(Long userId) {
        userDao.delUserRole(userId);
        userDao.delUser(userId);
    }

    @Override
    public User login(String username, String password) {
        try {
            return userDao.login(username, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
