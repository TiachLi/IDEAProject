package com.atguigu.java;

import sun.java2d.pipe.SpanIterator;

public class test {
    static int b=20;//类变量
    int a=10;//实例变量
    public test(){

    }
    public static void main(String[] args) {
        String s1="a";
        String s11=new String("a");
        String s2="b";
        String s3="a"+"b";
        String s5=s1+s2;
        String s4="ab";

        String s6=s5.intern();
        System.out.println(s3==s4);
        System.out.println(s4==s5);
        System.out.println(s3==s5);
        System.out.println(s6==s3);
        System.out.println(s6==s5);
        System.out.println(s11==s1);
        System.out.println(s11=="a");
    }
}
