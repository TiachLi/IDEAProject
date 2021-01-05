package com.li.controller;

import com.li.domain.User;
import com.li.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/demo1")
    public String demo1(){
        List<User> all = userService.findAll();
        System.out.println(all);
        return "success";
    }
}
