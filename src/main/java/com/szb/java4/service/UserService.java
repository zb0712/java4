package com.szb.java4.service;

import com.szb.java4.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 石致彬
 * @create 2021-02-17 15:23
 */
@Service
public interface UserService {
    public void registUser(User user);

    public User login(User user);

    public boolean existUsername(String name);

    public void updateUser(User user);

    public User queryUserByName(String name);

    public User queryByDiskId(Integer diskId);

}
