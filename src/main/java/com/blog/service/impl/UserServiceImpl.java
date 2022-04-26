package com.blog.service.impl;

import com.blog.mapper.UserMapper;
import com.blog.pojo.User;
import com.blog.service.UserService;
import com.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录检测
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User checkUser(String username, String password) {
        return userMapper.findUser(username, MD5Utils.code(password));
    }
}
