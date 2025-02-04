package web.Servlet;

import bean.User;
import service.UserService;
import service.UserServiceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class findUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取ID
        String id = request.getParameter("id");
        UserService service =new UserServiceImpl();
        User user= service.findByID(id);
        request.getSession().setAttribute("user",user);
        request.getRequestDispatcher("/update.jsp").forward(request,response);

       // response.sendRedirect(request.getContextPath()+"/update.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
