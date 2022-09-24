<%@ page import="Model.DB.DBconnection" %>
<%@ page import="Model.Bean.OrdersGoods" %>
<%@ page import="Model.DB.buyDB" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="Model.Bean.Goods" %>
<%@ page import="Model.DB.goodsDB" %><%--
  Created by IntelliJ IDEA.
  User: 30647
  Date: 2022/2/13
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpServletRequest req = (HttpServletRequest) request;
    int uId = (int) req.getSession().getAttribute("uId");
    DBconnection db = new DBconnection();
    List<OrdersGoods> list = null;
    buyDB dao = new buyDB();
    try {
        Connection conn = db.getConnection();
        list = dao.getOrders(conn, uId);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <link rel="icon" href="./icon/购物车.png">
    <title>我的订单</title>

    <!-- Bootstrap core CSS -->
    <link href="https://fastly.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.30/dist/css/bootstrap.min.css"
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
        <h1>我的订单</h1>
    </div>
</div>

<div class="container theme-showcase" role="main">
    <div class="container-fluid">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>订单编号</th>
                <th>商品名称</th>
                <th>购买数量</th>
                <th>付款金额</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (OrdersGoods og : list) {
                    goodsDB goods = new goodsDB();
                    Goods good = null;
                    good = goods.getGoods(og.getGId());
            %>
            <tr onclick="window.location.href='/demo_war_exploded/orderDetails.jsp?oId=<%=og.getOId()%>&gId=<%=good.getGId()%>'">
                <td><%=og.getOId()%>
                </td>
                <td><%=good.getGName()%></td>
                <td><%=og.getGNum()%></td>
                <td><%=og.getGNum() * good.getGPrice()%>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>

    </div>
</div>
<hr>
<br>
<br>
<br>
</body>
</html>
