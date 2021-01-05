package com.example.springboot_amqs;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableRabbit
@SpringBootApplication
public class SpringbootAmqsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAmqsApplication.class, args);
    }

}
