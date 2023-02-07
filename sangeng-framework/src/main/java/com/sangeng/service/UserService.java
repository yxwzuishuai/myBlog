package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.entity.User;
import com.sangeng.result.Result;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-02-02 10:08:17
 */
public interface UserService extends IService<User> {

    Result register(User user);

    //查询个人中心
    Result userInfo();

    Result updateUserInfo(User user);
}

