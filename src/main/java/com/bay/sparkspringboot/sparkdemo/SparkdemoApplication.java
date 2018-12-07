package com.bay.sparkspringboot.sparkdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.bay.sparkspringboot.sparkdemo.*")
@SpringBootApplication
public class SparkdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkdemoApplication.class, args);
    }
}
