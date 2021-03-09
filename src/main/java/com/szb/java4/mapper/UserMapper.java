package com.szb.java4.mapper;

import com.szb.java4.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 石致彬
 * @create 2021-02-16 20:48
 */
@Mapper
public interface UserMapper {
    List<User> queryAll();
    User queryUserById(Integer id);
    User queryUserByName(String username);
    int insertUser(User user);
    User queryUserByNameAndPassword(String username,String password);
    int updateUser(User user);
    User queryByDiskId(Integer diskId);
}
