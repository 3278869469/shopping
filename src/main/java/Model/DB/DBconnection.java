package Model.DB;

import java.sql.*;

public class DBconnection {
    static String url = "jdbc:mysql://127.0.0.1:3306/demo";
    static String user = "root";
    static String password = "123456";
    static Connection con;

    public static Connection getConnection() {
        //1.加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2.数据库连接
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("数据库连接成功");
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }
        return con;
    }

    public static void closeConn() {
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
