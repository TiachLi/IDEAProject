<%--
  Created by IntelliJ IDEA.
  User: TiachLi
  Date: 2020/6/19
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <script src="js/jquery-3.3.1.js"></script>
  <body>
  <a href="/user/String.do">测试String</a>
  <a href="/user/Model">测试Model</a>
  <a href="/user/void">测试void</a>
  <form action="/user/upload" enctype="multipart/form-data" method="post">
     <input type="file" name="upload">
    <input type="submit" value="提交">
  </form>
  </body>

  <script>
    $(function () {
      alert("function");
      $.get("user/json",{name:"wangli",password:"123"},function (data) {
        alert(data.name);
        alert(data.password);
      })
    })
</script>
</html>
