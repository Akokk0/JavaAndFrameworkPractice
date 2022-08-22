package com.akokko.dao.impl;

import com.akokko.dao.RoleDao;
import com.akokko.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Role> findAll() {
        String sql = "select * from sys_role";
        return template.query(sql, new BeanPropertyRowMapper<>(Role.class));
    }

    @Override
    public void save(Role role) {
        String sql = "insert into sys_role values(?,?,?)";
        template.update(sql, null, role.getRoleName(), role.getRoleDesc());
    }

    @Override
    public List<Role> findAllByUid(Long uid) {
        String sql = "select * from sys_user_role ur, sys_role r where ur.roleId = r.id and ur.userId = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Role.class), uid);
    }
}
