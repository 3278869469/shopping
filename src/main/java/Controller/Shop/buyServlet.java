package Controller.Shop;

import Model.Bean.CartGoods;
import Model.Bean.Orders;
import Model.Bean.OrdersGoods;
import Model.DB.DBconnection;
import Model.DB.buyDB;
import Model.DB.cartDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "buyServlet", value = "/buyServlet")
public class buyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        int gId = Integer.parseInt(request.getParameter("gId"));
        int uId = (int) req.getSession().getAttribute("uId");
        int number = Integer.parseInt(request.getParameter("number"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间
//        System.out.println("time" + time);
        String msg = "";
        if (number < 1) {
            msg = "购买数量必须大于1";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/goodDetails.jsp").forward(request, response);
        } else {
            OrdersGoods ordersgoods = new OrdersGoods(gId, number);
            Orders orders = new Orders(uId, time);
            DBconnection db = new DBconnection();
            buyDB dao = new buyDB();
            try {
                Connection conn = db.getConnection();
                if (dao.buy(conn, orders, ordersgoods)) {
                    msg = "购买成功";
                } else {
                    msg = "购买失败";
                }
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/goodDetails.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
