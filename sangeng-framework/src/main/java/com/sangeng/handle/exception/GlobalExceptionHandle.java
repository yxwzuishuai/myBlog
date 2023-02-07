package com.sangeng.handle.exception;

import com.sangeng.Exception.SystemException;
import com.sangeng.result.Result;
import com.sangeng.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    //处理自己代码中手动抛出的的异常
    @ExceptionHandler(SystemException.class)
    public Result systemExceptionHandle(SystemException e){
        //打印异常信息
        log.error("出现了异常!{}", e.getMessage());
        //从异常对象中获取提示信息封装返回
        return Result.errorResult(e.getCode(), e.getMsg());
    }

    //处理别的异常
    @ExceptionHandler(Exception.class)
    public Result ExceptionHandle(Exception e){
        //打印异常信息
        log.error("出现了异常!  {}", e);
        //从异常对象中获取提示信息封装返回
        return Result.errorResult(ResultEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
