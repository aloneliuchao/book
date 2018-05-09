package com.dbu.book.servcie.impl;

import com.dbu.book.dao.UserDao;
import com.dbu.book.model.User;
import com.dbu.book.servcie.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User add(User user) {
        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return userDao.saveAndFlush(user);
    }

    @Override
    public void delect(User user) {
        userDao.delete(user);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAll();
    }

    @Override
    public boolean findUserByUserName(User user) {
        User u = userDao.findUserByUsername(user.getUsername());
        if(u != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean findUserByUsernameAndPassword(User user) {
        User u = userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
        if(u != null){
            return true;
        }
        return false;
    }

    @Override
    public User getUser(User user) {
        return userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public User getUserById(String userId) {
        return userDao.findUserById(Integer.parseInt(userId));
    }

    @Override
    public Page<User> findUser(Pageable pageable) {
        return userDao.findAll(pageable);
    }
}
