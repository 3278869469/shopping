<%@ page import="Model.Bean.Goods" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 30647
  Date: 2022/2/13
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="goodsDB" class="Model.DB.goodsDB"></jsp:useBean>
<%
    List<Goods> list = goodsDB.getAllGoods();
%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <link rel="icon" href="./icon/购物车.png">

    <title>商品</title>

    <!-- Bootstrap core CSS -->
    <link href="https://fastly.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.30/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>
<!-- NAVBAR
================================================== -->
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

<div class="jumbotron">
    <div class="container">
        <h1>商品列表</h1>
    </div>
</div>

<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container marketing">

    <div class="container marketing">

        <!-- Three columns of text below the carousel -->
        <div class="row">
            <%
                for (int i = 0; i < list.size(); i++) {
                    Goods good = list.get(i);
            %>
            <div class="col-lg-4">
                <img class="img-responsive"
                     src="<%=good.getGPicture()%>"
                     alt="Generic placeholder image" width="210" height="210">
                <h2><%=good.getGName()%>
                </h2>
                <p><%=good.getGDetail()%>
                </p>
                <p><a class="btn btn-default" href="/demo_war_exploded/goodDetails.jsp?gId=<%=good.getGId()%>" role="button">View details
                    &raquo;</a></p>
                <input class="sr-only" name="goodId" value="<%=good.getGId()%>">
            </div><!-- /.col-lg-4 -->
            <%
                }
            %>
        </div><!-- /.row -->


    </div><!-- /.container -->


    <hr>
    <br>
    <br>
    <br>

</div><!-- /.container -->

</body>
</html>
