package com.example.consumer;

import com.example.consumer.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ConsumerApplicationTests {

    @Resource
    UserService userService;
    @Test
    void contextLoads() {
        userService.hello();
    }

}
