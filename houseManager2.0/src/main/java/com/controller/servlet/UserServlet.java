package com.controller.servlet;

import com.domain.ResultInfo;
import com.domain.User;
import com.domain.UserPageBean;
import com.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService;
    @Override
    public void init() throws ServletException {

        ApplicationContext ac =new ClassPathXmlApplicationContext("applicationContext.xml");
        userService=ac.getBean("userService", UserService.class);
    }
  public void findByPageWithConditions(HttpServletRequest request, HttpServletResponse response) throws Exception{
       //校验验证码
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        //从视图层获取要查询的信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装处理
        User user =new User();
        try{
            BeanUtils.populate(user,parameterMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        //对数据进行处理
        int currentPage=1;
        if (currentPageStr!=null&&!currentPageStr.equals("null")&&currentPageStr.length()>0&&!currentPageStr.equals("undefined")){
            currentPage=Integer.parseInt(currentPageStr);
        }
        int pageSize=5;
        if (pageSizeStr!=null&&!pageSizeStr.equals("null")&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }
        //调用Service获得每页要展示的数据
      UserPageBean userPageBean =new UserPageBean();
      userPageBean = userService.pageQueryWithCondition(currentPage, pageSize,user);
      //将信息返回给视图层展示
      writeValue(userPageBean,response);
    }

    public void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //接收数据并封装
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user =new User();
        ResultInfo addInfo =new ResultInfo();
        try {
            BeanUtils.populate(user,parameterMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        //调用service获得结果并返回
        addInfo=userService.addUser(user);
        writeValue(addInfo,response);
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //接收数据
        String userIdStr = request.getParameter("userId");
        //处理判断
        int userId=0;
        if (userIdStr.length()>0&&!userIdStr.equals("userId")&&userIdStr!=null){
            userId=Integer.parseInt(userIdStr);
        }
        //执行删除操作
        userService.deleteUser(userId);
    }

    public void showUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //获取请求的数据
        String userIdStr = request.getParameter("userId");
        String uTelStr = request.getParameter("userTel");
        //处理判断调用Service
        if (!(userIdStr==null||userIdStr.equals("null")||userIdStr.length()<=0)){
            int userId =Integer.parseInt(userIdStr);
            User oneById = userService.findOneById(userId);
            writeValue(oneById,response);
        }
        if (!(uTelStr==null||uTelStr.equals("null")||uTelStr.length()<=0)){
            User oneByTel = userService.findOneByTel(uTelStr);
            writeValue(oneByTel,response);
        }

    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装对象
        User user =new User();
        ResultInfo info =new ResultInfo();
        try {
            BeanUtils.populate(user,parameterMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        //调用Service进行逻辑处理
        info=userService.updateUser(user);
        writeValue(info,response);
    }

}
