
package model;

import java.time.LocalDateTime;
import java.util.List;


public class OrderHistory {
    private String madonhang;
    private LocalDateTime ngaydathang;
    private String trangthaidonhang;
    private List<SanPham> listsanpham;
    private Double tongtien;

    public OrderHistory() {
    }

    public String getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(String madonhang) {
        this.madonhang = madonhang;
    }

    public LocalDateTime getNgaydathang() {
        return ngaydathang;
    }

    public void setNgaydathang(LocalDateTime ngaydathang) {
        this.ngaydathang = ngaydathang;
    }

    public String getTrangthaidonhang() {
        return trangthaidonhang;
    }

    public void setTrangthaidonhang(String trangthaidonhang) {
        this.trangthaidonhang = trangthaidonhang;
    }

    public List<SanPham> getListsanpham() {
        return listsanpham;
    }

    public void setListsanpham(List<SanPham> listsanpham) {
        this.listsanpham = listsanpham;
    }

    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }
    
    
    
    
    
    
    
}
