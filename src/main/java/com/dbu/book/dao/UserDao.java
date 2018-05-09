package com.dbu.book.dao;

import com.dbu.book.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * user持久层处理
 */
public interface UserDao extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username,String password);

    User findUserById(int userId);


    Page<User> findAll(Pageable pageable);
}
