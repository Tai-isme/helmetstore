package database;

import model.DonHang;
import java.sql.*;
import java.util.ArrayList;
import utils.JDBCUtil;

public class DonHangDAO implements DAOInterface<DonHang> {

    @Override
    public ArrayList<DonHang> selectAll() {
        ArrayList<DonHang> list = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM donhang";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String madonhang = rs.getString("madonhang");
                String makhachhang = rs.getString("makhachhang");
                String masanpham = rs.getString("masanpham");
                list.add(new DonHang(madonhang, makhachhang, masanpham));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public DonHang selectById(DonHang t) {
        DonHang dh = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM donhang WHERE madonhang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getMadonhang());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String madonhang = rs.getString("madonhang");
                String makhachhang = rs.getString("makhachhang");
                String masanpham = rs.getString("masanpham");
                dh = new DonHang(madonhang, makhachhang, masanpham);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dh;
    }

    @Override
    public int insert(DonHang dh) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = " INSERT INTO donhang (madonhang, makhachhang, ngaydathang, ngaygiaohangdukien, trangthaidonhang, phuongthucthanhtoan, diachigiaohang, idmagiamgia, tienvanchuyen, vat, tongtien) \n"
                    + "VALUES (?, ?,?,?,?,?,?,?,?,?,?); ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dh.getMadonhang());
            ps.setString(2, dh.getMakhachhang());
            ps.setString(3, dh.getNgaydathang());
            ps.setString(4, dh.getNgaygiaohangdukien());
            ps.setString(5, dh.getTrangthaidonhang());
            ps.setString(6, dh.getPhuongthucthanhtoan());
            ps.setString(7, dh.getDiachigiaohang());
            ps.setString(8, dh.getIdmagiamgia());
            ps.setDouble(9, dh.getTienvanchuyen());
            ps.setDouble(10, dh.getVat());
            ps.setDouble(11, dh.getTongtien());
            result = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insertAll(ArrayList<DonHang> list) {
        int count = 0;
        for (DonHang dh : list) {
            count += insert(dh);
        }
        return count;
    }

    @Override
    public int delete(DonHang dh) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM donhang WHERE madonhang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dh.getMadonhang());
            result = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteAll(ArrayList<DonHang> list) {
        int count = 0;
        for (DonHang dh : list) {
            count += delete(dh);
        }
        return count;
    }

    @Override
    public int update(DonHang dh) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE donhang SET makhachhang = ?, masanpham = ? WHERE madonhang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dh.getMakhachhang());
            ps.setString(3, dh.getMadonhang());
            result = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(String madonhang) {
        int result = 0;

        try {
            Connection connection = JDBCUtil.getConnection();

            String sql = " delete donhang\n"
                    + " where madonhang = ? ";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, madonhang);
            
            result = ps.executeUpdate();
                
            connection.close();
        } catch (Exception e) {
            System.out.println("Lỗi ở delete Donhang");
            e.printStackTrace();
        }

        return result;

    }
    
    public DonHang selectById(String madonhang) {
        DonHang dh = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM donhang WHERE madonhang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, madonhang);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                dh = new DonHang();
                dh.setMadonhang(rs.getString("madonhang"));
                dh.setNgaydathang(rs.getString("ngaydathang"));
                dh.setTrangthaidonhang(rs.getString("trangthaidonhang"));
                dh.setDiachigiaohang(rs.getString("diachigiaohang"));
                dh.setTongtien(rs.getDouble("tongtien"));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dh;
    }
}
