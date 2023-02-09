package com.sangeng.controller;

import com.sangeng.entity.Menu;
import com.sangeng.entity.vo.AdminUserInfoVo;
import com.sangeng.entity.vo.RoutersVo;
import com.sangeng.entity.vo.UserInfoVo;
import com.sangeng.exception.SystemException;
import com.sangeng.entity.LoginUser;
import com.sangeng.entity.User;
import com.sangeng.result.Result;
import com.sangeng.result.ResultEnum;
import com.sangeng.service.LoginService;
import com.sangeng.service.MenuService;
import com.sangeng.service.RoleService;
import com.sangeng.util.BeanCopyUtils;
import com.sangeng.util.RedisCache;
import com.sangeng.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisCache redisCache;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(ResultEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }


    @GetMapping("getInfo")
    public Result<AdminUserInfoVo> getInfo(){
        //1.获取当前登陆的用户
        LoginUser userId = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(userId.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(userId.getUser().getId());
        //封装数据返回


        //获取用户信息
        User user = userId.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装数据返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
        return Result.okResult(adminUserInfoVo);
    }


    @GetMapping("getRouters")
    public Result<RoutersVo> getRouters(){
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return Result.okResult(new RoutersVo(menus));
    }

    @PostMapping("/user/logout")
    public Result logout(){

        Long userId = SecurityUtils.getUserId();

        redisCache.deleteObject("login:"+userId);
        return Result.okResult();

    }

}
