package Model.Bean;

import com.mysql.cj.x.protobuf.MysqlxCrud;

public class OrdersGoods {
    private int ogId, oId, gId, gNum, uId;
    private String oTime;

    public OrdersGoods() {
    }

    public OrdersGoods(int gId, int gNum) {
        this.gId = gId;
        this.gNum = gNum;
    }

    public int getOgId() {
        return ogId;
    }

    public void setOgId(int ogId) {
        this.ogId = ogId;
    }

    public int getOId() {
        return oId;
    }

    public void setOId(int oId) {
        this.oId = oId;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public int getGNum() {
        return gNum;
    }

    public void setGNum(int gNum) {
        this.gNum = gNum;
    }

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    public String getOTime() {
        return oTime;
    }

    public void setOTime(String oTime) {
        this.oTime = oTime;
    }


}

