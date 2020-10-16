package com.bihell.zuche;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bihell"})
@MapperScan(basePackages = {"com.bihell.dao"})
public class ZucheApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZucheApplication.class, args);
    }
}
