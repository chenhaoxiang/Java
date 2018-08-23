package com.uifuture.user.service.impl;

import com.uifuture.user.dao.UserMapper;
import com.uifuture.user.entity.User;
import com.uifuture.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2018/2/6.
 * Time: 下午 8:43.
 * Explain:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public Integer insertUser(User user) {
        return userMapper.insertSelective(user);
    }
}
