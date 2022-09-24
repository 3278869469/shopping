package Model.Bean;

public class User {
    private int uId, cId;
    private String uName, uAddress, phone, password;

    public User() {
    }

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
//        this.uId = "";
//        this.cId = cId;
        this.uName = "";
        this.uAddress = "";
    }

    public User(String phone) {
        this.phone = phone;
        this.uName = "";
        this.uAddress = "";
        this.password = "";
    }

    public User(int uId) {
        this.uId = uId;
        this.uName = "";
        this.uAddress = "";
        this.password = "";
    }

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    public int getCId() {
        return cId;
    }

    public void setCId(int cId) {
        this.cId = cId;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getUAddress() {
        return uAddress;
    }

    public void setUAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
