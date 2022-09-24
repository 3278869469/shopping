package Controller.User;

import Model.Bean.User;
import Model.DB.DBconnection;
import Model.DB.userDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "registerServlet", value = "/registerServlet")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPhone = request.getParameter("inputPhone");
        String userPassWord = request.getParameter("inputPassword");
        String msg="";
        DBconnection db = new DBconnection();
        User user = new User(userPhone,userPassWord);
        userDB dao = new userDB();
        try{
            Connection conn = db.getConnection();
            if("".equals(userPassWord) || "".equals(userPhone)){
                msg = "注册信息不能为空";
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("/register.jsp").forward(request,response);
            }else if(dao.Exist(conn,user) != null){
                msg = "该用户已被注册过";
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("/register.jsp").forward(request,response);
            }else{
                dao.Register(conn,user);
                System.out.println("注册成功");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
