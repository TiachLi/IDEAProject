package com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Map;

@Controller
@RequestMapping("/test")
public class demo1 {
    @RequestMapping("/first")
    public String first(@ModelAttribute("1")User user) {
        System.out.println(user);
        return "success";
    }
    @ModelAttribute
    public void model(Map<String,User> map){
        User user =new User();
        user.setAge(15);
        map.put("1",user);
    }

}
