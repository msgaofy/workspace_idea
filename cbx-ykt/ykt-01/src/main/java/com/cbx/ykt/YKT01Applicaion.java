package com.cbx.ykt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class YKT01Applicaion {
    public static void main(String[] args) {
        SpringApplication.run(YKT01Applicaion.class);
    }
}
