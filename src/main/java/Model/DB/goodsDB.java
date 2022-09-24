package Model.DB;

import Model.Bean.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class goodsDB {
    /*
    获取所有商品
     */
    public List<Goods> getAllGoods() {
        List<Goods> list = new ArrayList<>();
        Goods good = null;
        DBconnection util = new DBconnection();
        Connection conn = util.getConnection();
        String sql = "select * from goods";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                good = new Goods();
                good.setGId(rs.getInt("gId"));
                good.setGName(rs.getString("gName"));
                good.setGDetail(rs.getString("gDetail"));
                good.setGPrice(rs.getInt("gPrice"));
                good.setGPicture(rs.getString("gPicture"));
                good.setGCategory(rs.getString("gCategory"));
                good.setGSales(rs.getInt("gSales"));
                list.add(good);
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
    获取按销量排序的商品
     */
    public List<Goods> getOrderGoods() {
        List<Goods> list = new ArrayList<>();
        Goods good = null;
        DBconnection util = new DBconnection();
        Connection conn = util.getConnection();
        String sql = "select * from goods order by gSales DESC";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                good = new Goods();
                good.setGId(rs.getInt("gId"));
                good.setGName(rs.getString("gName"));
                good.setGDetail(rs.getString("gDetail"));
                good.setGPrice(rs.getInt("gPrice"));
                good.setGPicture(rs.getString("gPicture"));
                good.setGCategory(rs.getString("gCategory"));
                good.setGSales(rs.getInt("gSales"));
                list.add(good);
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
    获取特定商品
     */
    public  Goods getGoods(int gId) {
        Goods good = null;
        DBconnection util = new DBconnection();
        Connection conn = util.getConnection();
        String sql = "select * from goods where gId=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,gId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                good = new Goods();
                good.setGId(rs.getInt("gId"));
                good.setGName(rs.getString("gName"));
                good.setGDetail(rs.getString("gDetail"));
                good.setGPrice(rs.getInt("gPrice"));
                good.setGPicture(rs.getString("gPicture"));
                good.setGCategory(rs.getString("gCategory"));
                good.setGSales(rs.getInt("gSales"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.closeConn();
        }
        return good;
    }

    /*
    模糊搜索
     */
    public List<Goods> searchGoods(String s) {
        List<Goods> list = new ArrayList<>();
        Goods good = null;
        DBconnection util = new DBconnection();
        Connection conn = util.getConnection();
        String sql = "select * from goods where gName like \"%\"?\"%\"";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,s);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                good = new Goods();
                good.setGId(rs.getInt("gId"));
                good.setGName(rs.getString("gName"));
                good.setGDetail(rs.getString("gDetail"));
                good.setGPrice(rs.getInt("gPrice"));
                good.setGPicture(rs.getString("gPicture"));
                good.setGCategory(rs.getString("gCategory"));
                good.setGSales(rs.getInt("gSales"));
                list.add(good);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.closeConn();
        }
        return list;
    }
}
