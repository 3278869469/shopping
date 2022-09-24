<%--
  Created by IntelliJ IDEA.
  User: 30647
  Date: 2022/2/8
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String msg = (String) request.getAttribute("msg");
    String userPhone = "", password = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie value : cookies) {
            if (value.getName().equals("userPhone")) {
                userPhone = value.getValue();
            } else if (value.getName().equals("password")) {
                password = value.getValue();
            }
        }
    }
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

    <title>登录</title>
</head>
<body>

<div class="container">
    <form class="form-signin" action="loginServlet" method="post">
        <h2 class="form-signin-heading">请先登录</h2>
        <input name="inputPhone" class="form-control" placeholder="请输入手机号" value="<%=userPhone%>">
        <input name="inputPassword" type="password" class="form-control" placeholder="请输入密码"
               value="<%=password%>">
        <div>
            <input type="checkbox" name="remenber" value="on">
            <label>记住密码</label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        <button class="btn btn-lg btn-default btn-block" type="button"
                onclick="window.location.href='/demo_war_exploded/register.jsp'">注册
        </button>
        <span style="color:red;font-size:12px">${msg}</span></br>
    </form>

</div> <!-- /container -->

</body>
</html>
