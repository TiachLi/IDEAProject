<%--
  Created by IntelliJ IDEA.
  User: TiachLi
  Date: 2020/4/19
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="main">
    <form action="/registerServlet" method="post" >
        <table>
            <tr>
              <span>用户名：</span> <td><input name="username" type="text" placeholder="由字母和数字组成"></td>
            </tr>
            <tr>
                <td><input name="password" type="password" placeholder="请输入密码"></td>
            </tr>
        </table>
        <tr>
            <td><input type="submit" value="确定"></td>
        </tr>
    </form>
    <div><%=request.getSession().getAttribute("register")==null?  "": request.getSession().getAttribute("register") %>
     <%request.removeAttribute("register");%></div>

</div>
</body>
</html>
