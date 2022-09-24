package Controller.User;

import Model.Bean.User;
import Model.DB.DBconnection;
import Model.DB.userDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "fixmeServlet", value = "/fixmeServlet")
public class fixmeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uName = request.getParameter("uName");
        String uAddress = request.getParameter("uAddress");
        int uId = (int) req.getSession().getAttribute("uId");
        String msg = "";
        DBconnection db = new DBconnection();
        User user = new User(uId);
        user.setUName(uName);
        user.setUAddress(uAddress);
        userDB dao = new userDB();
        try {
            Connection conn = db.getConnection();
            if (dao.modifyUser(conn, user)) {
                msg = "修改成功";
            } else {
                msg = "修改失败";
            }
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/fixme.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
