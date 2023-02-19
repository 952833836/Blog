package com.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 博客前台应用程序
 *
 * @author a1387
 * @date 2023/02/18
 */
@SpringBootApplication
@MapperScan("com.blog.mapper")
public class BlogFrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogFrontApplication.class,args);
    }
}
