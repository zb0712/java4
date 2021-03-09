package com.szb.java4;

import com.szb.java4.bean.Disk;
import com.szb.java4.bean.MyFile;
import com.szb.java4.bean.User;
import com.szb.java4.controller.UserController;
import com.szb.java4.mapper.DiskMapper;
import com.szb.java4.mapper.MyFileMapper;
import com.szb.java4.mapper.UserMapper;
import com.szb.java4.service.DiskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class Java4ApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    UserMapper userMapper;
    @Autowired
    MyFileMapper myFileMapper;
    @Autowired
    DiskMapper diskMapper;

    @Test
    void contextLoads() {
        List<MyFile> myFiles = myFileMapper.queryNoJudgeFile();
        System.out.println(myFiles);
    }

}
