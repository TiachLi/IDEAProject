package Servlet;

import User.user;
import dao.UserDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        user newUser =new user();
        try {
            BeanUtils.populate(newUser,map);
            UserDao registerDao =new UserDao();

            String regex="[a-zA-Z]+\\d+";//正则表道达式
            Pattern p=Pattern.compile(regex);
            Matcher m=p.matcher(newUser.getUsername());
            if(m.matches()){
                user isregister = registerDao.register(newUser.getUsername(), newUser.getPassword());
                if (isregister!=null){
                    request.getSession().setAttribute("register","注册成功");
                    request.getRequestDispatcher("/Register.jsp").forward(request,response);
                }
                else{
                    request.getSession().setAttribute("register","注册失败,用户名已存在");
                    request.getRequestDispatcher("/Register.jsp").forward(request,response);
                }
            }else{
                request.getSession().setAttribute("register","注册失败，用户名应由字母和数字组成");
                request.getRequestDispatcher("/Register.jsp").forward(request,response);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doPost(request,response);
    }
}
