package Model.Bean;

public class Goods {
    private int gId, gPrice, gSales;
    private String gName, gDetail, gPicture, gCategory;

    public Goods() {
    }
    public Goods(int gId) {
        this.gId = gId;
    }

    public int getGId() {
        return gId;
    }

    public int getGPrice() {
        return gPrice;
    }

    public int getGSales() {
        return gSales;
    }

    public String getGName() {
        return gName;
    }

    public String getGDetail() {
        return gDetail;
    }

    public String getGPicture() {
        return gPicture;
    }

    public String getGCategory() {
        return gCategory;
    }

    public void setGId(int gId) {
        this.gId =  gId;
    }

    public void setGPrice(int gPrice) {
        this.gPrice = gPrice;
    }

    public void setGSales(int gSales) {
        this.gSales = gSales;
    }

    public void setGName(String gName) {
        this.gName =gName;
    }

    public void setGDetail(String gDetail) {
        this.gDetail = gDetail;
    }

    public void setGPicture(String gPicture) {
        this.gPicture = gPicture;
    }

    public void setGCategory(String gCategory) {
        this.gCategory = gCategory;
    }
}
