package com.example.JavaSQL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
public class JavaSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSqlApplication.class, args);
    }

}
