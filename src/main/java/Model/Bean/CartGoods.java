package Model.Bean;

public class CartGoods {
    private int cgId, cId, gId, gNum;

    public CartGoods() {
    }

    public CartGoods(int cId, int gId, int gNum) {
        this.cId = cId;
        this.gId = gId;
        this.gNum = gNum;
    }

    public int getCgId() {
        return cgId;
    }

    public void setCgId(int cgId) {
        this.cgId = cgId;
    }

    public int getCId() {
        return cId;
    }

    public void setCId(int cId) {
        this.cId = cId;
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
}
