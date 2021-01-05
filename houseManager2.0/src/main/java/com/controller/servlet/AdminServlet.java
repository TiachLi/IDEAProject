package com.controller.servlet;

import com.domain.Admin;
import com.domain.ResultInfo;
import com.service.AdminService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet("/admin/*")
public class AdminServlet extends BaseServlet {

    private AdminService adminService;
    @Override
    public void init() throws ServletException {
        ApplicationContext ac =new ClassPathXmlApplicationContext("applicationContext.xml");
        adminService = ac.getBean("adminService", AdminService.class);
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //校验验证码
        String check = request.getParameter("check");
        String check_server= (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");

        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        if (check==null||!check.equalsIgnoreCase(check_server)){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            writeValue(info,response);
            return;
        }
        Admin admin =new Admin();
        //获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装对象
        BeanUtils.populate(admin,parameterMap);
        //调用Service
       info = adminService.register(admin);
        if (!info.getFlag()){
            info.setFlag(false);
        }
        //将数据返回视图层
        writeValue(info,response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //校验验证码
        String check = request.getParameter("check");

        String check_server= (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");

        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        if (check==null||!check.equalsIgnoreCase(check_server)){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            writeValue(info,response);
            return;
        }
        //接收客户端发送的数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        Admin admin =new Admin();
        //封装对象
        BeanUtils.populate(admin,parameterMap);
        //调用Service处理
        ResultInfo registerResult = adminService.login(admin);
        if (registerResult.getFlag()){
            registerResult.setFlag(true);
            request.getSession().setAttribute("admin",admin);
        }
        //发送数据
        writeValue(registerResult,response);

    }
}
