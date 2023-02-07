package com.sangeng.controller;

import com.sangeng.annotation.SystemLog;
import com.sangeng.entity.User;
import com.sangeng.result.Result;
import com.sangeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user){

        return userService.register(user);
    }

    //查询个人中心
    @GetMapping("/userInfo")
    public Result userInfo(){
        return userService.userInfo();
    }

    //保存个人资料
    @SystemLog(businessName = "更新用户信息")
    @PutMapping("/userInfo")
    public Result updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
}
