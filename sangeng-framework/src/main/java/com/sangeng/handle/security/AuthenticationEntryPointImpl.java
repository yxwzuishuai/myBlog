package com.sangeng.handle.security;

import com.alibaba.fastjson.JSON;
import com.sangeng.result.Result;
import com.sangeng.result.ResultEnum;
import com.sangeng.util.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sangeng.result.ResultEnum.*;

@Component
public class AuthenticationEntryPointImpl  implements AuthenticationEntryPoint {

    //认证失败处理器
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        //InsufficientAuthenticationException
        //BadCredentialsException
        Result result = null;
        if(e instanceof BadCredentialsException){
            result = Result.errorResult(LOGIN_ERROR.getCode(),e.getMessage());
        }else if(e instanceof InsufficientAuthenticationException){
            result = Result.errorResult(NEED_LOGIN);
        }else{
            result = Result.errorResult(SYSTEM_ERROR.getCode(),"认证或授权失败");
        }
        //响应给前端
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
    }


}
