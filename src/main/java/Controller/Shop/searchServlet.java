package Controller.Shop;

import Model.Bean.Goods;
import Model.DB.goodsDB;
import com.mysql.cj.util.DnsSrv;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "searchServlet", value = "/searchServlet")
public class searchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("search");
        goodsDB goodsDB = new goodsDB();
        List<Goods> list = goodsDB.searchGoods(s);
        PrintWriter out = response.getWriter();
        try {
            out.println("<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <link rel=\"icon\" href=\"./icon/购物车.png\">\n" +
                    "\n" +
                    "    <title>商品</title>\n" +
                    "\n" +
                    "    <!-- Bootstrap core CSS -->\n" +
                    "    <link href=\"https://fastly.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.30/dist/css/bootstrap.min.css\"\n" +
                    "          rel=\"stylesheet\">\n" +
                    "\n" +
                    "</head>\n" +
                    "<!-- NAVBAR\n" +
                    "================================================== -->\n" +
                    "<body>\n" +
                    "<nav class=\"navbar navbar-inverse navbar-fixed-top\">\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"navbar-header\">\n" +
                    "            <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar\"\n" +
                    "                    aria-expanded=\"false\" aria-controls=\"navbar\">\n" +
                    "                <span class=\"sr-only\">Toggle navigation</span>\n" +
                    "                <span class=\"icon-bar\"></span>\n" +
                    "                <span class=\"icon-bar\"></span>\n" +
                    "                <span class=\"icon-bar\"></span>\n" +
                    "            </button>\n" +
                    "            <a class=\"navbar-brand\" href=\"/demo_war_exploded/\">网上购物子系统</a>\n" +
                    "        </div>\n" +
                    "        <div id=\"navbar\" class=\"navbar-collapse collapse\">\n" +
                    "            <ul class=\"nav navbar-nav\">\n" +
                    "                <li><a href=\"/demo_war_exploded/\">主页</a></li>\n" +
                    "                <li><a href=\"/demo_war_exploded/goods.jsp\">商品列表</a></li>\n" +
                    "                <li><a href=\"/demo_war_exploded/cart.jsp\">购物车</a></li>\n" +
                    "                <li><a href=\"/demo_war_exploded/orders.jsp\">我的订单</a></li>\n" +
                    "                <li><a href=\"/demo_war_exploded/me.jsp\">个人中心</a></li>\n" +
                    "            </ul>\n" +
                    "            <form class=\"navbar-form navbar-right\" action=\"searchServlet\" method=\"get\">\n" +
                    "                <div class=\"form-group\">\n" +
                    "                    <input type=\"text\" placeholder=\"输入关键字\" class=\"form-control\" name=\"search\">\n" +
                    "                </div>\n" +
                    "                <button type=\"submit\" class=\"btn btn-success\">搜索</button>\n" +
                    "            </form>\n" +
                    "        </div><!--/.navbar-collapse -->\n" +
                    "    </div>\n" +
                    "</nav>\n" +
                    "\n" +
                    "<div class=\"jumbotron\">\n" +
                    "    <div class=\"container\">\n" +
                    "        <h1>搜索结果</h1>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<!-- Marketing messaging and featurettes\n" +
                    "================================================== -->\n" +
                    "<!-- Wrap the rest of the page in another container to center all the content. -->\n" +
                    "\n" +
                    "<div class=\"container marketing\">\n" +
                    "\n" +
                    "    <div class=\"container marketing\">\n" +
                    "\n" +
                    "        <!-- Three columns of text below the carousel -->\n" +
                    "        <div class=\"row\">");

            for (int i = 0; i < list.size(); i++) {
                Goods good = list.get(i);

                out.println("<div class=\"col-lg-4\">\n" +
                        "                <img class=\"img-responsive\"\n" +
                        "                     src=\"" +
                        good.getGPicture() + "\"\n" +
                        "                     alt=\"Generic placeholder image\" width=\"210\" height=\"210\">\n" +
                        "                <h2>" +
                        good.getGName() + "\n" +
                        "                </h2>\n" +
                        "                <p>" +
                        good.getGDetail() + "\n" +
                        "                </p>\n" +
                        "                <p><a class=\"btn btn-default\" href=\"/demo_war_exploded/goodDetails.jsp?gId=" +
                        good.getGId() + "\" role=\"button\">View details\n" +
                        "                    &raquo;</a></p>\n" +
                        "                <input class=\"sr-only\" name=\"goodId\" value=\"" +
                        good.getGId() + "\">\n" +
                        "            </div><!-- /.col-lg-4 -->");
            }
            out.println("</div><!-- /.row -->\n" +
                    "\n" +
                    "\n" +
                    "    </div><!-- /.container -->\n" +
                    "\n" +
                    "\n" +
                    "    <hr>\n" +
                    "    <br>\n" +
                    "    <br>\n" +
                    "    <br>\n" +
                    "\n" +
                    "</div><!-- /.container -->\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");

            out.close();

        } finally {
            out.close();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
