/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class DonHang {

    private String madonhang;
    private String makhachhang;
    private String ngaydathang;
    private String ngaygiaohangdukien;
    private String trangthaidonhang;
    private String phuongthucthanhtoan;
    private String diachigiaohang;
    private String idmagiamgia;
    private Double tienvanchuyen;
    private Double vat;
    private Double tongtien;
    private KhachHang khachhang;

    public KhachHang getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(KhachHang khachhang) {
        this.khachhang = khachhang;
    }
    
    public DonHang() {
    }

    public DonHang(String madonhang, String makhachhang, String masanpham) {
        this.madonhang = madonhang;
        this.makhachhang = makhachhang;
        
    }
    
    public DonHang(String madonhang, String makhachhang) {
        this.madonhang = madonhang;
        this.makhachhang = makhachhang;
     
    }

    public String getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(String madonhang) {
        this.madonhang = madonhang;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getNgaydathang() {
        return ngaydathang;
    }

    public void setNgaydathang(String ngaydathang) {
        this.ngaydathang = ngaydathang;
    }

    public String getNgaygiaohangdukien() {
        return ngaygiaohangdukien;
    }

    public void setNgaygiaohangdukien(String ngaygiaohangdukien) {
        this.ngaygiaohangdukien = ngaygiaohangdukien;
    }

    public String getTrangthaidonhang() {
        return trangthaidonhang;
    }

    public void setTrangthaidonhang(String trangthaidonhang) {
        this.trangthaidonhang = trangthaidonhang;
    }

    public String getPhuongthucthanhtoan() {
        return phuongthucthanhtoan;
    }

    public void setPhuongthucthanhtoan(String phuongthucthanhtoan) {
        this.phuongthucthanhtoan = phuongthucthanhtoan;
    }

    public String getDiachigiaohang() {
        return diachigiaohang;
    }

    public void setDiachigiaohang(String diachigiaohang) {
        this.diachigiaohang = diachigiaohang;
    }

    public String getIdmagiamgia() {
        return idmagiamgia;
    }

    public void setIdmagiamgia(String idmagiamgia) {
        this.idmagiamgia = idmagiamgia;
    }

    public Double getTienvanchuyen() {
        return tienvanchuyen;
    }

    public void setTienvanchuyen(Double tienvanchuyen) {
        this.tienvanchuyen = tienvanchuyen;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }
}