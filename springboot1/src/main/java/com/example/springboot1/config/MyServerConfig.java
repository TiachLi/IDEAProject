package com.example.springboot1.config;

import com.example.springboot1.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class MyServerConfig{
    @Bean
    public LocaleResolver locale(){
        return new MyLocaleResolver();
    }


}
