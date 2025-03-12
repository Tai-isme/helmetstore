package model;

import java.io.Serializable;

public class SanPham implements Serializable {

    private String masanpham;
    private String tensanpham;
    private String hinhanhsanpham;
    private String mausac;
    private String kichco;
    private int soluong;
    private String kieumau;
    private double gianhap;
    private double giaban;
    private Integer giamgia;
    private String mota;
    private String gianhapformated;
    private String giabanformated;

    // Constructor không tham số
    public SanPham() {
    }

    public SanPham(String maSanPham, String size, int soLuong) {
        this.masanpham = maSanPham;
        this.kichco = size;
        this.soluong = soLuong;

    }
    

    public SanPham(String maSanPham, String size, int soLuong, double giaTien) {
        this.masanpham = maSanPham;
        this.kichco = size;
        this.soluong = soLuong;
        this.giaban = giaTien;
    }

    // Constructor đầy đủ tham số
    public SanPham(String masanpham, String tensanpham, String hinhanhsanpham,
            String mausac, String kichco, int soluong, String kieumau,
            Double gianhap, Double giaban, int giamgia, String mota) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.hinhanhsanpham = hinhanhsanpham;
        this.mausac = mausac;
        this.kichco = kichco;
        this.soluong = soluong;
        this.kieumau = kieumau;
        this.gianhap = gianhap;
        this.giaban = giaban;
        this.giamgia = giamgia;
        this.mota = mota;
    }

    public SanPham(String masanpham, String tensanpham, String hinhanhsanpham,
            String mausac, String kieumau,
            Double gianhap, Double giaban, int giamgia, String mota) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.hinhanhsanpham = hinhanhsanpham;
        this.mausac = mausac;
        this.kieumau = kieumau;
        this.gianhap = gianhap;
        this.giaban = giaban;
        this.giamgia = giamgia;
        this.mota = mota;
    }

    public SanPham(String masanpham, String tensanpham, String hinhanhsanpham,
            String mausac, String kieumau,
            Double giaban, int giamgia) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.hinhanhsanpham = hinhanhsanpham;
        this.mausac = mausac;
        this.kieumau = kieumau;

        this.giaban = giaban;
        this.giamgia = giamgia;

    }

    // Getter & Setter
    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public String getKichco() {
        return kichco;
    }

    public void setKichco(String kichco) {
        this.kichco = kichco;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getKieumau() {
        return kieumau;
    }

    public void setKieumau(String kieumau) {
        this.kieumau = kieumau;
    }
    
    

    public Double getGianhap() {
        return gianhap;
    }

    public void setGianhap(Double gianhap) {
        this.gianhap = gianhap;
    }

    public Double getGiaban() {
        return giaban;
    }

    public void setGiaban(Double giaban) {
        this.giaban = giaban;
    }

    public int getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(int giamgia) {
        this.giamgia = giamgia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getGianhapformated() {
        return gianhapformated;
    }

    public void setGianhapformated(String gianhapformated) {
        this.gianhapformated = gianhapformated;
    }

    public String getGiabanformated() {
        return giabanformated;
    }

    public void setGiabanformated(String giabanformated) {
        this.giabanformated = giabanformated;
    }
    
    

    @Override
    public String toString() {
        return "SanPham{" + "masanpham=" + masanpham + ", tensanpham=" + tensanpham + ", hinhanhsanpham=" + hinhanhsanpham + ", mausac=" + mausac + ", kichco=" + kichco + ", soluong=" + soluong + ", kieumau=" + kieumau + ", gianhap=" + gianhap + ", giaban=" + giaban + ", giamgia=" + giamgia + ", mota=" + mota + '}';
    }

}
