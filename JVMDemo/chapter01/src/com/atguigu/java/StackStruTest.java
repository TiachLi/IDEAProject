package com.atguigu.java;

/**
 * @author shkstart
 * @create 2020 下午 12:11
 */
public class StackStruTest {
    static int a;//类变量
    int a1;//实例变量

    public  int getA() {
        return a1;
    }

    public static void main(String[] args) {
        StackStruTest stackStruTest =new StackStruTest();
        System.out.println(stackStruTest.a1);
        System.out.println(a);
    }
}
