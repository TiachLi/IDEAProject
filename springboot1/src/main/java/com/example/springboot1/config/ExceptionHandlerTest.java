package com.example.springboot1.config;

import com.example.springboot1.Entity;
import com.example.springboot1.expection.TestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerTest {
    @ResponseBody
    @ExceptionHandler(TestException.class)
    public Map<String,Object> handleException(Exception e){
        System.out.println("exception handler");
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        return map;
    }
/*    @ResponseBody
    @ExceptionHandler(TestException.class)
    public Entity handleException1(Exception e){
        System.out.println("exception handler");
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        System.out.println("entity exception");
        Entity entity = new Entity();
        entity.setCode(1000);
        entity.setMessage("entity exception");
        return entity;
    }*/
/*    @ExceptionHandler(TestException.class)
    public String handleException(Exception e, HttpServletRequest request){
        System.out.println("exception handler");
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码  4xx 5xx，否则就不会进入定制错误页面的解析流程
      //  Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        //转发到/error
        return "forward:/error";
    }*/


}
