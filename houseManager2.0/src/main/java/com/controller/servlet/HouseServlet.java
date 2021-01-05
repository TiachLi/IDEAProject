package com.controller.servlet;

import com.domain.*;
import com.service.HouseService;
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

@WebServlet("/house/*")
public class HouseServlet extends BaseServlet {

    private HouseService houseService;
   @Override
    public void init() throws ServletException {
       ApplicationContext ac =new ClassPathXmlApplicationContext("applicationContext.xml");
       houseService = ac.getBean("houseService", HouseService.class);
   }



  public void findByPageWithConditions(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //从客户端获取数据
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        Map<String, String[]> parameterMap = request.getParameterMap();
        House house =new House();
        //对数据进行处理封装
        int currentPage=1;
        if (currentPageStr!=null&&!currentPageStr.equals("null")&&currentPageStr.length()>0&&!currentPageStr.equals("undefined")){
            currentPage=Integer.parseInt(currentPageStr);
        }
        int pageSize=5;
        if (pageSizeStr!=null&&!pageSizeStr.equals("null")&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }
        try {
          BeanUtils.populate(house,parameterMap);
         }catch (Exception e){
          e.printStackTrace();
         }
        //调用Service获取每页要展示的信息
        HousePageBean housePageBean = houseService.pageQueryWithCondition(currentPage, pageSize,house);
        //回显数据
        writeValue(housePageBean,response);
    }

   public void addHouse(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //从客户端接收数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装数据
        House house =new House();
        ResultInfo addInfo;
        try {
            BeanUtils.populate(house,parameterMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        //调用Service处理，获得处理结果
        addInfo=houseService.addHouse(house);
        //回显数据
        writeValue(addInfo,response);
    }

    public void deleteHouse(HttpServletRequest request, HttpServletResponse response) throws Exception{
       //获取要删除的房源id
        String houseIdStr = request.getParameter("houseId");
        //对数据进行判断处理
        int houseId=0;
        if (houseIdStr.length()>0&&!houseIdStr.equals("houseId")&&houseIdStr!=null){
            houseId=Integer.parseInt(houseIdStr);
        }
        //执行删除操作
        houseService.deleteHouse(houseId);
    }

    public void showHouse(HttpServletRequest request, HttpServletResponse response) throws Exception{
       //获得参数进行判断处理
        String houseIdStr = request.getParameter("houseId");
        if (houseIdStr==null||houseIdStr.equals("null")||houseIdStr.length()<=0){
            return;
        }
        int houseId=Integer.parseInt(houseIdStr);
        //调用Service
        House oneById = houseService.findOneById(houseId);
        writeValue(oneById,response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception{

        Map<String, String[]> parameterMap = request.getParameterMap();
        House house =new House();
        try {
            BeanUtils.populate(house,parameterMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        ResultInfo info = houseService.updateHouse(house);
        writeValue(info,response);
    }

}
