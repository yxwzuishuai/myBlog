package com.sangeng.result;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author bing_  @create 2022/2/7-17:37
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public Result() {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result errorResult(int code, String msg) {
        Result result = new Result();
        return result.error(code, msg);
    }
    public static Result okResult() {
        Result result = new Result();
        return result;
    }
    public static Result okResult(int code, String msg) {
        Result result = new Result();
        return result.ok(code, null, msg);
    }

    public static Result okResult(Object data) {
        Result result = setAppHttpCodeEnum(ResultEnum.SUCCESS, ResultEnum.SUCCESS.getMsg());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }

    public static Result errorResult(ResultEnum enums){
        return setAppHttpCodeEnum(enums,enums.getMsg());
    }

    public static Result errorResult(ResultEnum enums, String msg){
        return setAppHttpCodeEnum(enums,msg);
    }

    public static Result setAppHttpCodeEnum(ResultEnum enums){
        return okResult(enums.getCode(),enums.getMsg());
    }

    private static Result setAppHttpCodeEnum(ResultEnum enums, String msg){
        return okResult(enums.getCode(),msg);
    }

    public Result<?> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public Result<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public Result<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    public Result<?> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}