package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MaGiamGia;
import utils.JDBCUtil;

public class MaGiamGiaDAO implements DAOInterface<MaGiamGia> {

    @Override
    public ArrayList<MaGiamGia> selectAll() {
        ArrayList<MaGiamGia> list = new ArrayList<>();
        String query = "SELECT * FROM magiamgia where soluongvoucherconlai > 0 ";

        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MaGiamGia mg = new MaGiamGia(
                        rs.getString("idMaGiamGia"),
                        rs.getString("tenMaGiamGia"),
                        rs.getInt("tiLeGiam"),
                        rs.getString("ngayHetHan"),
                        rs.getInt("soluongvoucherconlai"),
                        rs.getString("hinhanhvoucher")
                );
                list.add(mg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public MaGiamGia selectById(MaGiamGia t) {
        MaGiamGia mg = null;
        String query = "SELECT * FROM MaGiamGia WHERE idMaGiamGia = ?";

        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, t.getIdMaGiamGia());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mg = new MaGiamGia(
                            rs.getString("idMaGiamGia"),
                            rs.getString("tenMaGiamGia"),
                            rs.getInt("tiLeGiam"),
                            rs.getString("ngayHetHan")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mg;
    }

    @Override
    public int insert(MaGiamGia t) {
        String query = "INSERT INTO MaGiamGia (idMaGiamGia, tenMaGiamGia, tiLeGiam, ngayHetHan) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, t.getIdMaGiamGia());
            stmt.setString(2, t.getTenMaGiamGia());
            stmt.setInt(3, t.getTiLeGiam());
            stmt.setString(4, t.getNgayHetHan());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertAll(ArrayList<MaGiamGia> list) {
        int count = 0;
        for (MaGiamGia mg : list) {
            count += insert(mg);
        }
        return count;
    }

    @Override
    public int delete(MaGiamGia t) {
        String query = "DELETE FROM MaGiamGia WHERE idMaGiamGia = ?";
        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, t.getIdMaGiamGia());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteAll(ArrayList<MaGiamGia> list) {
        int count = 0;
        for (MaGiamGia mg : list) {
            count += delete(mg);
        }
        return count;
    }

    @Override
    public int update(MaGiamGia t) {
        String query = "UPDATE MaGiamGia SET tenMaGiamGia = ?, tiLeGiam = ?, ngayHetHan = ? WHERE idMaGiamGia = ?";
        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, t.getTenMaGiamGia());
            stmt.setInt(2, t.getTiLeGiam());
            stmt.setString(3, t.getNgayHetHan());
            stmt.setString(4, t.getIdMaGiamGia());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateSauKhiKhachHangNhanMa(String idMaGiamGia) {
        String query = "UPDATE magiamgia SET soluongvoucherconlai = soluongvoucherconlai - 1 WHERE idmagiamgia = ?";
        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, idMaGiamGia);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public MaGiamGia selectById(String idmagiamgia) {
        MaGiamGia mg = null;
        String query = "SELECT * FROM MaGiamGia WHERE idMaGiamGia = ?";

        try {
            Connection conn = JDBCUtil.getConnection();

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, idmagiamgia);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String idmasanpham = rs.getString(1);
                String tenMaSanPham = rs.getString(2);
                int tilegiam = rs.getInt(3);
                Date ngayhethan = rs.getDate(4);
                int soluongvoucherconlai = rs.getInt(5);
                String theloai = rs.getString(6);
                String hinhanhvoucher = rs.getString(7);
                
                mg = new MaGiamGia();
                mg.setIdMaGiamGia(idmagiamgia);
                mg.setTenMaGiamGia(tenMaSanPham);
                mg.setTiLeGiam(tilegiam);
                mg.setNgayHetHan(String.valueOf(ngayhethan));
                mg.setSoLuong(soluongvoucherconlai);
                mg.setTheloai(theloai);
                mg.setHinhanhvoucher(hinhanhvoucher);
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mg;
    }

    

}
