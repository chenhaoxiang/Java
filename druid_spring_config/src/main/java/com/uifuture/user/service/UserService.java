package com.uifuture.user.service;

import com.uifuture.user.entity.User;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2018/2/6.
 * Time: 下午 8:42.
 * Explain:
 */
public interface UserService {

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 插入用户
     * @param user
     * @return
     */
    Integer insertUser(User user);
}
