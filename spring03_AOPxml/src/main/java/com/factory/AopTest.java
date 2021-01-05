package com.factory;

public class AopTest {
    public void before(){
        System.out.println("before test");
    }

    public void after(){
        System.out.println("after test");
    }

    public void afterThrow(){
        System.out.println("before test");
    }
}
