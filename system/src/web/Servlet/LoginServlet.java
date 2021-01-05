package web.Servlet;

import bean.Admin;
import dao.UserDao;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.UserServiceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取用户的验证码
        String input_code=request.getParameter("verifycode");
        //验证码校验
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
        if (checkcode_server!=null){
            if(!checkcode_server.equalsIgnoreCase(input_code)){
                //验证码不正确
                //提示信息
                request.setAttribute("login_msg","验证码错误！");
                //跳转登录页面
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }
        }
        else {
            request.setAttribute("login_msg","验证码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }


        //获取参数封装对象
        Map<String, String[]> map = request.getParameterMap();
        Admin loginAdmin =new Admin();
        try {
            BeanUtils.populate(loginAdmin,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //5.调用Service查询
        UserService service = new UserServiceImpl();
         Admin admin = service.login(loginAdmin);
        //6.判断是否登录成功
        if(admin != null){
            //登录成功
            //将用户存入session
            session.setAttribute("admin",admin);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            //登录失败
            //提示信息
            request.setAttribute("login_msg","用户名或密码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
