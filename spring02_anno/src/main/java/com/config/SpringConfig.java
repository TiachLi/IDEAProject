package com.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("com")
@Configuration
@Import(jdbcConfiig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfig {

}
