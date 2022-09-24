package Model.DB;

import Model.Bean.User;

import java.sql.*;

public class userDB {
    /*
     查询用户是否已经注册
     */
    public static User Exist(Connection con, User user) throws SQLException {
        User rUser = null;
        String sql = "select * from user where phone=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, user.getPhone());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            rUser = new User();
            rUser.setPhone(rs.getString("phone"));
            rUser.setPassword(rs.getString("password"));
            rUser.setUId(rs.getInt("uId"));
            rUser.setCId(rs.getInt("cId"));
            rUser.setUName(rs.getString("uName"));
            rUser.setUAddress(rs.getString("uAddress"));

            rs.close();
        }
        return rUser;
    }

    /*
    注册
     */
    public static boolean Register(Connection con, User user) throws SQLException {
        boolean flag = false;
        int uid, cid;
        uid = getNextUId(con);
        cid = uid;
        PreparedStatement pst = null;
        String sql = "insert into user(uId,phone,password,cId) values(?,?,?,?)";
        pst = con.prepareStatement(sql);
        pst.setInt(1, uid);
        pst.setInt(4, cid);
        pst.setString(2, user.getPhone());
        pst.setString(3, user.getPassword());
        if (pst.executeUpdate() > 0) {
            flag = true;
        }
        return flag;
    }

    /*
    获取下一个uId
     */
    public static int getNextUId(Connection con) throws SQLException {
        int uid = 1;
        String sql = "select max(uId) from user";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            uid = (rs.getInt("max(uId)")) + 1;
            rs.close();
        }
        return uid;
    }

    /*
     登录：效验手机号和密码
     */
    public static User Login(Connection con, User user) throws SQLException {
        User rUser = null;
        String sql = "select * from user where phone=? and password=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, user.getPhone());
        pst.setString(2, user.getPassword());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            rUser = new User();
            rUser.setPhone(rs.getString("phone"));
            rUser.setPassword(rs.getString("password"));
            rUser.setUId(rs.getInt("uId"));
            rUser.setCId(rs.getInt("cId"));
            rUser.setUName(rs.getString("uName"));
            rUser.setUAddress(rs.getString("uAddress"));

            rs.close();
        }
        return rUser;
    }

    /*
    修改资料
     */
    public static boolean modifyUser(Connection con, User user) throws SQLException {
        boolean flag = false;
        String sql = "update user set uName=?,uAddress=? where uId=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, user.getUName());
        pst.setString(2, user.getUAddress());
        pst.setInt(3, user.getUId());
        if (pst.executeUpdate() > 0)
            flag = true;
        return flag;
    }

    /*
    修改密码
     */
    public static boolean modifyPassword(Connection con, User user, String newPassword) throws SQLException {
        boolean flag = false;
        //效验原密码
        if (Login(con, user) != null) {
            //更新新密码
            String sql = "update user set password=? where phone=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, newPassword);
            pst.setString(2, user.getPhone());
            if (pst.executeUpdate() > 0)
                flag = true;
        }
        return flag;
    }


}
