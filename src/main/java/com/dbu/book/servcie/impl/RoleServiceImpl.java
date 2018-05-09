package com.dbu.book.servcie.impl;

import com.dbu.book.dao.RoleDao;
import com.dbu.book.model.Role;
import com.dbu.book.servcie.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role saveRole(Role role) {
        return roleDao.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleDao.saveAndFlush(role);
    }

    @Override
    public void delectRole(Role role) {
        roleDao.delete(role);
    }

    @Override
    public List<Role> findAllRole() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> findRoleByName(String name) {
        return roleDao.findRoleByRoleName(name);
    }
}
