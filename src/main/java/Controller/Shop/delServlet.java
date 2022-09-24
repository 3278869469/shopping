package Controller.Shop;

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

@WebServlet(name = "delServlet", value = "/delServlet")
public class delServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int gId = Integer.parseInt(request.getParameter("gId"));
        DBconnection db = new DBconnection();
        cartDB dao = new cartDB();
        String msg = "";
        try {
            Connection conn = db.getConnection();
            if (dao.delCart(conn,gId)) {
                msg = "删除成功";
            } else {
                msg = "删除失败";
            }
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
