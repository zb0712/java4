package com.szb.java4.controller;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.code.kaptcha.Constants;
import com.szb.java4.bean.Disk;
import com.szb.java4.bean.User;
import com.szb.java4.common.Result;
import com.szb.java4.mapper.DiskMapper;
import com.szb.java4.service.DiskService;
import com.szb.java4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author 石致彬
 * @create 2021-02-20 13:45
 */
@Controller
public class UserController extends BaseController{
    @Autowired
    UserService userService;

    @Autowired
    DiskService diskService;

    /**
     * 用户注册
     * @param user
     * @param code
     * @return
     */
    @ResponseBody
    @PostMapping("/regist")
    public Result registUser(User user, @RequestParam("code") String code) {
        String text = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (text!=null && code.equalsIgnoreCase(text)) {
            if (userService.existUsername(user.getUsername())) {
                return Result.error("用户名已存在");
            } else {
                user.setIdentity(0);
                userService.registUser(user);
                user = userService.queryUserByName(user.getUsername());
                Disk disk = new Disk(null,user.getUserId(),0,1048576);
                diskService.insertDisk(disk);
                Disk disk1 = diskService.queryByUserId(user.getUserId());
                user.setDiskId(disk1.getDiskId());
                userService.updateUser(user);
                return Result.ok(null);
            }
        } else if (text==null){
            return Result.error("表单重复提交");
        } else {
            return Result.error("验证码错误");
        }
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public Result login(User user) {
        User login = userService.login(user);
        HashMap<String,Object> map = new HashMap<>();
        if (login != null) {
            session.setAttribute("loginUser",login);
            map.put("loginUser",login);
            return Result.ok(map);
        }
        return Result.error("用户名或密码错误");
    }

    /**
     * 注销，清空session
     * @return
     */
    @ResponseBody
    @GetMapping("/logout")
    public Result logout() {
        session.invalidate();
        return Result.ok(null);
    }
}
