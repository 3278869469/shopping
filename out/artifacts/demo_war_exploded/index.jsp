<%@ page import="Model.Bean.Goods" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="goodsDB" class="Model.DB.goodsDB"></jsp:useBean>
<%
    List<Goods> list = goodsDB.getOrderGoods();
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <link rel="icon" href="./icon/购物车.png">

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="https://fastly.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.30/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- Custom styles for this template -->
    <%--    <link href="https://fastly.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.30/examples/jumbotron/jumbotron.css" rel="stylesheet">--%>

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
            <a class="navbar-brand" href="/demo_war_exploded/">网上购物子系统</a>
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

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h1>My Shop</h1>
        <p>优质商品任你挑选</p>
        <p>You can choose high-quality goods.</p>
        <p><a class="btn btn-primary btn-lg" href="/demo_war_exploded/goods.jsp" role="button">Learn more &raquo;</a>
        </p>
    </div>
</div>

<div class="container">
    <!-- Example row of columns -->
    <div class="row">
        <%
            for (int i = 0; i < 3; i++) {
                Goods good = list.get(i);
        %>
        <div class="col-md-4">
            <img class="img-responsive"
                 src="<%=good.getGPicture()%>"
                 alt="Generic placeholder image" width="180" height="180">
            <h2><%=good.getGName()%>
            </h2>
            <p><%=good.getGDetail()%>
            </p>
            <p><a class="btn btn-default" href="/demo_war_exploded/goodDetails.jsp?gId=<%=good.getGId()%>"
                  role="button">View details
                &raquo;</a></p>
        </div>
        <%
            }
        %>
    </div>

    <hr>

    <footer>
        <p>&copy; 2022 Company, Inc.</p>
    </footer>
</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
