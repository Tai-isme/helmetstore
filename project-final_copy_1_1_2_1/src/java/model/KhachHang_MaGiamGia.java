
package model;

public class KhachHang_MaGiamGia {
    private String maKhachHang;
    private String idMaGiamGia;
    private int soluongnguoidungdangco;

    public KhachHang_MaGiamGia(String maKhachHang, String idMaGiamGia) {
        this.maKhachHang = maKhachHang;
        this.idMaGiamGia = idMaGiamGia;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getIdMaGiamGia() {
        return idMaGiamGia;
    }

    public void setIdMaGiamGia(String idMaGiamGia) {
        this.idMaGiamGia = idMaGiamGia;
    }

    public int getSoluongnguoidungdangco() {
        return soluongnguoidungdangco;
    }

    public void setSoluongnguoidungdangco(int soluongnguoidungdangco) {
        this.soluongnguoidungdangco = soluongnguoidungdangco;
    }
    
    
    
    
}