package id.rezayds.tedi.model;

public class Users {

    private int userId;
    private String nama;
    private String ava;

    public Users() {}

    public Users(String nama, String ava) {
        this.nama = nama;
        this.ava = ava;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }


}
