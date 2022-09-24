<%@ page import="Model.Bean.Goods" %><%--
  Created by IntelliJ IDEA.
  User: 30647
  Date: 2022/2/16
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="goodsDB" class="Model.DB.goodsDB"></jsp:useBean>
<%
    int gId = Integer.valueOf(request.getParameter("gId"));
    Goods good = goodsDB.getGoods(gId);
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="./icon/购物车.png">
    <!-- Bootstrap core CSS -->
    <link href="https://fastly.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.30/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <title>商品详情</title>
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

<form id="Form1" name="Form1" action="addCartServlet" method="get">
    <div class="container">
        <input class="sr-only" value="<%=gId%>" name="gId">
        <div class="jumbotron">
            <h2><%=good.getGName()%>
            </h2>
            <p class="lead">RMB <%=good.getGPrice()%>
            </p>
        </div>

        <div class="row marketing">
            <div class="col-lg-6">
                <img class="img-responsive" src="<%=good.getGPicture()%>"
                     alt="Generic placeholder image" width="360" height="360">
            </div>

            <div class="col-lg-6">
                <h2>产品信息</h2>
                <h4><%=good.getGCategory()%>
                </h4>
                <p><%=good.getGDetail()%>
                </p>
                <p>月销   <%=good.getGSales()%>
                </p>
                <h4>购买数量</h4>
                <button onclick="subtractOne()" type="button">-</button>
                <input style="width: 5%" name="number" value="1" id="number">
                <button onclick="addOne()" type="button">+</button>
                </p>
                <p>
                </p>
                <p>
            </div>
            <button class="btn btn-lg btn-success" type="button" onclick="buy()">立 即 购 买</button>
            <button class="btn btn-lg btn-primary" type="submit">加入购物车</button>
        </div>
        <hr>
        <br>
        <br>
        <br>

    </div> <!-- /container -->
</form>
</div>

</body>

<%
    String msg = (String) request.getAttribute("msg");         // 获取错误属性
    if (msg != null) {
%>
<script type="text/javascript" language="javascript">
    alert("<%=msg%>");                                            // 弹出错误信息
</script>
<%
    }
%>

<script>
    function buy() {
        var m = "您真的确定要购买吗？";
        if (confirm(m) == true) {
            document.getElementById("Form1").action = "buyServlet"
            document.getElementById("Form1").submit();
        }
    }

    function addOne() {
        var num = document.getElementById("number");
        num.value = parseInt(num.value) + 1;
    }

    function subtractOne() {
        var num = document.getElementById("number");
        if (parseInt(num.value) > 1) {
            num.value = parseInt(num.value) - 1;
        }

    }

</script>
</html>
