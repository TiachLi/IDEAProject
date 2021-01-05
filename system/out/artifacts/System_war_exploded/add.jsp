<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- HTML5文档-->
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js">
    </script>
</head>
<body>
<div class="container">
    <center><h3>添加联系人页面</h3></center>
    <form action="${pageContext.request.contextPath}/addUserServlet" method="post" id="form">
        <div class="form-group" id="nameDiv">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名" autocomplete="off">
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="男" checked="checked"/>男
            <input type="radio" name="gender" value="女"/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄" autocomplete="off">
            <div id="ageDiv"></div>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="address">
                <option value="陕西">陕西</option>
                <option value="北京">北京</option>
                <option value="上海">上海</option>
            </select>
        </div>

        <div class="form-group" >
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" id="qq" name="qq" placeholder="请输入QQ号码" autocomplete="off"/>
            <div id="qqDiv"></div>
        </div>

        <div class="form-group" >
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱地址"/>
            <div id="emailDiv"></div>
        </div>

        <div class="form-group" style="text-align: center">
            <input id="submit" class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" />
        </div>
    </form>
</div>

<script>
        document.getElementById("form").onsubmit=function () {
            return checkQQ()&&checkAge()&&checkEmail();
        }
        document.getElementById("age").oninput= checkAge;
        document.getElementById("qq").oninput=checkQQ;
        document.getElementById("email").oninput=checkEmail;

    function checkAge() {
        let temp = document.getElementById("age").value;
        //2.定义正则表达式
        let reg = /^\d{1,3}$/;
        //3.判断值是否符合正则的规则
        let flag = reg.test(temp);
        //4.提示信息
        let s = document.getElementById("ageDiv");
        if(flag){
            s.innerHTML="";
        }else{
            //提示红色有误
            s.innerHTML = "<font color=red>年龄格式错误</font>";
        }
        return flag;
    }

    let  a =function () {
     alert("a");
        return checkAge;

    }
        let b =a();
    b();
    function checkQQ() {
        let temp = document.getElementById("qq").value;
        //2.定义正则表达式
        let reg = /^\d{6,9}$/;
        //3.判断值是否符合正则的规则
        let flag = reg.test(temp);
        //4.提示信息
        let s = document.getElementById("qqDiv");
        if(flag){
            s.innerHTML="";
        }else{
            //提示红色有误
            s.innerHTML = "<font color=red>QQ号格式错误</font>";
        }
        return flag;
    }

    function checkEmail() {
        let temp = document.getElementById("email").value;
        //2.定义正则表达式
        let reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        //3.判断值是否符合正则的规则
        let flag = reg.test(temp);
        //4.提示信息
        let s = document.getElementById("emailDiv");
        if(flag){
            s.innerHTML="";
        }else{
            //提示红色有误
            s.innerHTML = "<font color=red>邮箱格式错误</font>";
        }
        return flag;
    }
</script>
</body>
</html>