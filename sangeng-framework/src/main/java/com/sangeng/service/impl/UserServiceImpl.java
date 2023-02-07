package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.Exception.SystemException;
import com.sangeng.entity.User;
import com.sangeng.mapper.UserMapper;
import com.sangeng.result.Result;
import com.sangeng.service.UserService;
import com.sangeng.util.BeanCopyUtils;
import com.sangeng.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.sangeng.entity.vo.UserInfoVo;


import static com.sangeng.result.ResultEnum.*;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-02-02 10:08:18
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result register(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(NICKNAME_NOT_NULL);
        }
        if(userNameExist(user.getUserName())){
            throw new SystemException(USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(NICKNAME_EXIST);
        }
        //...
        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //存入数据库
        save(user);
        return Result.okResult();
    }

    //查询个人中心
    @Override
    public Result userInfo() {
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);
        //将user转为userVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return Result.okResult(userInfoVo);
    }

    //更新用户个人信息
    @Override
    public Result updateUserInfo(User user) {
        updateById(user);
        return Result.okResult();

    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,nickName);
        return count(queryWrapper)>0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        return count(queryWrapper)>0;
    }
}

