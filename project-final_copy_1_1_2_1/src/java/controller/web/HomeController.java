/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web;

import database.MaGiamGiaDAO;
import database.SanPhamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Item;
import model.MaGiamGia;
import model.Order;
import model.SanPham;
import utils.JDBCUtil;

/**
 *
 * @author Thien
 */
@WebServlet(name = "WebController", urlPatterns = {"/web"})
public class HomeController extends HttpServlet {

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

        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);

        if (action == null) {
            SanPhamDAO sanPhamDAO = new SanPhamDAO();
            List<SanPham> listsanpham = sanPhamDAO.selectAll();

            MaGiamGiaDAO maGiamGiaDAO = new MaGiamGiaDAO();
            List<MaGiamGia> listmagiamgia = maGiamGiaDAO.selectAll();

            request.setAttribute("listsanpham", listsanpham);
            request.setAttribute("listmagiamgia", listmagiamgia);

            List<String> listColor = sanPhamDAO.selectColor();
            List<Item> list = new ArrayList<Item>();
            Order order = new Order();
            order.setList(list);
//
            session.setAttribute("order", order);
            session.setAttribute("listColor", listColor);
            request.getRequestDispatcher("/GUI/index.jsp").forward(request, response);
//            response.sendRedirect("GUI/index.jsp");
            return;
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

}
