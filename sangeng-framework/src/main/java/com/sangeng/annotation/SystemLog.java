package com.sangeng.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//实现效果为让加上 SystemLog 的注解都实现日志的打印
//表明该注解处于一个RUNTIME的状态
@Retention(RetentionPolicy.RUNTIME)
//表明该注解的作用范围为method
@Target(ElementType.METHOD)
public @interface SystemLog {
    String businessName();
}
