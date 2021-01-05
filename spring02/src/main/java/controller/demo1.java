package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class demo1 {
    @RequestMapping("test")
    public ModelAndView test1(){
        System.out.println("jinlaile");
    ModelAndView mv =new ModelAndView();
    mv.addObject("msg","hello");
    return mv;
}
}
