package com.example.springboot_security;

import com.example.springboot_security.bean.Admin;
import com.example.springboot_security.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootSecurityApplicationTests {

    @Resource
    UserMapper userMapper;

    @Resource
    BCryptPasswordEncoder passwordEncoder;

    Admin admin=new Admin();
    @Test
    void contextLoads() {
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }
}
