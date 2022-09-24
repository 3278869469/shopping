<%@ page import="Model.Bean.Goods" %>
<%@ page import="Model.Bean.OrdersGoods" %>
<%@ page import="Model.DB.buyDB" %>
<%@ page import="Model.DB.DBconnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="Model.DB.goodsDB" %>
<%@ page import="Model.Bean.User" %>
<%@ page import="Model.DB.userDB" %><%--
  Created by IntelliJ IDEA.
  User: 30647
  Date: 2022/2/16
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="goodsDB" class="Model.DB.goodsDB"></jsp:useBean>
<%
    HttpServletRequest req = (HttpServletRequest) request;
    int uId = (int) req.getSession().getAttribute("uId");
    String phone = (String) req.getSession().getAttribute("userPhone");
    User user = new User(phone);
    int oId = Integer.valueOf(request.getParameter("oId"));
    int gId = Integer.valueOf(request.getParameter("gId"));
    buyDB dao = new buyDB();
    OrdersGoods order = null;
    Goods good = null;
    DBconnection db = new DBconnection();
    try {
        Connection conn = db.getConnection();
        order = dao.getOrder(uId, oId);
        goodsDB goodsdb = new goodsDB();
        good = goodsdb.getGoods(gId);
        userDB userdb = new userDB();
        user = userdb.Exist(conn, user);
    } catch (Exception e) {
        e.printStackTrace();
    }

%>
<html>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="./icon/购物车.png">
    <!-- Bootstrap core CSS -->
    <link href="https://fastly.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.30/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <title>订单详情</title>
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

<div class="container">
    <div class="jumbotron">
        <h2>订单详情
        </h2>
    </div>

    <div class="row marketing">
        <div class="col-lg-6">
            <img class="img-responsive" src="<%=good.getGPicture()%>"
                 alt="Generic placeholder image" width="360" height="360">
        </div>
        <p>
        <div class="col-lg-6">
            <h2><%=good.getGName()%>
            </h2>
            <p>
            </p>
            <p>订单编号： <%=order.getOId()%>
            </p>
            <p>下单时间：<%=order.getOTime()%>
            </p>
            <p>收货人手机号：<%=user.getPhone()%>
            </p>
            <p>收货地址：<%=user.getUAddress()%>
            </p>
            <p>购买数量：<%=order.getGNum()%>
            </p>
            <p>支付金额：<%=order.getGNum() * good.getGPrice()%>
            </p>


            </p>
            <p>
        </div>
        </p>
    </div>

    <hr>
    <br>
    <br>
    <br>

</div> <!-- /container -->

</div>

</body>
</html>