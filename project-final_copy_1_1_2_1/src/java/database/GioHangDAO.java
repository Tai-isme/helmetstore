package database;

import model.GioHang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCUtil;

public class GioHangDAO implements DAOInterface<GioHang> {

    @Override
    public ArrayList<GioHang> selectAll() {
        ArrayList<GioHang> list = new ArrayList<>();
        String sql = "SELECT * FROM giohang";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Lấy kết nối
            conn = JDBCUtil.getConnection();
            // 2. Tạo PreparedStatement
            stmt = conn.prepareStatement(sql);
            // 3. Thực thi truy vấn
            rs = stmt.executeQuery();
            // 4. Xử lý kết quả
            while (rs.next()) {
                GioHang gh = new GioHang(
                        rs.getString("makhachhang"),
                        rs.getString("masanpham"),
                        rs.getString("size"),
                        rs.getInt("soluong")
                );
                list.add(gh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. Đóng tài nguyên
            JDBCUtil.close(conn);
        }
        return list;
    }

    @Override
    public GioHang selectById(GioHang t) {
        GioHang gh = null;
        // Giả sử khóa chính gồm 3 trường: makhachhang, masanpham, size
        String sql = "SELECT * FROM giohang WHERE makhachhang = ? AND masanpham = ? AND size = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, t.getMakhachhang());
            stmt.setString(2, t.getMasanpham());
            stmt.setString(3, t.getSize());

            rs = stmt.executeQuery();
            if (rs.next()) {
                gh = new GioHang(
                        rs.getString("makhachhang"),
                        rs.getString("masanpham"),
                        rs.getString("size"),
                        rs.getInt("soluong")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return gh;
    }

    @Override
    public int insert(GioHang t) {
        int result = 0;
        String sql = "INSERT INTO giohang(makhachhang, masanpham, size, soluong) VALUES(?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, t.getMakhachhang());
            stmt.setString(2, t.getMasanpham());
            stmt.setString(3, t.getSize());
            stmt.setInt(4, t.getSoluong());

            result = stmt.executeUpdate();

        } catch (SQLException e) {
            if (result == 0) {
                result = addOneProduct(t);
            }
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return result;
    }

    @Override
    public int insertAll(ArrayList<GioHang> list) {
        int count = 0;
        for (GioHang gh : list) {
            count += insert(gh);
        }
        return count;
    }

    @Override
    public int delete(GioHang t) {
        int result = 0;
        String sql = "DELETE FROM giohang WHERE makhachhang = ? AND masanpham = ? AND size = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, t.getMakhachhang());
            stmt.setString(2, t.getMasanpham());
            stmt.setString(3, t.getSize());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return result;
    }

    @Override
    public int deleteAll(ArrayList<GioHang> list) {
        int count = 0;
        for (GioHang gh : list) {
            count += delete(gh);
        }
        return count;
    }

    @Override
    public int update(GioHang t) {
        int result = 0;
        // Giả sử cập nhật chỉ trường 'soluong', giữ nguyên các trường khóa
        String sql = "UPDATE giohang SET soluong = ? WHERE makhachhang = ? AND masanpham = ? AND size = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, t.getSoluong());
            stmt.setString(2, t.getMakhachhang());
            stmt.setString(3, t.getMasanpham());
            stmt.setString(4, t.getSize());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return result;
    }

    public int addOneProduct(GioHang t) {
        int result = 0;
        // Giả sử cập nhật chỉ trường 'soluong', giữ nguyên các trường khóa
        String sql = "update giohang \n"
                + " set soluong = soluong + 1 \n "
                + " where makhachhang = ? and masanpham = ?  and size = ? ";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, t.getMakhachhang());
            stmt.setString(2, t.getMasanpham());
            stmt.setString(3, t.getSize());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return result;

    }

    public List<GioHang> selectGioHangCuaKhachHang(String makhachhang) {
        GioHang gh = null;
        // Giả sử khóa chính gồm 3 trường: makhachhang, masanpham, size
        String sql = "SELECT * FROM giohang WHERE makhachhang = ? ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<GioHang> list = new ArrayList<GioHang>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, makhachhang);

            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new GioHang(rs.getString("makhachhang"),
                        rs.getString("masanpham"),
                        rs.getString("size"),
                        rs.getInt("soluong")
                )
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return list;
    }

    public int minusOneCart(GioHang gioHang) {
        int result = 0;
        // Giả sử cập nhật chỉ trường 'soluong', giữ nguyên các trường khóa
        String sql = "update giohang \n"
                + " set soluong = soluong - 1\n"
                + " where makhachhang = ? and masanpham = ?  and size = ? ";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, gioHang.getMakhachhang());
            stmt.setString(2, gioHang.getMasanpham());
            stmt.setString(3, gioHang.getSize());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return result;
    }

    public int deleteproductoutcart(GioHang gioHang) {

        int result = 0;
        // Giả sử cập nhật chỉ trường 'soluong', giữ nguyên các trường khóa
        String sql = "update giohang \n"
                + " set soluong = 0 \n "
                + " where makhachhang = ? and masanpham = ?  and size = ? ";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, gioHang.getMakhachhang());
            stmt.setString(2, gioHang.getMasanpham());
            stmt.setString(3, gioHang.getSize());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return result;

    }

    public GioHang selectByMaSanPham(String string) {
        String sql = "SELECT * FROM giohang WHERE masanpham = ? ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        GioHang result = null;
        try {
            // 1. Lấy kết nối
            conn = JDBCUtil.getConnection();
            // 2. Tạo PreparedStatement
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, string);
            // 3. Thực thi truy vấn
            rs = stmt.executeQuery();
            // 4. Xử lý kết quả
            while (rs.next()) {
                result = new GioHang(
                        rs.getString("makhachhang"),
                        rs.getString("masanpham"),
                        rs.getString("size"),
                        rs.getInt("soluong")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. Đóng tài nguyên
            JDBCUtil.close(conn);
        }
        return result;
    }

    public int updateAfterPay(GioHang t) {
        int result = 0;
        // Giả sử cập nhật chỉ trường 'soluong', giữ nguyên các trường khóa
        String sql = "update giohang\n"
                + "set soluong = soluong - ?\n"
                + "where makhachhang = ? and masanpham = ? AND size = ? ";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, t.getSoluong());
            stmt.setString(2, t.getMakhachhang());
            stmt.setString(3, t.getMasanpham());
            stmt.setString(4, t.getSize());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return result;
    }

}
