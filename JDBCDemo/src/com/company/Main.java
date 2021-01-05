package com.company;

public class Main {

    public static void main(String[] args) {
        user root =new user();
       //root.Register("zhangsan","123456");


    if(root.Logic("zhangsan","123456")){
        System.out.println("登录成功");
    }
    else {
        System.out.println("登录失败");
    }
    }
}
