package database;

import model.SanPham;
import utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SanPhamDAO implements DAOInterface<SanPham> {

    @Override
    public ArrayList<SanPham> selectAll() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "SELECT distinct s.masanpham, s.tensanpham, s.hinhanhsanpham, s.mausac, c.categoryName as 'kieumau', s.gianhap, s.giaban, s.giamgia, s.mota\n"
                + "FROM sanpham s join categories c on s.categoryID = c.categoryID\n"
                + "join sanpham_size ss on s.masanpham = ss.masanpham\n"
                + "where ss.soluong >0";

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
                        rs.getString("kieumau"),
                        rs.getDouble("gianhap"),
                        rs.getDouble("giaban"),
                        rs.getInt("giamgia"),
                        rs.getString("mota")
                );
                list.add(sp);
            }
            JDBCUtil.close(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public SanPham selectById(SanPham sp) {
        SanPham sanPham = null;
        String sql = "SELECT * FROM sanpham WHERE masanpham=?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sp.getMasanpham());

            rs = stmt.executeQuery();

            if (rs.next()) {
                sanPham = new SanPham(
                        rs.getString("masanpham"),
                        rs.getString("tensanpham"),
                        rs.getString("hinhanhsanpham"),
                        rs.getString("mausac"),
                        rs.getString("kichco"),
                        rs.getInt("soluong"),
                        rs.getString("kieumau"),
                        rs.getDouble("gianhap"),
                        rs.getDouble("giaban"),
                        rs.getInt("giamgia"),
                        rs.getString("mota")
                );
            }

            JDBCUtil.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sanPham;
    }

    @Override
    public int insert(SanPham sp) {
        int ketQua = 0;
        String sql = "INSERT INTO sanpham VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, sp.getMasanpham());
            stmt.setString(2, sp.getTensanpham());
            stmt.setString(3, sp.getHinhanhsanpham());
            stmt.setString(4, sp.getMausac());
            stmt.setString(5, sp.getKichco());
            stmt.setInt(6, sp.getSoluong());
            stmt.setString(7, sp.getKieumau());
            stmt.setDouble(8, sp.getGianhap());
            stmt.setDouble(9, sp.getGiaban());
            stmt.setInt(10, sp.getGiamgia());
            stmt.setString(11, sp.getMota());

            ketQua = stmt.executeUpdate();
            JDBCUtil.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return ketQua;
    }

    @Override
    public int delete(SanPham sp) {
        int ketQua = 0;
        String sql = "DELETE FROM sanpham WHERE masanpham=?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sp.getMasanpham());

            ketQua = stmt.executeUpdate();
            JDBCUtil.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return ketQua;
    }

    @Override
    public int update(SanPham sp) {
        int ketQua = 0;
        String sql = " UPDATE sanpham\n"
                + "SET tensanpham = ?,\n"
                + "    hinhanhsanpham = ?,\n"
                + "    mausac = ?,\n"
                + "    categoryID = ?,\n"
                + "    gianhap = ?,\n"
                + "    giaban = ?,\n"
                + "    giamgia = ?,\n"
                + "    mota = ?\n"
                + "WHERE masanpham = ?;";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, sp.getTensanpham());
            stmt.setString(2, sp.getHinhanhsanpham());
            stmt.setString(3, sp.getMausac());
            stmt.setString(4, sp.getKieumau());
            stmt.setDouble(5, sp.getGianhap());
            stmt.setDouble(6, sp.getGiaban());
            stmt.setInt(7, sp.getGiamgia());
            stmt.setString(8, sp.getMota());
            stmt.setString(9, sp.getMasanpham());

            stmt.executeUpdate();
            JDBCUtil.close(conn);
            ketQua = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return ketQua;
    }

    @Override
    public int insertAll(ArrayList<SanPham> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteAll(ArrayList<SanPham> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<String> selectColor() {
        ArrayList<String> list = new ArrayList<>();
        String sql = "select distinct mausac\n"
                + "from sanpham";

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

                list.add(rs.getString("mausac"));
            }
            JDBCUtil.close(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<SanPham> searchByConditions(String types, String color, String size, int priceFrom, int priceTo) {

        ArrayList<SanPham> list = new ArrayList<SanPham>();

        try {
            String sql = "SELECT * FROM sanpham WHERE 1=1";

// Lọc theo loại mũ (Fullface, Half-Helmet, Three-Quarter)
            if (types.length() > 0) {
                sql += " AND kieumau = ?";
            }

            if (color.length() > 0) {
                sql += " AND mausac = ?";
            }

// Lọc theo size (S, M, L, XL)
            if (size.length() > 0) {
                sql += " AND kichco = ?";
            }

// Lọc theo khoảng giá (200000-500000, 500000-1000000, ...)
            if (priceFrom > 0 && priceTo > 0) {

                sql += " AND giaban BETWEEN ? AND ?";
            }

            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            int index = 1;

// Gán giá trị cho câu SQL
            if (types.length() > 0) {
                ps.setString(index++, types);
            }

            if (color.length() > 0) {
                ps.setString(index++, color);
            }

            if (size.length() > 0) {
                ps.setString(index++, size);
            }

            if (priceFrom > 0 && priceTo > 0) {

                ps.setInt(index++, priceFrom);
                ps.setInt(index++, priceTo);
            }

// Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String masanpham = rs.getString("masanpham");
                String tensanpham = rs.getString("tensanpham");
                String hinhanhsanpham = rs.getString("hinhanhsanpham");
                String mausac = rs.getString("mausac");
                String kichco = rs.getString("kichco");
                Integer soluong = rs.getInt("soluong");
                String kieumau = rs.getString("kieumau");
                Double gianhap = rs.getDouble("gianhap");
                Double giaban = rs.getDouble("giaban");
                Integer giamgia = rs.getInt("giamgia");
                String mota = rs.getString("mota");

                SanPham sp = new SanPham(masanpham, tensanpham, hinhanhsanpham, mausac, kichco, soluong, kieumau, gianhap, giaban, giamgia, mota);
                list.add(sp);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;

    }

    public List<SanPham> searchList(String keyword) {
        List<SanPham> list = new ArrayList<SanPham>();
        SanPham sp = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE tensanpham LIKE ? OR masanpham LIKE ? OR mausac LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, '%' + keyword + '%');
            ps.setString(2, '%' + keyword + '%');
            ps.setString(3, '%' + keyword + '%');
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    sp = new SanPham(rs.getString("masanpham"), rs.getString("tensanpham"), rs.getString("hinhanhsanpham"), rs.getString("mausac"),
                            rs.getString("kieumau"), rs.getDouble("gianhap"),
                            rs.getDouble("giaban"), rs.getInt("giamgia"), rs.getString("mota"));
                    list.add(sp);
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SanPham> sort(List<SanPham> list, String sortPrices) {
        if (sortPrices.equals("default")) {
            return list;
        }

        if (sortPrices.equals("price-asc")) {
            Collections.sort(list, new Comparator<SanPham>() {
                @Override
                public int compare(SanPham o1, SanPham o2) {
                    return o1.getGiaban().compareTo(o2.getGiaban());
                }
            });
        } else if (sortPrices.equals("price-desc")) {
            Collections.sort(list, new Comparator<SanPham>() {
                @Override
                public int compare(SanPham o1, SanPham o2) {
                    return o2.getGiaban().compareTo(o1.getGiaban());
                }
            });
        }

        return list;
    }

    public SanPham selectById(String masanpham) {
        SanPham sanPham = null;
        String sql = " select s.masanpham, s.tensanpham, s.hinhanhsanpham, s.mausac, c.categoryName as 'kieumau', s.gianhap, s.giaban, s.giamgia, s.mota\n"
                + " from sanpham s join categories c on s.categoryID = c.categoryID\n"
                + " where masanpham = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, masanpham);

            rs = stmt.executeQuery();

            if (rs.next()) {
                String masanphamStr = rs.getString(1);
                String tensanpham = rs.getString(2);
                String hinhanhsanpham = rs.getString(3);
                String mausac = rs.getString(4);

                String kieumau = rs.getString(5);
                Double gianhap = rs.getDouble(6);
                Double giaban = rs.getDouble(7);
                Integer giamgia = rs.getInt(8);
                String mota = rs.getString(9);

                sanPham = new SanPham(masanpham, tensanpham, hinhanhsanpham, mausac, kieumau, gianhap, giaban, giamgia, mota);
            }

            JDBCUtil.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sanPham;
    }

    public int minusPoduct(String masanpham, String size, int soluong) {
        int rs = 0;
        SanPham sanPham = null;
        String sql = " update sanpham_size \n"
                + " set soluong = soluong - ? \n"
                + " where masanpham = ?  AND size = ?";
        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, soluong);
            stmt.setString(2, masanpham);
            stmt.setString(3, size);
            rs = stmt.executeUpdate();

            JDBCUtil.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;

    }

    public int kiemTraSoLuongSanPham(SanPham sanPham) {
        int soLuongSanPhamTrongKho = 0;
        String sql = " select soluong\n"
                + " from sanpham_size\n"
                + " where masanpham = ? and size = ? ";
        try {
            Connection conn = null;
            PreparedStatement stmt = null;

            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, sanPham.getMasanpham());
            stmt.setString(2, sanPham.getKichco());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                soLuongSanPhamTrongKho = rs.getInt(1);
            }
            System.out.println("soLuongSanPhamTrongKho" + soLuongSanPhamTrongKho);

            JDBCUtil.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soLuongSanPhamTrongKho;
    }

    public List<SanPham> selectsizecuamasanphamconhang(String masanpham) {
        List<SanPham> list = new ArrayList<SanPham>();
        String sql = "select soluong, size \n"
                + "from sanpham_size\n"
                + "where masanpham = ? "
                + " ORDER BY size desc ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Bước 1: Lấy Connection
            conn = JDBCUtil.getConnection();

            // Bước 2: Tạo PreparedStatement
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, masanpham);
            // Bước 3: Thực thi truy vấn
            rs = stmt.executeQuery();

            // Bước 4: Xử lý dữ liệu
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setSoluong(rs.getInt("soluong"));
                sp.setKichco(rs.getString("size"));

                list.add(sp);
            }
            JDBCUtil.close(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public SanPham selectByIdSanPhamSize(String masanpham) {

        String sql = " SELECT s.masanpham, s.tensanpham, s.hinhanhsanpham, s.mausac, s.categoryID, s.gianhap, s.giaban, s.giamgia, s.mota, ss.size,ss.soluong\n"
                + "FROM sanpham s JOIN sanpham_size ss ON s.masanpham = ss.masanpham\n"
                + "WHERE s.masanpham = ? ";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SanPham sp = new SanPham();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, masanpham);

            rs = stmt.executeQuery();

            if (rs.next()) {
                sp.setMasanpham(rs.getString(1));
                sp.setTensanpham(rs.getString(2));
                sp.setHinhanhsanpham(rs.getString(3));
                sp.setMausac(rs.getString(4));
                sp.setKieumau(rs.getString(5));
                sp.setGianhap(rs.getDouble(6));
                sp.setGiaban(rs.getDouble(7));
                sp.setGiamgia(rs.getInt(8));
                sp.setMota(rs.getString(9));
                sp.setKichco(rs.getString(10));
                sp.setSoluong(rs.getInt(11));
            }

            JDBCUtil.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sp;
    }

    public int updateAnhSanPham(SanPham sanpham) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = " UPDATE sanpham\n"
                    + " SET hinhanhsanpham = ?\n"
                    + " WHERE masanpham = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sanpham.getHinhanhsanpham());
            ps.setString(2, sanpham.getMasanpham());

            ketQua = ps.executeUpdate();

            JDBCUtil.close(con);

        } catch (Exception e) {
            System.out.println("Lỗi ở updateAvatar");
            e.printStackTrace();
        }

        return ketQua;
    }

    public int refundSize(SanPham sanPham) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = " update sanpham_size "
                    + " set  soluong = soluong + ? "
                    + " where masanpham = ? and size = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, sanPham.getSoluong());
            ps.setString(2, sanPham.getMasanpham());
            ps.setString(3, sanPham.getKichco());

            ketQua = ps.executeUpdate();

            JDBCUtil.close(con);

        } catch (Exception e) {
            System.out.println("Lỗi ở updateAvatar");
            e.printStackTrace();
        }

        return ketQua;
    }

    public int soLuongSanPhamDaBan(String masanpham) {
        int soLuongSanPhamDaBan = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = " select count(c.masanpham) as soluongsanphamdaban\n "
                    + " from chitietdonhang c\n "
                    + " where c.masanpham = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, masanpham);
        
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                soLuongSanPhamDaBan = rs.getInt("soluongsanphamdaban");
            }
                
            JDBCUtil.close(con);

        } catch (Exception e) {
            System.out.println("Lỗi ở updateAvatar");
            e.printStackTrace();
        }

        return soLuongSanPhamDaBan;
    }

}
