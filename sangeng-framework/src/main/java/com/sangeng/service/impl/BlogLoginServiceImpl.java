package com.sangeng.service.impl;

import com.sangeng.entity.LoginUser;
import com.sangeng.entity.User;
import com.sangeng.entity.vo.BlogUserLoginVo;
import com.sangeng.entity.vo.UserInfoVo;
import com.sangeng.result.Result;
import com.sangeng.service.BlogLoginService;

import com.sangeng.util.BeanCopyUtils;
import com.sangeng.util.JwtUtil;
import com.sangeng.util.RedisCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.sangeng.constants.Constants.LOGIN_PREFIX;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    //需要手动放入容器中 SecurityConfig
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Override
    public Result login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //authenticate将用户名和密码封装起来，传到UserDetailsServiceImpl，最后将LoginUser返回
        //返回用户的 权限
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userId生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String token = JwtUtil.createJWT(userId);
        //存入到redis
        redisCache.setCacheObject(LOGIN_PREFIX+userId, loginUser);
        //把user转为userInfo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(token, userInfoVo);

        //响应数据（把token和userinfo封装返回）
        return Result.okResult(blogUserLoginVo);
    }

    @Override
    public Result logout() {
        //TODO 后续会进行封装
        //获取token，解析获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userId
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject(LOGIN_PREFIX+userId);
        return Result.okResult();
    }
}
