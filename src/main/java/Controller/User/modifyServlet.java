package Controller.User;

import Model.Bean.User;
import Model.DB.DBconnection;
import Model.DB.userDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "modifyServlet", value = "/modifyServlet")
public class modifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPhone = (String) request.getAttribute("userPhone");
        String msg = "";
        DBconnection db = new DBconnection();
        User user = new User(userPhone);
        userDB dao = new userDB();
        try {
            Connection conn = db.getConnection();
            user.setUName("");
            user.setUAddress("");
            dao.modifyUser(conn,user);

            msg = "修改成功";
            request.setAttribute("msg",msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
