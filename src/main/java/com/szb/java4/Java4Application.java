package com.szb.java4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.szb.java4.mapper")
@SpringBootApplication
public class Java4Application {

    public static void main(String[] args) {
        SpringApplication.run(Java4Application.class, args);
    }

}
