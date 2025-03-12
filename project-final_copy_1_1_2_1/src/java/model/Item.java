
package model;

import java.io.Serializable;


public class Item implements Serializable{
    private String masanpham;
    private SanPham sanpham;
    private int soluong;

    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }
    

 

    public SanPham getSanpham() {
        return sanpham;
    }

    public void setSanpham(SanPham sanpham) {
        this.sanpham = sanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "Item{" + "masanpham=" + masanpham + ", sanpham=" + sanpham + ", soluong=" + soluong + '}';
    }
    
    
    
    
    
    
}
