/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web;

import database.KhachHang_MaGiamGiaDAO;
import database.MaGiamGiaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.KhachHang;
import model.KhachHang_MaGiamGia;

/**
 *
 * @author Thien
 */
@WebServlet(name = "MaGiamGiaController", urlPatterns = {"/ma-giam-gia"})
public class MaGiamGiaController extends HttpServlet {

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

        if (hanhdong.equals("get")) {
            khachHangGetMaGiamGia(request, response);

        }

    }

    private void khachHangGetMaGiamGia(HttpServletRequest request, HttpServletResponse response) {
        String idmagiamgia = request.getParameter("idmagiamgia");
        // Lấy đối tượng kh từ session
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");

        String error = "";

        if (khachHang != null) {
            // Lưu xuống CSDL ở khachhang_magiamgia -> khách hàng đó có voucher
            KhachHang_MaGiamGia khachHang_MaGiamGia = new KhachHang_MaGiamGia(khachHang.getMaKhachHang(), idmagiamgia);
            KhachHang_MaGiamGiaDAO dao = new KhachHang_MaGiamGiaDAO();
            if (dao.insert(khachHang_MaGiamGia) == 0) {
                error = "Có lỗi trong quá trình nhận mã giảm giá";
                request.setAttribute("error", error);
            }else{
                // lấy thành công sẽ trừ số lượng mã giảm giá lại
                MaGiamGiaDAO maGiamGiadao = new MaGiamGiaDAO();
                maGiamGiadao.updateSauKhiKhachHangNhanMa(idmagiamgia);
            }
            try {
                request.getServletContext().getRequestDispatcher("/homeGUI/getvoucher.jsp").forward(request, response);
            } catch (Exception e) {
            }

        }
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }