package Model.DB;

import Model.Bean.CartGoods;
import Model.Bean.Orders;
import Model.Bean.OrdersGoods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class buyDB {
    /*
    购买商品
     */
    public boolean buy(Connection con, Orders orders, OrdersGoods og) throws SQLException {
        boolean flag = false;
        int oId;
        oId = getNextOId(con);
        PreparedStatement pst = null;
        String sql = "insert into orders(oId,uId,oTime) values(?,?,?)";
        pst = con.prepareStatement(sql);
        pst.setInt(1, oId);
        pst.setInt(2, orders.getUId());
        pst.setString(3, orders.getOtime());
        if (pst.executeUpdate() > 0) {
            flag = true;
            buy2(con, og, oId);
        }
        return flag;


    }

    public boolean buy2(Connection con, OrdersGoods og, int oId) throws SQLException {
        boolean flag = false;
        int ogId;
        ogId = getNextOgId(con);
        PreparedStatement pst = null;
        String sql = "insert into ordersgoods(ogId,oId,gId,gNum) values(?,?,?,?)";
        pst = con.prepareStatement(sql);
        pst.setInt(1, ogId);
        pst.setInt(2, oId);
        pst.setInt(3, og.getGId());
        pst.setInt(4, og.getGNum());
        if (pst.executeUpdate() > 0) {
            flag = true;
        }
        return flag;
    }

    /*
   获取下一个oId
    */
    private int getNextOId(Connection con) throws SQLException {
        int oId = 1;
        String sql = "select max(oId) from orders";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            oId = (rs.getInt("max(oId)")) + 1;
            rs.close();
        }
        return oId;
    }


    /*
   获取下一个ogId
    */
    public int getNextOgId(Connection con) throws SQLException {
        int ogId = 1;
        String sql = "select max(ogId) from ordersgoods";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            ogId = (rs.getInt("max(ogId)")) + 1;
            rs.close();
        }
        return ogId;
    }

    /*
    获取当前用户的订单列表
     */
    public List<OrdersGoods> getOrders(Connection con, int id) {
        List<OrdersGoods> list = new ArrayList<>();
        OrdersGoods order = null;
        DBconnection util = new DBconnection();
        Connection conn = util.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from orders o,ordersgoods og where uId=? and o.oId=og.oId";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                order = new OrdersGoods();
                order.setOId(rs.getInt("oId"));
                order.setUId(rs.getInt("uId"));
                order.setOTime(rs.getString("oTime"));
                order.setOgId(rs.getInt("ogId"));
                order.setGId(rs.getInt("gId"));
                order.setGNum(rs.getInt("gNum"));
                list.add(order);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.closeConn();
        }
        return list;
    }

    /*
   获取订单详情
    */
    public OrdersGoods getOrder(int uId, int oId) {
        OrdersGoods order = null;
        DBconnection util = new DBconnection();
        Connection conn = util.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from orders o,ordersgoods og where uId=? and o.oId=? and o.oId=og.oId";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, uId);
            pst.setInt(2, oId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                order = new OrdersGoods();
                order.setOId(rs.getInt("oId"));
                order.setUId(rs.getInt("uId"));
                order.setOTime(rs.getString("oTime"));
                order.setOgId(rs.getInt("ogId"));
                order.setGId(rs.getInt("gId"));
                order.setGNum(rs.getInt("gNum"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.closeConn();
        }
        return order;
    }
}
