<%--
  Created by IntelliJ IDEA.
  User: 30647
  Date: 2022/2/8
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String msg = "";
    msg = (String) request.getAttribute("msg");
%>
<html>
<head>
    <link rel="icon" href="./icon/购物车.png">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="https://fastly.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.30/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="https://fastly.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.30/examples/signin/signin.css"
          rel="stylesheet">

    <title>注册</title>
</head>
<body>

<div class="container">
    <form class="form-signin" action="registerServlet" method="post">
        <h2 class="form-signin-heading">注册你的账号</h2>
        <label class="sr-only">账号</label>
        <input name="inputPhone" id="inputPhone" class="form-control" placeholder="请输入手机号" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input name="inputPassword" type="password" id="inputPassword" class="form-control" placeholder="请输入密码"
               required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
        <button class="btn btn-lg btn-default  btn-block" onclick="window.location.href='/demo_war_exploded/login.jsp'"
                type="button">返回登录页面
        </button>
        <span style="color:red;font-size:12px">${msg}</span></br>
    </form>

</div> <!-- /container -->
</body>
</html>
