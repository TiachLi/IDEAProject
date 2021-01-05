<%--
  Created by IntelliJ IDEA.
  User: TiachLi
  Date: 2020/7/1
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <script src="js/jquery-3.3.1.js"></script>
  <body>
  <a href="/SSM/user/demo1">test demo1</a>
  <h1 id="div">

  </h1>
  </body>
  <script>
    $(function () {
      $.get("user/demo1",{userName:"zhangsan",sex:"男"} ,function (data) {
        alert("发送请求");
        $("#div").html(data.user.userName);
      })
    })
  </script>
</html>
