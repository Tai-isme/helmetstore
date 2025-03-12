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
import model.ChiTietDonHang;
import model.GioHang;
import model.SanPham;
import utils.JDBCUtil;
import utils.Time;

/**
 *
 * @author Admin
 */
public class ChiTietDonHangDAO implements DAOInterface<ChiTietDonHang> {

    @Override
    public ArrayList<ChiTietDonHang> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChiTietDonHang selectById(ChiTietDonHang t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertAll(ArrayList<ChiTietDonHang> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(ChiTietDonHang t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteAll(ArrayList<ChiTietDonHang> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(ChiTietDonHang t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(ChiTietDonHang ctdh) {
        int result = 0;

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO chitietdonhang VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ctdh.getMadonhang());
            ps.setString(2, ctdh.getMasanpham());
            ps.setString(3, ctdh.getSize());
            ps.setInt(4, ctdh.getSoluongsanphammua());
            ps.setDouble(5, ctdh.getGiaban());
            result = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ChiTietDonHang select(String madonhang) throws SQLException {
        ChiTietDonHang ketQua = null;

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = " SELECT * \n"
                    + " FROM chitietdonhang\n"
                    + " WHERE madonhang = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, madonhang);

            ResultSet rs = ps.executeQuery();

            JDBCUtil.close(con);

        } catch (Exception e) {
        }

        return ketQua;
    }

    public int delete(String madonhang) {
        int result = 0;

        try {
            Connection connection = JDBCUtil.getConnection();

            String sql = " delete chitietdonhang\n"
                    + " where madonhang = ? ";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, madonhang);

            result = ps.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Lỗi ở delete Chitietdonhang");
            e.printStackTrace();
        }

        return result;

    }

    public List<SanPham> selectProductToRefundSize(String orderID) {
        List<SanPham> list = new ArrayList<SanPham>();

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = " select * from chitietdonhang "
                    + " where madonhang = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, orderID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMasanpham(rs.getString("masanpham"));
                sp.setKichco(rs.getString("size"));
                sp.setSoluong(rs.getInt("soluongsanphammua"));
                list.add(sp);
            }

            JDBCUtil.close(con);

        } catch (Exception e) {
        }

        return list;

    }

}
