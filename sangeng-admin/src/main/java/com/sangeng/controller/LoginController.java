package com.sangeng.controller;

import com.sangeng.Exception.SystemException;
import com.sangeng.entity.User;
import com.sangeng.result.Result;
import com.sangeng.result.ResultEnum;
import com.sangeng.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(ResultEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }
}
