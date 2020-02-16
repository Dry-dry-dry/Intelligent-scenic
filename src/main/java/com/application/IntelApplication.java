package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*@EnableAutoConfiguration
@ComponentScan()*/

@SpringBootApplication
public class IntelApplication {
    public static void main(String[] args) {
        SpringApplication.run(IntelApplication.class, args);
    }
}