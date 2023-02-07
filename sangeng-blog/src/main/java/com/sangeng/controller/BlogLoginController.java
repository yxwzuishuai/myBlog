package com.sangeng.controller;


import com.sangeng.Exception.SystemException;
import com.sangeng.entity.User;
import com.sangeng.result.Result;
import com.sangeng.result.ResultEnum;
import com.sangeng.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(ResultEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public Result logout(){
        return blogLoginService.logout();
    }
}
