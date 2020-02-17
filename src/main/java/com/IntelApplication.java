package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*@EnableAutoConfiguration
@ComponentScan()*/

@SpringBootApplication
@MapperScan(basePackages = {"com.mapper"})
public class IntelApplication {
    public static void main(String[] args) {
        SpringApplication.run(IntelApplication.class, args);
    }
}