/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web;

import database.SanPhamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SanPham;
import utils.JDBCUtil;

/**
 *
 * @author Thien
 */
@WebServlet(name = "SanPhamController", urlPatterns = {"/san-pham"})
public class SanPhamController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hanhdong = request.getParameter("hanhdong");

        if (hanhdong == null || hanhdong.equals("searchByConditions")) {
            searchByConditions(request, response);
        } else if (hanhdong.equals("search")) {
            search(request, response);
        } else if (hanhdong.equals("sort")) {
            sort(request, response);
        } else if (hanhdong.equals("productdetails")) {
            productdetails(request, response);
        } else if (hanhdong.equals("viewproductdetail")) {
            viewproductdetail(request, response);
        }

    }

    private void searchByConditions(HttpServletRequest request, HttpServletResponse response) {
        try {

            String types = request.getParameter("types");
            String color = request.getParameter("color");

            String size = request.getParameter("size");
            String price = request.getParameter("price");

//            String sql = "SELECT * FROM sanpham WHERE 1=1";
            ////////////////////////////////////////////////////////////////////
            String sql = " SELECT distinct s.masanpham, tensanpham, hinhanhsanpham, mausac, c.categoryName as 'kieumau', giaban, giamgia \n"
                    + "from sanpham s inner join sanpham_size ss on s.masanpham=ss.masanpham join categories c on c.categoryID = s.categoryID";

            sql += " where 1=1 and ss.soluong >0  ";

////////////////////////////////////////////////////////////////////
// Lọc theo loại mũ (Fullface, Half-Helmet, Three-Quarter)
            if (request.getParameter("types") != null && !request.getParameter("types").isEmpty()) {
                sql += " AND c.categoryName = ?";
            }

            if (request.getParameter("color") != null && !request.getParameter("color").isEmpty()) {
                sql += " AND mausac = ?";
            }

// Lọc theo size (S, M, L, XL)
            if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
                sql += " AND size = ?";
            }

// Lọc theo khoảng giá (200000-500000, 500000-1000000, ...)
            if (request.getParameter("price") != null && !request.getParameter("price").isEmpty()) {
                String priceRange = request.getParameter("price");
                String[] prices = priceRange.split("-");
                sql += " AND giaban BETWEEN ? AND ?";
            }

// Thêm điều kiện sắp xếp
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            int index = 1;

// Gán giá trị cho câu SQL
            if (request.getParameter("types") != null && !request.getParameter("types").isEmpty()) {
                ps.setString(index++, request.getParameter("types"));
            }

            if (request.getParameter("color") != null && !request.getParameter("color").isEmpty()) {
                ps.setString(index++, request.getParameter("color"));
            }

            if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
                ps.setString(index++, request.getParameter("size"));
            }

            if (request.getParameter("price") != null && !request.getParameter("price").isEmpty()) {
                String[] prices = request.getParameter("price").split("-");
                ps.setInt(index++, Integer.parseInt(prices[0]));
                ps.setInt(index++, Integer.parseInt(prices[1]));
            }

            System.out.println("Câu sql11111: " + sql);

// Thực thi truy vấn
            ResultSet rs = ps.executeQuery();
            ArrayList<SanPham> list = new ArrayList<SanPham>();
            while (rs.next()) {
                list.add(new SanPham(
                        rs.getString("masanpham"),
                        rs.getString("tensanpham"),
                        rs.getString("hinhanhsanpham"),
                        rs.getString("mausac"),
                        rs.getString("kieumau"),
                        rs.getDouble("giaban"),
                        rs.getInt("giamgia")
                ));
            }
            conn.close();

            HttpSession session = request.getSession(false);
            request.setAttribute("types", types);
            request.setAttribute("color", color);

            request.setAttribute("size", size);

            request.setAttribute("price", price);
            if (list.isEmpty()) {
                System.out.println("listSerchByCondition bị null");
            } else {
                System.out.println("Lỗi ở trang khác");
            }

            session.setAttribute("list", list);

            request.getRequestDispatcher("/GUI/shop.jsp").forward(request, response);
        } catch (Exception e) {
        }

    }

    public void search(HttpServletRequest request, HttpServletResponse response) {
        List<SanPham> list = new ArrayList<SanPham>();
        String url = "";
        try {
            HttpSession session = request.getSession();
            String keyword = request.getParameter("keyword");
            SanPhamDAO spd = new SanPhamDAO();
            list = spd.searchList(keyword);

            if (list != null && !list.isEmpty()) {

                session.setAttribute("list", list);
                request.setAttribute("keyword", keyword);
                request.getRequestDispatcher("/GUI/shop.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/GUI/shop.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sort(HttpServletRequest request, HttpServletResponse response) {
        String types = request.getParameter("types");
        String color = request.getParameter("color");

        String size = request.getParameter("size");
        String price = request.getParameter("price");
        request.setAttribute("types", types);
        request.setAttribute("color", color);

        request.setAttribute("size", size);

        request.setAttribute("price", price);
        String sortPrices = request.getParameter("sortPrices");
        List<SanPham> list = new ArrayList<SanPham>();
        String url = "";
        try {
            HttpSession session = request.getSession();
            list = (List<SanPham>) session.getAttribute("list");
            String keyword = request.getParameter("keyword");

            SanPhamDAO spd = new SanPhamDAO();
            list = spd.sort(list, sortPrices);

            if (list != null && !list.isEmpty()) {

                session.setAttribute("list", list);
                request.setAttribute("keyword", keyword);

                request.getRequestDispatcher("/GUI/shop.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/GUI/shop.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void productdetails(HttpServletRequest request, HttpServletResponse response) {
        String masanpham = request.getParameter("masanpham");

        SanPhamDAO sanPhamDAO = new SanPhamDAO();
        SanPham sanPham = sanPhamDAO.selectById(masanpham);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void viewproductdetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String masanpham = request.getParameter("masanpham");
            SanPhamDAO dao = new SanPhamDAO();
            SanPham sp1 = dao.selectById(masanpham);
            List<SanPham> listsoluongsize = dao.selectsizecuamasanphamconhang(masanpham);

            List<SanPham> list = dao.selectAll(); // list cho 4 sản phẩm bên dưới
            int soluongSanPhamDaBan = dao.soLuongSanPhamDaBan(masanpham);
            
            
            request.setAttribute("list", list);
            request.setAttribute("soluongSanPhamDaBan", soluongSanPhamDaBan);
            request.setAttribute("listsoluongsize", listsoluongsize);
            request.setAttribute("sanpham", sp1);
            request.getRequestDispatcher("/GUI/product-details.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
