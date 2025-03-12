package model;

public class GioHang {
    private String makhachhang;
    private String masanpham;
    private String size;
    private int soluong;

    public GioHang() {
    }

    public GioHang(String makhachhang, String masanpham, String size, int soluong) {
        this.makhachhang = makhachhang;
        this.masanpham = masanpham;
        this.size = size;
        this.soluong = soluong;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "GioHang{" + 
               "makhachhang='" + makhachhang + '\'' +
               ", masanpham='" + masanpham + '\'' +
               ", size='" + size + '\'' +
               ", soluong=" + soluong +
               '}';
    }
}
