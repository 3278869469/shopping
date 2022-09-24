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

@WebServlet(name = "fixpasswordServlet", value = "/fixpasswordServlet")
public class fixpasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String phone = (String) req.getSession().getAttribute("userPhone");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String newPassword_ = request.getParameter("newPassword_");
        System.out.println("password" + newPassword);
        System.out.println("password_" + newPassword_);

        String msg = "";
        DBconnection db = new DBconnection();
        User user = new User(phone, password);
        userDB dao = new userDB();
        if (newPassword.equals(newPassword_)) {
            try {
                Connection conn = db.getConnection();
                if (dao.modifyPassword(conn, user, newPassword)) {
                    msg = "修改成功";

                } else {
                    msg = "修改失败";
                }
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/fixpassword.jsp").forward(request, response);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            msg = "两个密码不一致";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/fixpassword.jsp").forward(request, response);
        }
    }
}
