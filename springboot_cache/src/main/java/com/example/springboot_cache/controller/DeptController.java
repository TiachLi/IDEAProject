package com.example.springboot_cache.controller;



import com.example.springboot_cache.service.DeptService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DeptController {

    @Resource
    DeptService deptService;

/*    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id){
        return deptService.getDeptById(id);
    }*/
}
