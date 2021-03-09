package com.szb.java4.service.impl;

import com.szb.java4.bean.User;
import com.szb.java4.mapper.UserMapper;
import com.szb.java4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 石致彬
 * @create 2021-02-17 15:20
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public void registUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User login(User user) {
        return userMapper.queryUserByNameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String name) {
        User user = userMapper.queryUserByName(name);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    public User queryByDiskId(Integer diskId) {
        return userMapper.queryByDiskId(diskId);
    }
}
