package com.akokko.dao;

import com.akokko.domain.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAll();

    void save(Role role);

    List<Role> findAllByUid(Long uid);
}
