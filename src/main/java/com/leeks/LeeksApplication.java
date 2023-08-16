package com.leeks;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.leeks.dao")
public class LeeksApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeeksApplication.class, args);
    }

}
