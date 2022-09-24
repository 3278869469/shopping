package Controller.Shop;

import Model.Bean.CartGoods;
import Model.Bean.User;
import Model.DB.DBconnection;
import Model.DB.cartDB;
import Model.DB.userDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "addCartServlet", value = "/addCartServlet")
public class addCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        int gId = Integer.parseInt(request.getParameter("gId"));
        int uId = (int) req.getSession().getAttribute("uId");
        int number = Integer.parseInt(request.getParameter("number"));
        String msg = "";
        if (number < 1) {
            msg = "购买数量必须大于1";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/goodDetails.jsp").forward(request, response);
        } else {
            CartGoods cartgoods = new CartGoods(uId, gId, number);
            DBconnection db = new DBconnection();
            cartDB dao = new cartDB();
            try {
                Connection conn = db.getConnection();
                if (dao.addCart(conn, cartgoods)) {
                    msg = "成功添加入购物车";
                } else {
                    msg = "加入购物车失败";
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
