package com.git;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.git.sys.mapper")
@ComponentScan("com.git.sys.service")
public class HttpReptileApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpReptileApplication.class, args);
    }

}
