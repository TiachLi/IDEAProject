package com.example.heima_oauth_source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class HeimaOauthSourceApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {

    }

}
