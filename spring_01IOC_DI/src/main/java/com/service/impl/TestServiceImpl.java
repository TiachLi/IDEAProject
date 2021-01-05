package com.service.impl;

import com.service.TestService;

import javax.xml.transform.Source;

public class TestServiceImpl implements TestService {


    public void init() {
        System.out.println("init");
    }

    public void test() {
        System.out.println("test");
    }

    public void destroy() {
        System.out.println("destroy");
    }
}
