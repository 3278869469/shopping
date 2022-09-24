package Controller.User;

import Model.Bean.User;
import Model.DB.DBconnection;
import Model.DB.userDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String userPhone = request.getParameter("inputPhone");
        String userPassWord = request.getParameter("inputPassword");
        String remember = request.getParameter("remenber");
        String msg = "";
        DBconnection db = new DBconnection();
        User user = new User(userPhone, userPassWord);
        userDB dao = new userDB();
        try {
            Connection conn = db.getConnection();
            user = dao.Login(conn, user);
            if (user != null) {
                msg = "登录成功";
                request.setAttribute("msg", msg);
                req.getSession().setAttribute("userPhone", userPhone);
                req.getSession().setAttribute("uId", user.getUId());
//                request.setAttribute("userPhone", userPhone);
//                System.out.println("userPhone:"+userPhone);
                //记住密码
                if ("on".equals(remember)) {
                    System.out.println("记住密码");
                    //储存账号、密码的cookie
                    Cookie cookie = null;
                    Cookie cookie2 = null;
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (Cookie value : cookies) {
                            if (value.getName().equals("userPhone")) {
                                cookie = value;
                            } else if (value.getName().equals("password")) {
                                cookie2 = value;
                            }
                        }
                    }
                    if (cookie == null) {
                        System.out.println("新建cookie");
                        //一个月过期
                        int maxAge = 60 * 60 * 24 * 30;
                        cookie = new Cookie("userPhone", userPhone);
                        //设置路径
                        cookie.setPath(request.getContextPath());
                        //设置过期时间
                        cookie.setMaxAge(maxAge);
                        //储存
                        response.addCookie(cookie);

                        cookie2 = new Cookie("password", userPassWord);
                        cookie2.setPath(request.getContextPath());
                        cookie2.setMaxAge(maxAge);
                        response.addCookie(cookie2);
                    }
                }
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                msg = "账号或密码错误";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/loginFail.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
