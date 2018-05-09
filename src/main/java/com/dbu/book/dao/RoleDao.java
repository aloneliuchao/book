package com.dbu.book.dao;

import com.dbu.book.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao extends JpaRepository<Role,Long> {

    @Query(value = "select * from role where role_name = ?1",nativeQuery = true)
    List<Role> findRoleByRoleName(String name);

}
