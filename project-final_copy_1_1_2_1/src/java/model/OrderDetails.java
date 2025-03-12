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
public class OrderDetails {

    private DonHang donHang;
    private ChiTietDonHang chiTietDonHang;
    private SanPham sanPham;
    private Categories categories;

    public OrderDetails() {
    }

    public OrderDetails(DonHang donHang, ChiTietDonHang chiTietDonHang, SanPham sanPham, Categories categories) {
        this.donHang = donHang;
        this.chiTietDonHang = chiTietDonHang;
        this.sanPham = sanPham;
        this.categories = categories;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }

    public ChiTietDonHang getChiTietDonHang() {
        return chiTietDonHang;
    }

    public void setChiTietDonHang(ChiTietDonHang chiTietDonHang) {
        this.chiTietDonHang = chiTietDonHang;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

}