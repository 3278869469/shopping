package Model.Bean;

public class Orders {
    private int oId, uId;
    private String otime;

    public Orders() {
    }

    public Orders(int uId, String otime) {
        this.uId = uId;
        this.otime = otime;
    }

    public int getOId() {
        return oId;
    }

    public int getUId() {
        return uId;
    }

    public String getOtime() {
        return otime;
    }

    public void setOId(int oId) {
        this.oId = oId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

}
