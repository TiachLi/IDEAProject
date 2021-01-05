package com.example.springboot1.controlller;

import com.example.springboot1.expection.TestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @RequestMapping("/aaa")
    public String test(String data){
        return "success";
    }
    @RequestMapping("/myError")
    @ResponseBody
    public String test1(String data) {
        throw new TestException();
    }
}
