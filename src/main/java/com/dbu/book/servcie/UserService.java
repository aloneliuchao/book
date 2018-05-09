package com.dbu.book.servcie;

import com.dbu.book.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户业务层
 */
public interface UserService {

    /**
     * 添加用户
     * @param user
     * @return
     */
    User add(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    User update(User user);

    /**
     * 删除用户
     * @param user
     */
    void delect(User user);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUser();

    boolean findUserByUserName(User user);

    boolean findUserByUsernameAndPassword(User user);

    User getUser(User user);

    User getUserById(String userId);

    Page<User> findUser(Pageable pageable);
}
