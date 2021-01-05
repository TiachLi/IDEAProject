package com.itheima.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

public class txConfig {
    @Bean
    public DataSourceTransactionManager creatPlatTX(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
