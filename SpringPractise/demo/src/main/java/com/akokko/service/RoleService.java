package com.akokko.service;

import com.akokko.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findAll();

    void save(Role role);
}
