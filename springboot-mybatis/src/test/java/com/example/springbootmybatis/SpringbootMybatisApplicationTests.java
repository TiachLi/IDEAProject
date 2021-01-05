package com.example.springbootmybatis;

import com.example.springbootmybatis.bean.User;
import com.example.springbootmybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringbootMybatisApplicationTests {

    @Resource
    UserMapper userMapper;
    @Test
    void contextLoads() {
        List<User> all = userMapper.findAll();
        System.out.println(all);
        User user = new User();
        user.setName("bbb");
        int i = userMapper.insertDept(user);
        System.out.println(i);
        List<User> all1 = userMapper.findAll();
        System.out.println(all1);
    }

}
