package com.dbu.book.servcie;

import com.dbu.book.model.Role;

import java.util.List;

public interface RoleService {

    Role saveRole(Role role);

    Role updateRole(Role role);

    void delectRole(Role role);

    List<Role> findAllRole();

    List<Role> findRoleByName(String name);
}
