package com.example.springboot1.dao;

import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.sql.DataSource;


public class TestDataSource {
    @Resource
    DataSource dataSource;

    @Bean
    public DataSource getDataSource(){
        System.out.println(dataSource);
        return dataSource;
    }
}
