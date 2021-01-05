package com.example.springboot1.config;

import com.example.springboot1.filter.MyFilter;
import com.example.springboot1.interceptor.LoginInterceptor;
/*import com.example.springboot1.listener.MyListener;*/
import com.example.springboot1.listener.HelloSpringApplicationRunListener;
import com.example.springboot1.servlet.MyServlet;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
         return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/index.html").setViewName("login");
            registry.addViewController("/").setViewName("login");
            registry.addViewController("/main.html").setViewName("dashboard");
            }

             @Override
             public void addInterceptors(InterceptorRegistry registry) {
                 registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/*").
                         excludePathPatterns("/index.html","/","user/login","/mystatic/success3.html");
             }
/*             @Override
             public void addResourceHandlers(ResourceHandlerRegistry registry) {
                 registry.addResourceHandler("/**").addResourceLocations("classpath:/myStatic");
             }*/
         };

    }

    @Bean
    ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean =new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new MyServlet());


        servletRegistrationBean.setUrlMappings(Arrays.asList("/myServlet","/Servlet"));
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

/*
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }
*/

}
