package com.ui;

import com.service.TestService;
import com.service.impl.TestServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testMain {
    public static void main(String[] args) {
        ApplicationContext ac =new ClassPathXmlApplicationContext("bean.xml");
        TestServiceImpl testService = (TestServiceImpl) ac.getBean("testService");
        testService.test();

    }
}
