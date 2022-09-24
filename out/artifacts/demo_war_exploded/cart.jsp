<%@ page import="Model.DB.DBconnection" %>
<%@ page import="Model.Bean.User" %>
<%@ page import="Model.DB.userDB" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="Model.DB.cartDB" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Bean.CartGoods" %>
<%@ page import="Model.DB.goodsDB" %>
<%@ page import="Model.Bean.Goods" %><%--
  Created by IntelliJ IDEA.
  User: 30647
  Date: 2022/2/13
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpServletRequest req = (HttpServletRequest) request;
    int uId = (int) req.getSession().getAttribute("uId");
    DBconnection db = new DBconnection();
    List<CartGoods> list = null;
    cartDB dao = new cartDB();
    try {
        Connection conn = db.getConnection();
        list = dao.getCart(conn, uId);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>


<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>购物车</title>
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
<div class="jumbotron">
    <div class="container">
        <h1>购物车</h1>
    </div>
</div>
<div class="container theme-showcase" role="main">
    <div class="page-header">
    </div>
    <div class="container-fluid">
        <table class="table">
            <thead>
            <tr>
                <th>商品名称</th>
                <th>商品数量</th>
                <th>商品价格</th>
                <th>应付金额</th>
            </tr>
            </thead>
            <form id="Form1" method="get" action="">
                <tbody>
                <%
                    for (CartGoods cg : list) {
                        goodsDB goods = new goodsDB();
                        Goods good = null;
                        good = goods.getGoods(cg.getGId());
                %>
                <input class="sr-only" value="<%=cg.getGId()%>" name="gId">
                <input class="sr-only" value="<%=cg.getGNum()%>" name="number">
                <tr>
                    <td><%=good.getGName()%>
                    </td>
                    <td><%=cg.getGNum()%>
                    </td>
                    <td><%=good.getGPrice()%>
                    </td>
                    <td><%=cg.getGNum() * good.getGPrice()%>
                    </td>
                    <td>
                        <button type="button" class="btn btn-sm btn-success" onclick="buy()">购买</button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-sm btn-danger" onclick="del()">删除</button>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </form>
        </table>
    </div>
</div>
</div>

<hr>
<br>
<br>
<br>
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

    function del() {
        var m = "您真的确定要删除吗？";
        if (confirm(m) == true) {
            document.getElementById("Form1").action = "delServlet"
            document.getElementById("Form1").submit();
        }
    }
</script>

</body>
</html>
