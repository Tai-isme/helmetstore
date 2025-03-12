/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categories;
import model.ChiTietDonHang;
import model.DonHang;
import model.KhachHang;
import model.OrderDetails;
import model.SanPham;
import utils.JDBCUtil;

/**
 *
 * @author Admin
 */
public class AdminDAO {

    public List<SanPham> selectAllSanPham() {
        List<SanPham> listSanPham = new ArrayList<>();
        String sql = " select \n"
                + "s.masanpham, s.tensanpham,s.hinhanhsanpham,s.mausac,s.gianhap,s.giaban,s.giamgia,s.mota,\n"
                + "c.categoryID, c.categoryName,\n"
                + "sz.size, sz.soluong\n"
                + "from sanpham s join categories c ON c.categoryID = s.categoryID\n"
                + "join sanpham_size sz on sz.masanpham = s.masanpham ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Bước 1: Lấy Connection
            conn = JDBCUtil.getConnection();

            // Bước 2: Tạo PreparedStatement
            stmt = conn.prepareStatement(sql);

            // Bước 3: Thực thi truy vấn
            rs = stmt.executeQuery();

            // Bước 4: Xử lý dữ liệu
            while (rs.next()) {
                SanPham sp = new SanPham(
                        rs.getString("masanpham"),
                        rs.getString("tensanpham"),
                        rs.getString("hinhanhsanpham"),
                        rs.getString("mausac"),
                        rs.getString("size"),
                        rs.getInt("soluong"),
                        rs.getString("categoryName"),
                        rs.getDouble("gianhap"),
                        rs.getDouble("giaban"),
                        rs.getInt("giamgia"),
                        rs.getString("mota")
                );
                listSanPham.add(sp);
            }
            JDBCUtil.close(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listSanPham;
    }

    public List<Categories> selectType() {
        List<Categories> listCategories = new ArrayList<>();
        String sql = " SELECT * FROM categories ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Bước 1: Lấy Connection
            conn = JDBCUtil.getConnection();

            // Bước 2: Tạo PreparedStatement
            stmt = conn.prepareStatement(sql);

            // Bước 3: Thực thi truy vấn
            rs = stmt.executeQuery();

            // Bước 4: Xử lý dữ liệu
            while (rs.next()) {
                Categories c = new Categories(rs.getString("categoryId"), rs.getString("categoryName"));
                listCategories.add(c);
            }
            JDBCUtil.close(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listCategories;
    }

    public int insertSanPham(SanPham sp) {
        int ketQua = 0;
        try {
            // B1: Tạo connection
            Connection c = JDBCUtil.getConnection();

            // B2: Tạo câu SQL
            String sql = " INSERT INTO sanpham(masanpham, tensanpham, hinhanhsanpham, mausac, categoryID, gianhap, giaban, giamgia, mota)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, sp.getMasanpham());
            ps.setString(2, sp.getTensanpham());
            ps.setString(3, sp.getHinhanhsanpham());
            ps.setString(4, sp.getMausac());
            ps.setString(5, sp.getKieumau());
            ps.setDouble(6, sp.getGianhap());
            ps.setDouble(7, sp.getGiaban());
            ps.setInt(8, sp.getGiamgia());
            ps.setString(9, sp.getMota());

            // B3: Chạy câu lệnh SQL
            ketQua = ps.executeUpdate();

            //B4: Xử lý dữ liệu (nếu cần)
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            // B5: Đóng Connection
            JDBCUtil.close(c);

        } catch (SQLException e) {
            System.out.println("Insert sp thất bại");
            e.printStackTrace();
            e.getErrorCode();
            e.getSQLState();
        }

        return ketQua;
    }

    public int insertSize(SanPham sp) {
        int ketQua = 0;
        try {
            // B1: Tạo connection
            Connection c = JDBCUtil.getConnection();

            // B2: Tạo câu SQL
            String sql = " INSERT INTO sanpham_size(masanpham, size, soluong) VALUES (?,?,?) ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, sp.getMasanpham());
            ps.setString(2, sp.getKichco());
            ps.setInt(3, sp.getSoluong());

            // B3: Chạy câu lệnh SQL
            ketQua = ps.executeUpdate();

            //B4: Xử lý dữ liệu (nếu cần)
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            // B5: Đóng Connection
            JDBCUtil.close(c);

        } catch (SQLException e) {
            System.out.println("Insert sp1 thất bại");
            e.printStackTrace();
            e.getErrorCode();
            e.getSQLState();
        }

        return ketQua;
    }

    public boolean delete(SanPham sp) {
        String sql = " DELETE FROM sanpham WHERE masanpham = ? ";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sp.getMasanpham());

            stmt.executeUpdate();
            JDBCUtil.close(conn);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return false;
    }

    public boolean deleteSanPhamSize(SanPham sp) {
        String sql = " DELETE FROM sanpham_size WHERE masanpham = ? and size = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sp.getMasanpham());
            stmt.setString(2, sp.getKichco());

            stmt.executeUpdate();
            JDBCUtil.close(conn);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return false;
    }

    public boolean updateSanPhamSize(SanPham sp) {
        String sqlCheck = "SELECT COUNT(*) FROM sanpham_size WHERE masanpham = ? AND size = ?";
        String sqlUpdate = "UPDATE sanpham_size SET soluong = ? WHERE masanpham = ? AND size = ?";
        String sqlInsert = "INSERT INTO sanpham_size (masanpham, size, soluong) VALUES (?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement checkStmt = conn.prepareStatement(sqlCheck);
                PreparedStatement updateStmt = conn.prepareStatement(sqlUpdate);
                PreparedStatement insertStmt = conn.prepareStatement(sqlInsert)) {

            // Kiểm tra xem sản phẩm có tồn tại hay không
            checkStmt.setString(1, sp.getMasanpham());
            checkStmt.setString(2, sp.getKichco());

            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                // Nếu đã tồn tại thì UPDATE
                updateStmt.setInt(1, sp.getSoluong());
                updateStmt.setString(2, sp.getMasanpham());
                updateStmt.setString(3, sp.getKichco());
                updateStmt.executeUpdate();
            } else {
                // Nếu chưa tồn tại thì INSERT
                insertStmt.setString(1, sp.getMasanpham());
                insertStmt.setString(2, sp.getKichco());
                insertStmt.setInt(3, sp.getSoluong());
                insertStmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<KhachHang> userList() {
        List<KhachHang> list = new ArrayList<KhachHang>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = " SELECT * FROM khachhang ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKhachHang(rs.getString("makhachhang"));
                    kh.setTenDangNhap(rs.getString("tendangnhap"));
                    kh.setMatKhau(rs.getString("matkhau"));
                    kh.setHoVaTen(rs.getString("hovaten"));
                    kh.setGioiTinh(rs.getString("gioitinh"));
                    kh.setNgaySinh(rs.getDate("ngaysinh"));
                    kh.setSoDienThoai(rs.getString("sodienthoai"));
                    kh.setEmail(rs.getString("email"));
                    kh.setQuocTich(rs.getString("quoctich"));
                    kh.setDiaChiKhachHang(rs.getString("diachikhachhang"));
                    kh.setDiaChiNhanHang(rs.getString("diachinhanhang"));
                    kh.setIsAdmin(rs.getInt("isAdmin"));
                    kh.setHinhAvatar(rs.getString("hinhavatar"));
                    list.add(kh);
                }
            }
            con.close();
        } catch (Exception e) {
        }
        return list;
    }

    public boolean deleteUser(KhachHang kh) {
        String sql = " UPDATE khachhang\n"
                + "SET \n"
                + "    tenDangNhap = NULL,\n"
                + "    matKhau = NULL,\n"
                + "    hoVaTen = NULL,\n"
                + "    gioiTinh = NULL,\n"
                + "    ngaySinh = NULL,\n"
                + "    soDienThoai = NULL,\n"
                + "    email = NULL,\n"
                + "    quocTich = NULL,\n"
                + "    diaChiKhachHang = NULL,\n"
                + "    diaChiNhanHang = NULL,\n"
                + "    maXacThuc = NULL,\n"
                + "    thoiGianHieuLucMaXacThuc = NULL,\n"
                + "    trangThaiXacThuc = NULL,\n"
                + "    hinhAvatar = NULL,\n"
                + "    isAdmin = NULL\n"
                + "WHERE maKhachHang = ?; ";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, kh.getMaKhachHang());

            stmt.executeUpdate();
            JDBCUtil.close(conn);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return false;
    }

    public List<DonHang> selectAllDonHang() {
        List<DonHang> listDonHang = new ArrayList<>();
        String sql = " SELECT dh.*, kh.*\n"
                + "FROM donhang dh\n"
                + "JOIN khachhang kh ON dh.makhachhang = kh.makhachhang"
                + " Order by dh.ngaydathang desc ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DonHang d = null;
        KhachHang kh = null;
        try {
            // Bước 1: Lấy Connection
            conn = JDBCUtil.getConnection();

            // Bước 2: Tạo PreparedStatement
            stmt = conn.prepareStatement(sql);

            // Bước 3: Thực thi truy vấn
            rs = stmt.executeQuery();

            while (rs.next()) {
                d = new DonHang();
                d.setMadonhang(rs.getString("madonhang"));
                d.setMakhachhang(rs.getString("makhachhang"));
                d.setNgaydathang(rs.getString("ngaydathang"));
                d.setTrangthaidonhang(rs.getString("trangthaidonhang"));

                kh = new KhachHang();
                kh.setHoVaTen(rs.getString("hovaten"));
                kh.setSoDienThoai(rs.getString("sodienthoai"));
                kh.setEmail(rs.getString("email"));

                d.setKhachhang(kh);

                listDonHang.add(d);

            }

            // Bước 4: Xử lý dữ liệu
            JDBCUtil.close(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listDonHang;
    }

    public List<OrderDetails> selectOrderSanPham(String orderID) {
        List<OrderDetails> listOrderDetails = new ArrayList<OrderDetails>();
        String sql = " SELECT sp.hinhanhsanpham, sp.masanpham, sp.tensanpham, c.categoryName,sp.mausac ,\n"
                + "ctdh.size,ctdh.soluongsanphammua,sp.gianhap,sp.giaban,sp.giamgia, dh.tongtien\n"
                + "FROM donhang dh JOIN chitietdonhang ctdh ON dh.madonhang = ctdh.madonhang\n"
                + "JOIN sanpham sp ON sp.masanpham = ctdh.masanpham\n"
                + "JOIN categories c on c.categoryID = sp.categoryID\n"
                + "WHERE dh.madonhang = ? ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OrderDetails orderDetails = null;
        ChiTietDonHang ctdh = null;
        SanPham sp = null;
        Categories categories = null;
        try {
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, orderID);

            rs = stmt.executeQuery();

            while (rs.next()) {

                ctdh = new ChiTietDonHang();
                ctdh.setSize(rs.getString("size"));
                ctdh.setSoluongsanphammua(rs.getInt("soluongsanphammua"));
                ctdh.setGiaban(rs.getDouble("giaban"));

                sp = new SanPham();
                sp.setMasanpham(rs.getString("masanpham"));
                sp.setTensanpham(rs.getString("tensanpham"));
                sp.setHinhanhsanpham(rs.getString("hinhanhsanpham"));
                sp.setMausac(rs.getString("mausac"));
                sp.setGianhap(rs.getDouble("gianhap"));
                sp.setGiaban(rs.getDouble("giaban"));
                sp.setGiamgia(rs.getInt("giamgia"));

                categories = new Categories();
                categories.setCategoryName(rs.getString("categoryName"));

                orderDetails = new OrderDetails();
                orderDetails.setSanPham(sp);
                orderDetails.setChiTietDonHang(ctdh);
                orderDetails.setCategories(categories);

                listOrderDetails.add(orderDetails);
            }

            JDBCUtil.close(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOrderDetails;
    }

    public KhachHang selectKhachHangByOrderID(String madonhang) {
        KhachHang kh = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT k.* FROM donhang  d JOIN khachhang k on d.makhachhang = k.makhachhang WHERE madonhang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, madonhang);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("makhachhang"));
                kh.setHoVaTen(rs.getString("hovaten"));
                kh.setSoDienThoai(rs.getString("sodienthoai"));
                kh.setEmail(rs.getString("email"));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }

    public boolean updateDiaChiGiaoHang(String madonhang, String diachigiaohang) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = " UPDATE donhang SET diachigiaohang = ? WHERE madonhang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, diachigiaohang);
            ps.setString(2, madonhang);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateKhachHangInOrder(KhachHang kh) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = " UPDATE khachhang SET hovaten = ?, sodienthoai = ?, email = ? WHERE makhachhang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kh.getHoVaTen());
            ps.setString(2, kh.getSoDienThoai());
            ps.setString(3, kh.getEmail());
            ps.setString(4, kh.getMaKhachHang());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
       AdminDAO a = new AdminDAO();
       a.updateDiaChiGiaoHang("1741679675975", "123123");
       KhachHang kh = new KhachHang();
       kh.setMaKhachHang("1");
       kh.setHoVaTen("test");
       kh.setSoDienThoai("123");
       kh.setEmail("test@gmail.com");
       if(a.updateKhachHangInOrder(kh) == true){
           System.out.println("update dung");
       } else
            System.out.println("update sai");
       
    }
}
