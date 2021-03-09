package com.szb.java4.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szb.java4.bean.Disk;
import com.szb.java4.bean.MyFile;
import com.szb.java4.bean.User;
import com.szb.java4.common.Result;
import com.szb.java4.service.DiskService;
import com.szb.java4.service.MyFileService;
import com.szb.java4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @author 石致彬
 * @create 2021-03-05 18:22
 */
@Controller
public class AdminController extends BaseController{

    @Autowired
    UserService userService;

    @Autowired
    MyFileService myFileService;

    @Autowired
    DiskService diskService;

    /**
     * 管理员登录
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/adminLogin")
    public Result adminLogin(User user) {
        User login = userService.login(user);
        if (login == null) {
            return Result.error("用户名或密码错误");
        }
        if (login.getIdentity() != 1) {
            return Result.error("不是管理员，登录失败");
        }
        session.setAttribute("loginUser",login);
        HashMap<String,Object> map = new HashMap<>();
        map.put("loginUser",login);
        return Result.ok(map);
    }

    /**
     * 审核不通过删除图片
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/delete")
    public Result deleteFile(@RequestParam("fileId") String id) {
        System.out.println(id);;
        System.out.println(session);
        Integer fileId = Integer.parseInt(id);
        System.out.println(fileId);
        MyFile myFile = myFileService.queryMyFileById(fileId);
        System.out.println(myFile);
        if (myFile == null) {
            return Result.error("未找到该文件");
        }
        String filePath = myFile.getFilePath();
        File file = new File(filePath);
        if (file.delete()) {
            //删除数据库的记录
            myFileService.deleteMyFile(fileId);
            //修改网盘大小
            Disk disk = diskService.queryByDiskId(myFile.getDiskId());
            disk.setSize(disk.getSize() - myFile.getSize());
            diskService.updateDisk(disk);
            return Result.ok(null);
        }

        return Result.error("服务器异常，删除失败");
    }

    /**
     * 审核通过将图片的exam修改为1
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/pass")
    public Result judgeImage(@RequestParam("fileId") String id) {
        Integer fileId = Integer.parseInt(id);
        MyFile myFile = myFileService.queryMyFileById(fileId);
        myFile.setExamed(1);
        myFileService.updateMyFile(myFile);
        return Result.ok(null);
    }

    /**
     * 分页查看未审核的图片
     * @param cu
     * @param size
     * @return
     */
    @ResponseBody
    @PostMapping("/show")
    public Result showAllNoJudgeImage(@RequestParam("current") String cu,
                                      @RequestParam("pageSize") String size) {
        Integer current = Integer.parseInt(cu);
        Integer pageSize = Integer.parseInt(size);
        System.out.println(current);
        System.out.println(pageSize);
        PageHelper.startPage(current,pageSize);
        List<MyFile> myFiles = myFileService.queryNoJudgeImage();
        for (MyFile myFile : myFiles) {
            User user = userService.queryByDiskId(myFile.getDiskId());
            myFile.setUser(user);
        }
        PageInfo<MyFile> pageInfo = new PageInfo<>(myFiles);
        HashMap<String,Object> map = new HashMap<>();
        map.put("page",pageInfo);
        return Result.ok(map);
    }





}
