package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
//一定要加入扫描dao接口注解，否则注入时会发生错误
@MapperScan(value = "com.baizhi.dao")
public class Rukou extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Rukou.class,args);
    }
}
