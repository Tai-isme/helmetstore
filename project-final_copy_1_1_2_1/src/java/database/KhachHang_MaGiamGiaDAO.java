package database;

import model.KhachHang_MaGiamGia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.MaGiamGia;
import utils.JDBCUtil;

public class KhachHang_MaGiamGiaDAO implements DAOInterface<KhachHang_MaGiamGia> {

    @Override
    public ArrayList<KhachHang_MaGiamGia> selectAll() {
        ArrayList<KhachHang_MaGiamGia> list = new ArrayList<>();
        String sql = "SELECT * FROM khachhang_magiamgia";
        try (
                Connection conn = JDBCUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new KhachHang_MaGiamGia(rs.getString("maKhachHang"), rs.getString("idMaGiamGia")));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public KhachHang_MaGiamGia selectById(KhachHang_MaGiamGia t) {
        KhachHang_MaGiamGia khmg = null;
        String sql = "SELECT * FROM khachhang_magiamgia WHERE maKhachHang = ? AND idMaGiamGia = ?";
        try (Connection conn = JDBCUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getMaKhachHang());
            ps.setString(2, t.getIdMaGiamGia());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                khmg = new KhachHang_MaGiamGia(rs.getString("maKhachHang"), rs.getString("idMaGiamGia"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khmg;
    }

    @Override
    public int insert(KhachHang_MaGiamGia t) {
        String sql = "INSERT INTO khachhang_magiamgia (makhachHang, idmagiamgia, soluongnguoidungdangco) VALUES (?, ? ,?)";
        int ketQua = 0;
        try (
                Connection conn = JDBCUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getMaKhachHang());
            ps.setString(2, t.getIdMaGiamGia());
                        ps.setInt(3, 1);

            ketQua = ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Lỗi ở insert khi khách hàng nhận mã giảm giá");
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int insertAll(ArrayList<KhachHang_MaGiamGia> list) {
        int count = 0;
        for (KhachHang_MaGiamGia t : list) {
            count += insert(t);
        }
        return count;
    }

    @Override
    public int delete(KhachHang_MaGiamGia t) {
        int ketQua = 0;
        String sql = "DELETE FROM khachhang_magiamgia WHERE maKhachHang = ? AND idMaGiamGia = ?";
        try (Connection conn = JDBCUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getMaKhachHang());
            ps.setString(2, t.getIdMaGiamGia());
            ketQua = ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int deleteAll(ArrayList<KhachHang_MaGiamGia> list) {
        int count = 0;
        for (KhachHang_MaGiamGia t : list) {
            count += delete(t);
        }
        return count;
    }

    @Override
    public int update(KhachHang_MaGiamGia t) {
        return 0;
    }

    public List<MaGiamGia> selectMaGiamGiaCuaKhachHang(String makhachhang) {
        List<MaGiamGia> list = new ArrayList<MaGiamGia>();

        String sql = " SELECT m.idmagiamgia, m.tenmagiamgia, m.ngayhethan, km.soluongnguoidungdangco, m.hinhanhvoucher "
                + " FROM khachhang_magiamgia km join magiamgia m on km.idmagiamgia = m.idmagiamgia "
                + " WHERE maKhachHang = ? and km.soluongnguoidungdangco > 0 ";
        try (
                Connection conn = JDBCUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, makhachhang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MaGiamGia maGiamGia = new MaGiamGia();
                maGiamGia.setIdMaGiamGia(rs.getString(1));
                maGiamGia.setTenMaGiamGia(rs.getString(2));
                maGiamGia.setNgayHetHan(rs.getDate(3) + "");
                maGiamGia.setSoLuong(rs.getInt(4));
                maGiamGia.setHinhanhvoucher(rs.getString(5));

                list.add(maGiamGia);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int minusQuantityVoucher(String maKhachHang, String idmagiamgia) {
        int ketQua = 0;
        String sql = " update khachhang_magiamgia \n"
                + " set soluongnguoidungdangco = soluongnguoidungdangco - 1 \n"
                + " where makhachhang = ? and idmagiamgia = ?";
        try (Connection conn = JDBCUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKhachHang);
            ps.setString(2, idmagiamgia);
            ketQua = ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public int plusQuantityVoucher(String maKhachHang, String idmagiamgia) {
        int ketQua = 0;
        String sql = " update khachhang_magiamgia \n"
                + " set soluongnguoidungdangco = soluongnguoidungdangco + 1 \n"
                + " where makhachhang = ? and idmagiamgia = ?";
        try (Connection conn = JDBCUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKhachHang);
            ps.setString(2, idmagiamgia);
            ketQua = ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}
