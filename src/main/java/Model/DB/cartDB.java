package Model.DB;

import Model.Bean.CartGoods;
import Model.Bean.Goods;
import Model.Bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class cartDB {
    /*
    当前物品是否已经存在与购物车
     */
    public CartGoods Eixt(Connection con, CartGoods cg) throws SQLException {
        CartGoods flag = null;
        String sql = "select * from cartgoods where cId=? and gId=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, cg.getCId());
        pst.setInt(2, cg.getGId());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            flag = new CartGoods();
            flag.setGNum(rs.getInt("gNum"));
            rs.close();
        }
        return flag;
    }

    /*
    加入购物车
     */
    public boolean addCart(Connection con, CartGoods cg) throws SQLException {
        boolean flag = false;
        CartGoods eixt = Eixt(con, cg);
        //如果商品已经存在于购物车中
        if (eixt != null) {
            //商品数量累加
            int num = eixt.getGNum() + cg.getGNum();
            //更新商品数量
            String sql = "update cartgoods set gNum=? where cId=? and gId=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, num);
            pst.setInt(2, cg.getCId());
            pst.setInt(3, cg.getGId());
            if (pst.executeUpdate() > 0)
                flag = true;
        //如果商品还没存在
        } else {
            int cgId;
            cgId = getNextCgId(con);
            PreparedStatement pst = null;
            //购物车数据库新增数据
            String sql = "insert into cartgoods(cgId,cId,gId,gNum) values(?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, cgId);
            pst.setInt(2, cg.getCId());
            pst.setInt(3, cg.getGId());
            pst.setInt(4, cg.getGNum());
            if (pst.executeUpdate() > 0) {
                flag = true;
            }
        }
        return flag;
    }

    /*
    获取购物车里某个商品的数量
     */
    public int getNum(Connection con, CartGoods cg) throws SQLException {
        int num = 0;
        String sql = "select gNum from cartgoods where cId=? and gId=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, cg.getCId());
        pst.setInt(2, cg.getGId());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            num = rs.getInt("gNum");
            rs.close();
        }
        return num;
    }

    /*
    获取下一个cgId
    用于新加数据的id
     */
    public int getNextCgId(Connection con) throws SQLException {
        int cgId = 1;
        String sql = "select max(cgId) from cartgoods";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            cgId = (rs.getInt("max(cgId)")) + 1;
            rs.close();
        }
        return cgId;
    }

    /*
    获取当前用户的购物车
     */
    public List<CartGoods> getCart(Connection con, int id) {
        List<CartGoods> list = new ArrayList<>();
        CartGoods cart = null;
        DBconnection util = new DBconnection();
        Connection conn = util.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from cartgoods where cId=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cart = new CartGoods();
                cart.setCgId(rs.getInt("cgId"));
                cart.setGId(rs.getInt("gId"));
                cart.setCId(rs.getInt("cId"));
                cart.setGNum(rs.getInt("gNum"));
                list.add(cart);
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
    删除购物车里某条商品信息
     */
    public boolean delCart(Connection con, int id) throws SQLException {
        boolean flag = false;
        PreparedStatement pst = null;
        String sql = "DELETE FROM cartgoods WHERE gId=?;";
        pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        if (pst.executeUpdate() > 0) {
            flag = true;
        }
        return flag;
    }
}
