package com.sangeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author bing_  @create 2022/2/7-15:42
 */
@EnableScheduling
@MapperScan("com.sangeng.mapper")
@SpringBootApplication
public class SanGengBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SanGengBlogApplication.class,args);
    }
}
