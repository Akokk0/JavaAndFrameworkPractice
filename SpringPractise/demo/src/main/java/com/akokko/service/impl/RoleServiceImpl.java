package com.akokko.service.impl;

import com.akokko.dao.RoleDao;
import com.akokko.domain.Role;
import com.akokko.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao dao;

    @Override
    public List<Role> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Role role) {
        dao.save(role);
    }
}
