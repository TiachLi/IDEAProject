package com.example.providorticketer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class ProvidorTicketerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvidorTicketerApplication.class, args);
    }

}
