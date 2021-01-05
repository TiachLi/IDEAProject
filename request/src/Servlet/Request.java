package Servlet;

import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/request")
public class Request extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
       //获取请求行
       //获取虚拟目录
     /*   System.out.println(request.getContextPath());
       //获取servlet路径
        System.out.println(request.getServletPath());
       //获取本地资源路径（统一资源标识符）
        System.out.println(request.getRequestURI());
        //获取完整资源路径（统一资源定位符）
        System.out.println(request.getRequestURL());
       //获取HTTP版本
        System.out.println(request.getProtocol());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            System.out.println(headerNames.nextElement());
        }*/

        String username = request.getParameter("username");
        System.out.println(username);
        Enumeration<String> parameterNames = request.getParameterNames();
         while (parameterNames.hasMoreElements()){
             System.out.println(parameterNames.nextElement());
         }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   doPost(request,response);
    }
}
