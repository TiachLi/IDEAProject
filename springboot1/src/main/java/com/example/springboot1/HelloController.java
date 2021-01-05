package com.example.springboot1;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {
    //查出用户数据，在页面展示
    @RequestMapping("/success2")
    public String success(Map<String,Object> map){
        map.put("hello","你好");
        map.put("users",Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }

    @GetMapping("/emp")
    public String test3(    Map<String,Object> map){

        System.out.println("接收到请求");
        map.put("hello","你好");
        map.put("users",Arrays.asList("zhangsan","lisi","wangwu"));
        return "emp/list";
    }

    @PostMapping("/emp")
    public String test4(    Map<String,Object> map){

        System.out.println("接收到post请求");
        map.put("hello","你好");
        map.put("users",Arrays.asList("zhangsan","lisi","wangwu"));
        return "emp/list";
    }
}
