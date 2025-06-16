package org.example.oj.checkPointService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CheckPointServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckPointServiceApplication.class, args);
    }
}
