package com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class Demo2 {
    @RequestMapping("/String.do")
    public String demo1(){
        return "success";
    }
    @RequestMapping("/Model")
    public String demo2(Model model){
        model.addAttribute("name","wang li");
        return "success";
    }
    @RequestMapping("/void")
    public ModelAndView demo3(){
        System.out.println("void");
        ModelAndView mc=new ModelAndView();
        mc.addObject("name","model and view");
        mc.setViewName("success");
        return mc;
    }
    @RequestMapping(value = "/json.do",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String demo4( User user) throws JsonProcessingException {
        System.out.println(user);
        user.setName("change");
        ObjectMapper mapper=new ObjectMapper();
        String s = mapper.writeValueAsString(user);
        return s;
    }

    @RequestMapping("/upload")
    public String fileUnload(HttpServletRequest request, MultipartFile upload) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("file");
        File file =new File(realPath);
        System.out.println(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String originalFilename = upload.getOriginalFilename();
        System.out.println(originalFilename);
        upload.transferTo(new File(realPath,originalFilename));
        return "success";
    }


}
