/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import database.AdminDAO;
import database.ChiTietDonHangDAO;
import database.DonHangDAO;
import database.KhachHangDAO;
import database.SanPhamDAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Categories;
import model.DonHang;
import model.KhachHang;
import model.OrderDetails;
import model.SanPham;
import utils.Money;

/**
 *
 * @author Admin
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

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
        System.out.println("Hanh dong o admin = " + hanhdong);
        if (hanhdong == null) {
            response.sendRedirect("admin-home");
        } else if (hanhdong.equals("load")) {
            loadAllSanPham(request, response);
        } else if (hanhdong.equals("createproduct")) {
            createproduct(request, response);
        } else if (hanhdong.equals("addProduct")) {
            addProduct(request, response);
        } else if (hanhdong.equals("delete")) {
            delete(request, response);
        } else if (hanhdong.equals("editsanpham")) {
            editSanPhamDetails(request, response);
        } else if (hanhdong.equals("updatesanpham")) {
            updateSanPhamDetails(request, response);
        } else if (hanhdong.equals("user")) {
            user(request, response);
        } else if (hanhdong.equals("edituser")) {
            editUserDetails(request, response);
        } else if (hanhdong.equals("updateuser")) {
            updateUserDetails(request, response);
        } else if (hanhdong.equals("createuser")) {
            createuser(request, response);
        } else if (hanhdong.equals("adduser")) {
            adduser(request, response);
        } else if (hanhdong.equals("deleteuser")) {
            deleteuser(request, response);
        } else if (hanhdong.equals("order")) {
            order(request, response);
        } else if (hanhdong.equals("orderDetails")) {
            orderDetails(request, response);
        } else if (hanhdong.equals("updateKhachHangInOrder")) {
            updateKhachHangInOrder(request, response);
        } else if (hanhdong.equals("editKhachHangInOrder")) {
            editKhachHangInOrder(request, response);
        } else if (hanhdong.equals("deleteorder")) {
            deleteorder(request, response);
        }
    }

    private void loadAllSanPham(HttpServletRequest request, HttpServletResponse response) {
        List<SanPham> spList = new ArrayList<SanPham>();
        try {
            AdminDAO adao = new AdminDAO();
            spList = adao.selectAllSanPham();

            if (spList != null) {

                String contextPath = request.getContextPath();
                long currentMilliTime = System.currentTimeMillis();

                for (SanPham sanPham : spList) {
                    sanPham.setGianhapformated(Money.getMoney(sanPham.getGianhap()));
                    sanPham.setGiabanformated(Money.getMoney(sanPham.getGiaban()));
                }
                System.out.println("spList size " + spList.size());
                request.setAttribute("spList", spList);
            }
            request.getRequestDispatcher("/admin/category.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createproduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Categories> spListType = new ArrayList<Categories>();
            SanPham sp = new SanPham();

            AdminDAO adao = new AdminDAO();
            spListType = adao.selectType();

            request.setAttribute("nextaction", "addProduct");
            request.setAttribute("sp", sp);
            request.setAttribute("spListType", spListType);
//            response.sendRedirect(request.getContextPath() + "/admin/addproduct.jsp");
            request.getRequestDispatcher("/admin/addproduct.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addhinhanhsanpham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Da vao add hinh anh");
        String folerName = "/GUI/imgsanpham";

        // Lấy đường dẫn thực của thư mục upload trên server
        String uploadPath = getServletContext().getRealPath("");
        uploadPath = uploadPath.substring(0, uploadPath.length() - 10);
        uploadPath = uploadPath + "web" + File.separator + folerName + File.separator;;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // Tạo folder nếu chưa có
        }
        //Lấy file từ request
        Part filePart = request.getPart("hinhanhsanpham");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        String error = "";
        // Kiểm tra file hợp lệ
        if (fileName == null || fileName.isEmpty()) {
            if (fileName.length() < 5) {
                error += "File không hợp lệ";
            }
            return;
        }
        // Kiểm tra xem có đúng là file ảnh hay không
        if (fileName.lastIndexOf(".jpg") > 0 || fileName.lastIndexOf(".jpeg") > 0 || fileName.lastIndexOf(".png") > 0) {

//            HttpSession session = request.getSession(false);
            Object obj = request.getAttribute("sp");
            SanPham sanpham = null;
            if (obj != null) {
                sanpham = (SanPham) obj;
            }

            //Đổi lại tên file bằng tên của tên san pham để tránh bị trùng
            int dotLaster = fileName.lastIndexOf(".");
            fileName = sanpham.getTensanpham() + fileName.substring(dotLaster);

            // Lưu file vào thư mục uploads
            String filePath = uploadPath + fileName;
            System.out.println("Lưu thành công" + filePath);
            filePart.write(filePath);

            // Lưu tên file xuống CSDL
            sanpham.setHinhanhsanpham(fileName);
            SanPhamDAO spdao = new SanPhamDAO();
            if (spdao.updateAnhSanPham(sanpham) > 0) {
                System.out.println("Lưu tên file xuống CSDL thành công");
                error = "Đã cập nhật Avatar thành công";
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin?hanhdong=load");
    }

    protected void addhinhavatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Da vao add hinh anh");
        String folerName = "uploads";

        // Lấy đường dẫn thực của thư mục upload trên server
        String uploadPath = getServletContext().getRealPath("");
        uploadPath = uploadPath.substring(0, uploadPath.length() - 10);
        uploadPath = uploadPath + "web" + File.separator + folerName + File.separator;;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // Tạo folder nếu chưa có
        }
        //Lấy file từ request
        Part filePart = request.getPart("hinhavatar");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        String error = "";
        // Kiểm tra file hợp lệ
        if (fileName == null || fileName.isEmpty()) {
            if (fileName.length() < 5) {
                error += "File không hợp lệ";
            }
            return;
        }
        // Kiểm tra xem có đúng là file ảnh hay không
        if (fileName.lastIndexOf(".jpg") > 0 || fileName.lastIndexOf(".jpeg") > 0 || fileName.lastIndexOf(".png") > 0) {

//            HttpSession session = request.getSession(false);
            Object obj = request.getAttribute("khachHang");
            KhachHang khachhang = null;
            if (obj != null) {
                khachhang = (KhachHang) obj;
            }

            //Đổi lại tên file bằng tên của tên san pham để tránh bị trùng
            int dotLaster = fileName.lastIndexOf(".");
            fileName = khachhang.getTenDangNhap() + fileName.substring(dotLaster);

            // Lưu file vào thư mục uploads
            String filePath = uploadPath + fileName;
            System.out.println("Lưu thành công" + filePath);
            filePart.write(filePath);

            // Lưu tên file xuống CSDL
            khachhang.setHinhAvatar(fileName);
            KhachHangDAO khdao = new KhachHangDAO();
            if (khdao.updateAvatar(khachhang) > 0) {
                System.out.println("Lưu tên file xuống CSDL thành công");
                error = "Đã cập nhật Avatar thành công";
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin?hanhdong=user");
    }

    private String uploadNewImage(HttpServletRequest request, String oldImage) throws ServletException, IOException {
        Part filePart = request.getPart("hinhanhsanpham");

        if (filePart == null || filePart.getSize() <= 0) {
            return oldImage; // Không có ảnh mới -> giữ nguyên ảnh cũ
        }

        String folderName = "/GUI/imgsanpham";
        String uploadPath = getServletContext().getRealPath(folderName);

        if (uploadPath == null) {
            uploadPath = "C:\\path\\to\\your\\project" + folderName; // Cập nhật theo đường dẫn thực tế
        }

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Kiểm tra file hợp lệ (chỉ chấp nhận ảnh)
        if (!fileName.matches(".*\\.(jpg|jpeg|png)$")) {
            return oldImage;
        }

        // Đổi tên file theo thời gian để tránh trùng
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        fileName = "product_" + System.currentTimeMillis() + fileExtension;

        // Lưu file vào thư mục uploads
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        return fileName; // Trả về tên file mới
    }

    private String uploadNewImageAvatar(HttpServletRequest request, String oldImage) throws ServletException, IOException {
        Part filePart = request.getPart("hinhavatar");

        if (filePart == null || filePart.getSize() <= 0) {
            return oldImage; // Không có ảnh mới -> giữ nguyên ảnh cũ
        }

        String folderName = "uploads";
        String uploadPath = getServletContext().getRealPath("/") + folderName;

        if (uploadPath == null) {
            uploadPath = "C:\\path\\to\\your\\project\\uploads";
        }

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Kiểm tra file hợp lệ (chỉ chấp nhận ảnh)
        if (!fileName.matches(".*\\.(jpg|jpeg|png)$")) {
            return oldImage;
        }

        // Đổi tên file theo thời gian để tránh trùng
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        fileName = "product_" + System.currentTimeMillis() + fileExtension;

        // Lưu file vào thư mục uploads
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        return fileName; // Trả về tên file mới
    }

    public void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            // Lấy dữ liệu từ request
            String masanpham = request.getParameter("masanpham");
            String tensanpham = request.getParameter("tensanpham");
            String hinhanhsanpham = request.getParameter("hinhanhsanpham");
            String mausac = request.getParameter("mausac");
            String kichco = request.getParameter("kichco");
            String categoryID = request.getParameter("categoryID");
            String mota = request.getParameter("mota");
            // Xử lý số lượng và giá cả
            int soluong = 1;
            double gianhap = 0;
            double giaban = 0;
            int giamgia = 0;

            try {
                String soluongStr = request.getParameter("soluong");
                String gianhapStr = request.getParameter("gianhap");
                String giabanStr = request.getParameter("giaban");
                String giamgiaStr = request.getParameter("giamgia");

                if (soluongStr != null && !soluongStr.trim().isEmpty()) {
                    soluong = Integer.parseInt(soluongStr);
                }

                if (gianhapStr != null && !gianhapStr.trim().isEmpty()) {
                    gianhap = Double.parseDouble(gianhapStr);
                }

                if (giabanStr != null && !giabanStr.trim().isEmpty()) {
                    giaban = Double.parseDouble(giabanStr);
                }

                if (giamgiaStr != null && !giamgiaStr.trim().isEmpty()) {
                    giamgia = Integer.parseInt(giamgiaStr);
                }

            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/admin?hanhdong=addProduct");
                return;
            }
            // Chỉ kiểm tra masanpham và tensanpham, các trường khác có thể null
            if (masanpham == null || masanpham.trim().isEmpty()
                    || tensanpham == null || tensanpham.trim().isEmpty() || mausac == null
                    || mausac.trim().isEmpty() || soluong <= 0 || categoryID == null
                    || categoryID.trim().isEmpty() || kichco == null || kichco.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/admin?hanhdong=createproduct");
                return;
            }
            // Kiểm tra sản phẩm có tồn tại không
            SanPhamDAO spdao = new SanPhamDAO();
            if (spdao.selectById(masanpham) != null) {
                response.sendRedirect(request.getContextPath() + "/admin?hanhdong=addProduct");
                return;
            }

            // Tạo đối tượng sản phẩm (các giá trị null được phép)
            SanPham sp = new SanPham(masanpham, tensanpham, hinhanhsanpham, mausac, kichco, soluong, categoryID, gianhap, giaban, giamgia, mota);
            SanPham sp1 = new SanPham(masanpham, kichco, soluong);
            AdminDAO adminDAO = new AdminDAO();

            // Thêm sản phẩm vào database
            if (adminDAO.insertSanPham(sp) == 1) {
                adminDAO.insertSize(sp1);
                request.setAttribute("sp", sp);
                addhinhanhsanpham(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendRedirect(request.getContextPath() + "/admin?hanhdong=addProduct");
            } catch (Exception ex) {
                ex.printStackTrace();
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

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            String masanpham = request.getParameter("masanpham");
            String size = request.getParameter("size");
            System.out.println(masanpham);
            System.out.println(size);
            AdminDAO adao = new AdminDAO();
            SanPham sp = new SanPham();

            sp.setMasanpham(masanpham);
            sp.setKichco(size);

            if (sp != null) {
                if (adao.deleteSanPhamSize(sp) == true) {
                    adao.delete(sp);
                }
                System.out.println("Xoa thanh cong");
            }
            response.sendRedirect(request.getContextPath() + "/admin?hanhdong=load");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editSanPhamDetails(HttpServletRequest request, HttpServletResponse response) {
        try {
            String massanpham = request.getParameter("masanpham");
            SanPhamDAO sdao = new SanPhamDAO();
            AdminDAO adao = new AdminDAO();
            SanPham sp = sdao.selectByIdSanPhamSize(massanpham);
            List<Categories> categories = adao.selectType();
            if (sp != null) {
                request.setAttribute("sp", sp);
                request.setAttribute("categories", categories);
                request.setAttribute("nextaction", "updatesanpham");
                request.getRequestDispatcher("/admin/updateproductdetails.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSanPhamDetails(HttpServletRequest request, HttpServletResponse response) {
        String masanpham = request.getParameter("masanpham");
        String tensanpham = request.getParameter("tensanpham");
        String oldImage = request.getParameter("oldImage"); // Lấy ảnh cũ từ form
        String mausac = request.getParameter("mausac");
        String kichco = request.getParameter("size");
        String categoryID = request.getParameter("categoryID");

        int soluong = 0;
        double gianhap = 0, giaban = 0;
        int giamgia = 0;

        try {
            soluong = Integer.parseInt(request.getParameter("soluong"));
            gianhap = Double.parseDouble(request.getParameter("gianhap"));
            giaban = Double.parseDouble(request.getParameter("giaban"));
            giamgia = Integer.parseInt(request.getParameter("giamgia"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String mota = request.getParameter("mota");

        try {
            // Gọi hàm upload ảnh mới, nếu không có ảnh mới sẽ giữ ảnh cũ
            String hinhanhsanpham = uploadNewImage(request, oldImage);

            // Cập nhật sản phẩm
            SanPham sp = new SanPham(masanpham, tensanpham, hinhanhsanpham, mausac, categoryID, gianhap, giaban, giamgia, mota);
            SanPham sp1 = new SanPham(masanpham, kichco, soluong);

            SanPhamDAO spdao = new SanPhamDAO();
            AdminDAO adao = new AdminDAO();

            if (spdao.update(sp) == 1) {
                adao.updateSanPhamSize(sp1);
                request.setAttribute("sp", sp);
                request.setAttribute("sp1", sp1);
            }

            response.sendRedirect(request.getContextPath() + "/admin?hanhdong=load");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void user(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<KhachHang> userList = new ArrayList<KhachHang>();
            AdminDAO aO = new AdminDAO();
            userList = aO.userList();
            if (userList != null) {
                request.setAttribute("userList", userList);
                request.getRequestDispatcher("/admin/user.jsp").forward(request, response);
            }
        } catch (Exception e) {
        }
    }

    private void editUserDetails(HttpServletRequest request, HttpServletResponse response) {
        try {
            String maKhachHang = request.getParameter("maKhachHang");
            KhachHangDAO khdao = new KhachHangDAO();
            KhachHang khachhang = khdao.selectById(maKhachHang);
            if (khachhang != null) {
                request.setAttribute("khachhang", khachhang);
                request.setAttribute("nextaction", "updateuser");
                request.getRequestDispatcher("/admin/updateuserdetails.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUserDetails(HttpServletRequest request, HttpServletResponse response) {
        String maKhachHang = request.getParameter("maKhachHang");
        String tenDangNhap = request.getParameter("tenDangNhap");
        String matKhau = request.getParameter("matKhau");
        String hoVaTen = request.getParameter("hoVaTen");
        String gioiTinh = request.getParameter("gioiTinh");
        String ngaySinhStr = request.getParameter("ngaySinh");
        String oldImage = request.getParameter("oldImage"); // Lấy ảnh cũ từ form
        java.sql.Date ngaySinh = null;

        if (ngaySinhStr != null && !ngaySinhStr.trim().isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                java.util.Date utilDate = sdf.parse(ngaySinhStr);
                ngaySinh = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String soDienThoai = request.getParameter("soDienThoai");
        String email = request.getParameter("email");
        String quocTich = request.getParameter("quocTich");
        String diaChiKhachHang = request.getParameter("diaChiKhachHang");
        String diaChiNhanHang = request.getParameter("diaChiNhanHang");

        int isAdmin = 0;
        try {
            isAdmin = Integer.parseInt(request.getParameter("isAdmin"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            // Gọi hàm upload ảnh mới, nếu không có ảnh mới sẽ giữ ảnh cũ
            String hinhavatar = uploadNewImageAvatar(request, oldImage);

            KhachHang khachHang = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, ngaySinh, soDienThoai, email, quocTich, diaChiKhachHang, diaChiNhanHang, hinhavatar, isAdmin);
            KhachHangDAO khdao = new KhachHangDAO();
            if (khdao.update(khachHang) == 1) {
                request.setAttribute("khachHang", khachHang);
            }
//            response.sendRedirect("/project-final/admin?hanhdong=user");
            response.sendRedirect(request.getContextPath() + "/admin?hanhdong=user");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createuser(HttpServletRequest request, HttpServletResponse response) {
        try {
            KhachHang kh = new KhachHang();

            request.setAttribute("nextaction", "adduser");
            request.setAttribute("kh", kh);
            response.sendRedirect(request.getContextPath() + "/admin/adduser.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adduser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Random rd = new Random();
        String maKhachHang = System.currentTimeMillis() + rd.nextInt(1000) + "";
        String tenDangNhap = request.getParameter("tenDangNhap");
        String matKhau = request.getParameter("matKhau");
        String hoVaTen = request.getParameter("hoVaTen");
        String gioiTinh = request.getParameter("gioiTinh");
        String hinhavatar = request.getParameter("hinhavatar");

        String ngaySinhStr = request.getParameter("ngaySinh"); // Lấy dữ liệu từ form
        java.sql.Date ngaySinh = null; // Khởi tạo giá trị mặc định

        if (ngaySinhStr != null && !ngaySinhStr.trim().isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = sdf.parse(ngaySinhStr);
                ngaySinh = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                System.out.println("Lỗi: Định dạng ngày tháng không hợp lệ.");
            }
        }

        String soDienThoai = request.getParameter("soDienThoai");
        String email = request.getParameter("email");
        String quocTich = request.getParameter("quocTich");
        String diaChiKhachHang = request.getParameter("diaChiKhachHang");
        String diaChiNhanHang = request.getParameter("diaChiNhanHang");
        String isAdminStr = request.getParameter("isAdmin");
        // Kiểm tra giá trị null trước khi tạo đối tượng KhachHang
        if (tenDangNhap == null || tenDangNhap.trim().isEmpty()
                || matKhau == null || matKhau.trim().isEmpty()) {
            System.out.println("ten dang nhap == null || mat khau == null");
            response.sendRedirect(request.getContextPath() + "/admin?hanhdong=createuser");
            return;
        }

        KhachHangDAO khdao = new KhachHangDAO();
        if (khdao.isUsernameExists(tenDangNhap)) {
            System.out.println("ten dang nhap da ton tai");
            response.sendRedirect(request.getContextPath() + "/admin?hanhdong=createuser");
            return;
        }

        int isAdmin = 0;
        if (isAdminStr != null && !isAdminStr.trim().isEmpty()) {
            try {
                isAdmin = Integer.parseInt(isAdminStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        try {
            KhachHang khachHang = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, ngaySinh, soDienThoai, email, quocTich, diaChiKhachHang, diaChiNhanHang, hoVaTen, isAdmin);
            if (khdao.insert(khachHang) == 1) {
                request.setAttribute("khachHang", khachHang);
                addhinhavatar(request, response);
            }
            response.sendRedirect(request.getContextPath() + "/admin?hanhdong=user");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteuser(HttpServletRequest request, HttpServletResponse response) {
        try {
            String maKhachHang = request.getParameter("maKhachHang");
            System.out.println(maKhachHang);
            AdminDAO adao = new AdminDAO();
            KhachHang kh = new KhachHang();

            kh.setMaKhachHang(maKhachHang);

            if (kh != null) {
                adao.deleteUser(kh);
                System.out.println("Xoa thanh cong");
            }
            response.sendRedirect(request.getContextPath() + "/admin?hanhdong=user");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void order(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            List<DonHang> listDonHang = new ArrayList<DonHang>();

            AdminDAO adao = new AdminDAO();
            listDonHang = adao.selectAllDonHang();

            System.out.println(listDonHang.size());

            request.setAttribute("listDonHang", listDonHang);
            request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void orderDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String madonhang = request.getParameter("madonhang");
            System.out.println(madonhang);

            List<OrderDetails> listOderDetails = new ArrayList<OrderDetails>();
            KhachHang khachHang = new KhachHang();
            DonHang donHang = new DonHang();

            AdminDAO adao = new AdminDAO();
            DonHangDAO dAO = new DonHangDAO();

            listOderDetails = adao.selectOrderSanPham(madonhang);

            khachHang = adao.selectKhachHangByOrderID(madonhang);
            donHang = dAO.selectById(madonhang);

            request.setAttribute("donHang", donHang);
            request.setAttribute("khachHang", khachHang);
            request.setAttribute("listOderDetails", listOderDetails);
            request.getRequestDispatcher("/admin/orderdetails.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editKhachHangInOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<OrderDetails> listOderDetails = new ArrayList<OrderDetails>();
            KhachHang kh = new KhachHang();
            DonHang dh = new DonHang();

            String makhachhang = request.getParameter("makhachhang");
            String madonhang = request.getParameter("madonhang");

            AdminDAO adao = new AdminDAO();
            listOderDetails = adao.selectOrderSanPham(madonhang);

            KhachHangDAO khdao = new KhachHangDAO();
            kh = khdao.selectById(makhachhang);

            DonHangDAO ddao = new DonHangDAO();
            dh = ddao.selectById(madonhang);

            request.setAttribute("kh", kh);
            request.setAttribute("dh", dh);
            request.setAttribute("listOderDetails", listOderDetails);
            request.setAttribute("nextaction", "updateKhachHangInOrder");
            request.getRequestDispatcher("/admin/updateKhachHangInOrder.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateKhachHangInOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            String makhachhang = request.getParameter("makhachhang");
            String hovaten = request.getParameter("hovaten");
            String sodienthoai = request.getParameter("sodienthoai");
            String email = request.getParameter("email");
            String diachigiaohang = request.getParameter("diachigiaohang");

            String madonhang = request.getParameter("madonhang");

            KhachHang kh = new KhachHang();
            kh.setMaKhachHang(makhachhang);
            kh.setHoVaTen(hovaten);
            kh.setSoDienThoai(sodienthoai);
            kh.setEmail(email);

            DonHang dh = new DonHang();
            dh.setMadonhang(madonhang);
            dh.setDiachigiaohang(diachigiaohang);

            AdminDAO adao = new AdminDAO();
            adao.updateDiaChiGiaoHang(dh.getMadonhang(), dh.getDiachigiaohang());
            adao.updateKhachHangInOrder(kh);

            response.sendRedirect(request.getContextPath() + "/admin?hanhdong=orderDetails&madonhang=" + madonhang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteorder(HttpServletRequest request, HttpServletResponse response) {
        try {
            String orderID = request.getParameter("orderID");

            ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO();
            List<SanPham> listProductOrdered = chiTietDonHangDAO.selectProductToRefundSize(orderID);
            chiTietDonHangDAO.delete(orderID);

            DonHangDAO donHangDAO = new DonHangDAO();
            donHangDAO.delete(orderID);

            SanPhamDAO sanPhamDAO = new SanPhamDAO();
            for (SanPham sanPham : listProductOrdered) {
                sanPhamDAO.refundSize(sanPham);
            }

            response.sendRedirect(request.getContextPath() + "/admin?hanhdong=order");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
