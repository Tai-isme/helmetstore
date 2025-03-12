/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.OrderHistory;
import model.SanPham;
import utils.JDBCUtil;

/**
 *
 * @author Thien
 */
public class OrderHistoryDAO implements DAOInterface<OrderHistory> {

    @Override
    public ArrayList<OrderHistory> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderHistory selectById(OrderHistory t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(OrderHistory t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertAll(ArrayList<OrderHistory> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(OrderHistory t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteAll(ArrayList<OrderHistory> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(OrderHistory t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<OrderHistory> selectByIdMaKhachHang(String makhachhang) {
        List<OrderHistory> list = new ArrayList<OrderHistory>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "Select distinct d.madonhang, d.ngaydathang, d.trangthaidonhang, d.tongtien \n"
                    + " from donhang d join chitietdonhang c on d.madonhang = c.madonhang\n"
                    + " join sanpham s on c.masanpham = s.masanpham join categories ca on ca.categoryID = s.categoryID\n"
                    + " where d.makhachhang = ? "
                    + "Order by d.ngaydathang desc ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, makhachhang);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String madonhang = rs.getString(1);
                LocalDateTime ngaydathang = rs.getObject(2, LocalDateTime.class);
                String trangthaidonhang = rs.getString(3);
                Double tongtien = rs.getDouble(4);

                List<SanPham> listsanpham = selectSanPhamCuaMotDonHang(madonhang);
                
                OrderHistory oh = new OrderHistory();
                oh.setMadonhang(madonhang);
                oh.setNgaydathang(ngaydathang);
                oh.setTrangthaidonhang(trangthaidonhang);
                oh.setTongtien(tongtien);
                oh.setListsanpham(listsanpham);
                
                list.add(oh);
                
            }

        } catch (Exception e) {
            System.out.println("Lỗi ở selectByIdMaKhachHang");
            e.printStackTrace();
        }
        return list;

    }

    public List<SanPham> selectSanPhamCuaMotDonHang(String madonhang) {
        List<SanPham> listsanpham = new ArrayList<SanPham>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "select s.hinhanhsanpham,  c.masanpham,ct.categoryName,  c.size, c.soluongsanphammua, c.giaban\n"
                    + " from chitietdonhang c join donhang d on c.madonhang = d.madonhang\n"
                    + " join  sanpham s on c.masanpham = s.masanpham\n"
                    + " join categories ct on ct.categoryID = s.categoryID \n"
                    + " where d.madonhang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, madonhang);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String hinhanhsanpham = rs.getString(1);
                String masanpham = rs.getString(2);
                String categoryName = rs.getString(3);
                String size = rs.getString(4);
                int soluongsanphammua = rs.getInt(5);
                Double giaban = rs.getDouble(6);
                
                SanPham sp = new SanPham();
                sp.setHinhanhsanpham(hinhanhsanpham);
                sp.setMasanpham(masanpham);
                sp.setKieumau(categoryName);
                sp.setKichco(size);
                sp.setSoluong(soluongsanphammua);
                sp.setGiaban(giaban);
                
                listsanpham.add(sp);
                
            }

        } catch (Exception e) {
            System.out.println("Lỗi ở selectSanPhamCuaMotDonHang");
            e.printStackTrace();
        }

        return listsanpham;
    }

}
