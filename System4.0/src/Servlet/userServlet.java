package Servlet;

import User.user;
import dao.UserDao;
import javafx.scene.control.Alert;
import org.apache.commons.beanutils.BeanUtils;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet("/userServlet")
public class userServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进来了");
        //设置编码
        req.setCharacterEncoding("utf-8");
        user loginUser = new user();
        //获取所有请求对象
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        //创建user对象
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        //获取验证码
        String input_checkCode = req.getParameter("input_checkCode");
        System.out.println(input_checkCode);
        String checkCode = (String) req.getSession().getAttribute("code_session");
        //删除验证码
        req.getSession().removeAttribute("code_session");
        System.out.println(checkCode);
        System.out.println(username+""+password);
        if (checkCode!= null && input_checkCode.equalsIgnoreCase(checkCode)) {
            UserDao Dao = new UserDao();
            user login = Dao.login(loginUser);
            System.out.println(login.getUsername()+""+login.getPassword());
            //判断
            if (login == null) {
                //登录失败
                req.setAttribute("login_error","用户名或密码错误");
                req.getRequestDispatcher("/Login2.jsp").forward(req,resp);
            } else {
                //登录成功
                //存储数据
                req.setAttribute("user", login);
                //转发
                req.getRequestDispatcher("/successServlet").forward(req, resp);
            }
        }
        else {
            //验证码不一致
            //存储提示信息到request
           req.setAttribute("cc_error","验证码错误");
            //转发到登录页面
            req.getRequestDispatcher("/Login2.jsp").forward(req,resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      this.doGet(req,resp);
    }
}
