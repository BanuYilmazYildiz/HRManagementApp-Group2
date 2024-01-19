package com.bilgeadam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmployeeMicroServiceSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeMicroServiceSpringApplication.class);
    }
}