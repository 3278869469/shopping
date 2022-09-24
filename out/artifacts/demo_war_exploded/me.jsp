<%@ page import="Model.DB.DBconnection" %>
<%@ page import="Model.Bean.User" %>
<%@ page import="Model.DB.userDB" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: 30647
  Date: 2022/2/13
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpServletRequest req = (HttpServletRequest) request;
    String uName, uAddress, phone;
    DBconnection db = new DBconnection();
    phone = (String) req.getSession().getAttribute("userPhone");
    User user = new User(phone);
    userDB dao = new userDB();
    try {
        Connection conn = db.getConnection();
        user = dao.Exist(conn, user);
    } catch (Exception e) {
        e.printStackTrace();
    }
    uName = user.getUName();
    uAddress = user.getUAddress();
%>

<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <link rel="icon" href="./icon/购物车.png">

    <title>个人中心</title>

    <!-- Bootstrap core CSS -->
    <link href="https://fastly.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.30/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://fastly.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.30/examples/dashboard/dashboard.css"
          rel="stylesheet">

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/demo_war_exploded">网上购物子系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/demo_war_exploded/">主页</a></li>
                <li><a href="/demo_war_exploded/goods.jsp">商品列表</a></li>
                <li><a href="/demo_war_exploded/cart.jsp">购物车</a></li>
                <li><a href="/demo_war_exploded/orders.jsp">我的订单</a></li>
                <li><a href="/demo_war_exploded/me.jsp">个人中心</a></li>
            </ul>
            <form class="navbar-form navbar-right" action="searchServlet" method="get">
                <div class="form-group">
                    <input type="text" placeholder="输入关键字" class="form-control" name="search">
                </div>
                <button type="submit" class="btn btn-success">搜索</button>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="/demo_war_exploded/me.jsp">个人信息 <span class="sr-only">(current)</span></a></li>
                <li><a href="/demo_war_exploded/fixme.jsp">修改个人信息</a></li>
                <li><a href="/demo_war_exploded/fixpassword.jsp">修改密码</a></li>
            </ul>

        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">个人信息</h1>

            <div class="row placeholders">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="./icon/用户.png"
                         width="128" height="128" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>用户名</h4>
                    <span class="text-muted"><%=uName%></span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="./icon/地址.png"
                         width="128" height="128" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>地址</h4>
                    <span class="text-muted"><%=uAddress%></span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="./icon/手机.png"
                         width="128" height="128" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>手机号</h4>
                    <span class="text-muted"><%=phone%></span>
                </div>
<%--                <div class="col-xs-6 col-sm-3 placeholder">--%>
<%--                    <img src="./icon/购物车.png"--%>
<%--                         width="128" height="128" class="img-responsive" alt="Generic placeholder thumbnail">--%>
<%--                    <h4>购物车</h4>--%>
<%--                    &lt;%&ndash;                    <span class="text-muted">Something else</span>&ndash;%&gt;--%>
<%--                </div>--%>
            </div>


        </div>
    </div>
</div>

</body>

</html>
