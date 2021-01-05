package com.example.springboot2;

import com.example.springboot2.com.Father;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot2ApplicationTests {

    @Autowired
    Father father;
    @Test
    void contextLoads() {
        father.run();
    }

}
