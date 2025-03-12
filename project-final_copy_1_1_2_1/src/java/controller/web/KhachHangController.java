package controller.web;

import database.ChiTietDonHangDAO;
import database.DonHangDAO;
import database.GioHangDAO;
import database.KhachHangDAO;
import database.KhachHang_MaGiamGiaDAO;
import database.MaGiamGiaDAO;
import database.OrderHistoryDAO;
import database.SanPhamDAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.ChiTietDonHang;
import model.GioHang;
import model.Item;
import model.KhachHang;
import model.KhachHang_MaGiamGia;
import model.MaGiamGia;
import model.Order;
import model.OrderHistory;
import model.SanPham;
import utils.Email;
import utils.OTP;
import utils.Time;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)

@WebServlet(name = "KhachHangController", urlPatterns = {"/khach-hang"})
public class KhachHangController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hanhdong = request.getParameter("hanhdong");
        System.out.println(hanhdong);
        if (hanhdong.equals("register")) {
            register(request, response);
        } else if (hanhdong.equals("login")) {
            login(request, response);
        } else if (hanhdong.equals("updateProfile")) {
            updateProfile(request, response);
        } else if (hanhdong.equals("logout")) {
            logout(request, response);
        } else if (hanhdong.equals("resetpassword")) {
            resetPassword(request, response);
        } else if (hanhdong.equals("viaemailbeforeresetpassword")) {
            viaemailbeforeresetpassword(request, response);
        } else if (hanhdong.equals("updateAvatar")) {
            updateavatar(request, response);
        } else if (hanhdong.equals("buynow")) {
            buynow(request, response);
        } else if (hanhdong.equals("addtocart")) {
            addtocart(request, response);
        } else if (hanhdong.equals("minusonecart")) {
            minusonecart(request, response);
        } else if (hanhdong.equals("plusonecart")) {
            plusonecart1(request, response);
        } else if (hanhdong.equals("deleteproductoutcart")) {
            deleteproductoutcart1(request, response);
        } else if (hanhdong.equals("showcart")) {
            showcart(request, response);
        } else if (hanhdong.equals("checkout")) {
            checkout(request, response);
        } else if (hanhdong.equals("myorder")) {
            myorder(request, response);
        } else if (hanhdong.equals("cancelorder")) {
            cancelorder(request, response);
        } else if (hanhdong.equals("myvoucher")) {
            myvoucher(request, response);
        } else if (hanhdong.endsWith("getvoucher")) {
            getvoucher(request, response);
        }

    }

    private void register(HttpServletRequest request, HttpServletResponse response) {
        String error = "";

        String url = "";
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmpassword = request.getParameter("confirmpassword");
            String email = request.getParameter("email");

            // set attribute trả về để không phải nhập lại từng cái nếu sai
            request.setAttribute("username", username);
            request.setAttribute("email", email);

            // Kiểm tra khách hàng có nhập thông tin hay chưa
            if (username.length() == 0) {
                error += "Tên đăng nhập không được để trống";
            }
            if (password.length() == 0) {
                error += "Password không được để trống";
            }
            if (confirmpassword.length() == 0) {
                error += "Confirm Password không được để trống";
            }
            if (email.length() == 0) {
                error += "Email không được để trống";
            }

            // Nếu tên đăng nhập bị trùng (chưa làm)
            // Nếu email bị trùng (chưa làm)
            // Kiểm tra regex coi có phải là dạng email hay không (chưa làm)
            // Kiểm tra password và confirmpassword 
            if (password.equals(confirmpassword) == false) {
                error += "Mật khẩu và mật khẩu nhập lại KHÔNG TRÙNG KHỚP. Vui lòng kiểm tra lại!";
            }

            // mã hóa mật khẩu (chưa làm)
            //Trả về báo lỗi nếu có
            if (error.length() > 0) {
                request.setAttribute("error", error);
                // Quay lại trang đăng nhập
                url = "/khachhang/register.jsp";
            } else { // nếu không có lỗi thì tạo đối tượng Khách Hàng lưu xuống
                // Tạo ra mã khách hàng
                Random rd = new Random();
                String maKhachHang = System.currentTimeMillis() + rd.nextInt(1000) + "";
                KhachHang kh = new KhachHang(maKhachHang, username, password, email);

                KhachHangDAO khachHangDao = new KhachHangDAO();
                khachHangDao.insert(kh);
                url = "/khachhang/registersuccess.jsp";

            }

            // Tạo KhachHang lưu xuống CSDL
        } catch (Exception e) {

            e.printStackTrace();
        }

        try {
            System.out.println(url);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String error = "";

        String url = "";
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // set attribute trả về để không phải nhập lại từng cái nếu sai
            request.setAttribute("username", username);

            // Kiểm tra khách hàng có nhập thông tin hay chưa
            if (username.length() == 0) {
                error += "Bạn chưa nhập tên đăng nhập";
            }
            if (password.length() == 0) {
                error += "Bạn chưa nhập Password";
            }

            // mã hóa mật khẩu (chưa làm)
            //Trả về báo lỗi nếu có
            if (error.length() > 0) {
                request.setAttribute("error", error);
                // Quay lại trang đăng nhập
                url = "/khachhang/loginsuccess.jsp";
            } else { // nếu không có lỗi thì Chuyển sang trang chính              
                KhachHang khachHang = new KhachHang(username, password);
                KhachHangDAO khachHangDAO = new KhachHangDAO();
                khachHang = khachHangDAO.selectByUsernameAndPassword(khachHang);
                // Kiểm tra kh được lấy lên từ CSDL có đúng hay không
                HttpSession session = request.getSession();
                if (khachHang != null) {
                    if (khachHang.getIsAdmin() == 1) {
                        url = "/admin/index.jsp";
                    } else {
                        url = "/web";
                    }

                    session.setAttribute("khachHang", khachHang);
                } else {
                    url = "/khachhang/login.jsp";
                }

            }

            // Tạo KhachHang lưu xuống CSDL
        } catch (Exception e) {

            e.printStackTrace();
        }

        // Điều hướng chương trình đừng sửa gì phần này
        System.out.println("url=" + url);
        try {
            RequestDispatcher rdd = getServletContext().getRequestDispatcher(url);
            rdd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maKhachHang = request.getParameter("maKhachHang");
        try {
            if (maKhachHang == null || maKhachHang.isEmpty()) {
                request.getRequestDispatcher("khachhang/updateprofile.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi để kiểm tra nếu cần
        }

        String hoVaTen = request.getParameter("hoVaTen");
        String soDienThoai = request.getParameter("soDienThoai");
        String ngaySinhStr = request.getParameter("ngaySinh");
        String gioiTinh = request.getParameter("gioiTinh");
        String email = request.getParameter("email");
        String quocGia = request.getParameter("quocGia");
        String diaChiKhachHang = request.getParameter("diaChiKhachHang");
        String diaChiNhanHang = request.getParameter("diaChiNhanHang");

        Date ngaySinh = null;
        try {
            ngaySinh = Date.valueOf(ngaySinhStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("hoVaTen", hoVaTen);
        request.setAttribute("gioiTinh", gioiTinh);
        request.setAttribute("ngaySinh", ngaySinh);
        request.setAttribute("soDienThoai", soDienThoai);
        request.setAttribute("email", email);
        request.setAttribute("diaChiKhachHang", diaChiKhachHang);
        request.setAttribute("diaChiNhanHang", diaChiNhanHang);
        request.setAttribute("quocGia", quocGia);

        System.out.println(gioiTinh);
        KhachHang kh = new KhachHang(maKhachHang, hoVaTen, gioiTinh, ngaySinh, soDienThoai, email, quocGia, diaChiKhachHang, diaChiNhanHang);
        KhachHangDAO khachHangDao = new KhachHangDAO();
        int isUpdate = khachHangDao.update(kh);

        HttpSession session = request.getSession();
        session.setAttribute("khachHang", khachHangDao.selectById(kh));

        request.getRequestDispatcher("/khachhang/update.jsp").forward(request, response);

        String url = "./khachhang/registersuccess.jsp";
        try {
            response.sendRedirect(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();

            // Hủy bỏ session
            session.invalidate();

            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();

            response.sendRedirect(url + "/web");
        } catch (Exception e) {
            System.out.println("Log-out thất bại");
            e.printStackTrace();
        }
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) {
        try {
            String password = request.getParameter("password");
            String newPassword = request.getParameter("newPassword");
            String newPasswordConfirm = request.getParameter("newPasswordConfirm");

            String error = "";
            String url = "";

            HttpSession session = request.getSession();
            Object obj = session.getAttribute("khachHang");
            KhachHang khachHang = null;
            if (obj != null) {
                khachHang = (KhachHang) obj;
            }

            if (password.length() == 0) {
                error += "password không được để trống";
            }

            if (password.equals(khachHang.getMatKhau()) == false) {
                error += "password hiện tại không đúng";
            }

            if (newPassword.length() == 0) {
                error += "NewPassword không được để trống";
            }

            if (newPasswordConfirm.length() == 0) {
                error += "NewPassword không được để trống";
            }

            if (!newPassword.equals(newPasswordConfirm)) {
                error += "NewPassword và NewPasswordConfirm KHÔNG KHỚP";
            }

            request.setAttribute("error", error);

            if (error.length() > 0) {
                url = "/khachhang/resetpassword.jsp";
            } else {
                url = "/khachhang/viaemailbeforeresetpassword.jsp";

                // Cập nhật newPassword để qua viamail-resetpassword có thể lưu xuống
//                khachHang.setMatKhau(newPassword);
                // Gửi mail để khách hàng nhận OTP
                // Cập nhật OTP và thời gian xác thực xuống CSDL để qua trang VIA check
                String maXacThuc = OTP.getOTP();
                String timeLate = Time.timeNowPlus_X_minutes(10);

                khachHang.setMaXacThuc(maXacThuc);
                khachHang.setThoiGianHieuLucMaXacThuc(timeLate);
                khachHang.setTrangThaiXacThuc("0");

                session.setAttribute("khachHang", khachHang);
                request.setAttribute("newPassword", newPassword);
                KhachHangDAO dao = new KhachHangDAO();
                if (dao.updateMaXacThuc(khachHang) > 0) {
                    System.out.println("Cập nhật OTP thành công");

                    // gửi OTP đến mail
                    Email.sendEmailTo(khachHang.getEmail(), khachHang.getMaXacThuc());
                }
            }

            System.out.println("error=" + error);
            System.out.println("url=" + url);

//            request.setAttribute("khachHang", khachHang);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);

        } catch (Exception e) {
            System.out.println("Lỗi ở resetPassword");
            e.printStackTrace();
        }
    }

    private void viaemailbeforeresetpassword(HttpServletRequest request, HttpServletResponse response) {
        try {
            String sortOTP = request.getParameter("sortOTP");
            String newPasswordtemp = request.getParameter("newPassword");
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("khachHang");
            KhachHang khachHang = null;
            if (obj != null) {
                khachHang = (KhachHang) obj;
            }

            String url = "";
            String error = "";

//            String timeNowPlus15Minutes = Time.timeNowPlus_X_minutes(15);
//            System.out.println("Timenow: " +timeNow);
//            System.out.println("TimeKahchHnag: " +khachHang.getThoiGianHieuLucMaXacThuc());
            String timeNow = Time.getTimeNow();

            if (Time.compareTime(timeNow, khachHang.getThoiGianHieuLucMaXacThuc()) > 0) {
                error += "Đã hết thời gian hiệu lực của mã OTP";
                url = "/khachhang/viaemailbeforeresetpassword.jsp";
            }

            if (!sortOTP.equals(khachHang.getMaXacThuc())) {
                System.out.println("Đã xác thực THẤT BẠI.");
                error = "SAI OTP";
                url = "/khachhang/viaemailbeforeresetpassword.jsp";
            }

            if (error.length() > 0) {
                url = "/khachhang/viaemailbeforeresetpassword.jsp";
            } else {
                // Cập nhật thông tin xuống CSDL khi người dùng nhập đúng OTP
                KhachHangDAO dao = new KhachHangDAO();

                // Set mật khẩu mới và trạng thái xác thực = 1
                khachHang.setMatKhau(newPasswordtemp);
                dao.updateNewPassword(khachHang);

                System.out.println("Đã xác thực thành công. Mật khẩu đã được đổi");
                error = "Đã xác thực thành công. Mật khẩu đã được đổi";
                url = "/khachhang/resetpassword.jsp";
            }

            request.setAttribute("error", error);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Lỗi ở viaemailbeforeresetpassword");
            e.printStackTrace();
        }
    }

    protected void updateavatar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        Part filePart = request.getPart("avatar");
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

            HttpSession session = request.getSession();
            Object obj = session.getAttribute("khachHang");
            KhachHang khachHang = null;
            if (obj != null) {
                khachHang = (KhachHang) obj;
            }

            //Đổi lại tên file bằng tên của tên đăng nhập để tránh bị trùng
            int dotLaster = fileName.lastIndexOf(".");
            fileName = khachHang.getTenDangNhap() + fileName.substring(dotLaster);

            // Lưu file vào thư mục uploads
            String filePath = uploadPath + fileName;
            System.out.println("Lưu thành công" + filePath);
            filePart.write(filePath);

            // Lưu tên file xuống CSDL
            khachHang.setHinhAvatar(fileName);
            KhachHangDAO dao = new KhachHangDAO();
            if (dao.updateAvatar(khachHang) > 0) {
                System.out.println("Lưu tên file xuống CSDL thành công");
                error = "Đã cập nhật Avatar thành công";
            }
        }

        request.getRequestDispatcher("/khachhang/update.jsp").forward(request, response);
    }

    private void addtocart(HttpServletRequest request, HttpServletResponse response) {
        try {

            HttpSession session = request.getSession(false);
            KhachHang kh = (KhachHang) session.getAttribute("khachHang");

            if (kh == null) {
                response.sendRedirect(request.getContextPath() + "/khachhang/login.jsp");
            } else {
                String masanpham = request.getParameter("masanpham");
                String kichco = request.getParameter("size");
                String soluong = request.getParameter("soluong");
//            String nextpage = request.getParameter("nextpage");

                SanPhamDAO sanPhamDAO = new SanPhamDAO();
                SanPham sanPham = sanPhamDAO.selectById(masanpham);

                sanPham.setKichco(kichco);
//            sanPham.setSoluong(Integer.parseInt(soluong));
                System.out.println("Ma san pham: " + masanpham);
                System.out.println("kichco: " + kichco);

                String error = "";
//            SanPham sanpham = null;

                if (session.getAttribute("order") == null) {

                    Order order = new Order();
                    List<Item> list = new ArrayList<Item>();
                    Item item = new Item();
                    item.setSanpham(sanPham);
                    sanPham = item.getSanpham();
                    item.setSoluong(Integer.parseInt(soluong));
                    System.out.println(item.toString());
                    list.add(item);
                    order.setList(list);

                    session.setAttribute("order", order);
                } else {
                    Order order = (Order) session.getAttribute("order");
                    System.out.println("order2 " + order == null);
                    List<Item> list = order.getList();
                    boolean check = false;
                    for (Item item : list) {
                        if (item.getSanpham().getMasanpham().equals(masanpham) && item.getSanpham().getKichco().equals(kichco)) {
                            int soLuongSanPhamTrongKho = sanPhamDAO.kiemTraSoLuongSanPham(item.getSanpham());
                            if (soLuongSanPhamTrongKho >= item.getSoluong() + 1) {
                                item.setSoluong(item.getSoluong() + 1);
                            } else {
                                error = " <br> Không thể thêm! Sản phẩm trong giỏ hàng của bạn đã vượt quá số lượng trong kho";
                                item.setSoluong(item.getSoluong());
                            }
                            check = true;
                            break;
                        }
                    }

                    if (check == false) {
                        Item item = new Item();
                        item.setSanpham(sanPham);
//                    sanpham = item.getSanpham();
                        item.setSoluong(Integer.parseInt(soluong));
                        list.add(item);

                    }
                    session.setAttribute("order", order);

                }

//            if (nextpage.equals("product-details.jsp")) {
                SanPhamDAO dao = new SanPhamDAO();
                List<SanPham> list = dao.selectAll();
                List<SanPham> listsoluongsize = dao.selectsizecuamasanphamconhang(masanpham);

                request.setAttribute("list", list);
                request.setAttribute("sanpham", sanPham);
                request.setAttribute("error", error);
                request.setAttribute("listsoluongsize", listsoluongsize);
                request.getRequestDispatcher("/GUI/product-details.jsp").forward(request, response);
//            } else {
//                response.sendRedirect(request.getContextPath() + "/san-pham");
//            }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    private void minusonecart(HttpServletRequest request, HttpServletResponse response) {
        try {

            HttpSession session = request.getSession(false);
            Order order = (Order) session.getAttribute("order");

            String masanpham = request.getParameter("masanpham");
            String size = request.getParameter("size");

            List<Item> list = order.getList();

            for (Item item : list) {
                if (item.getSanpham().getMasanpham().equals(masanpham) && item.getSanpham().getKichco().equals(size)) {
                    if (item.getSoluong() == 1) {
                        deleteproductoutcart1(request, response);
                    }
                    item.setSoluong(item.getSoluong() - 1);
                    break;
                }

            }

            session.setAttribute("order", order);
            request.getRequestDispatcher("/khachhang/cart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void plusonecart(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("khachHang");
            KhachHang kh = null;
            if (obj != null) {
                kh = (KhachHang) obj;
            }

            String error = "";
            String url = "";

            if (kh == null) {
                url = "/khachhang/login.jsp";
            } else {
                String masanpham = request.getParameter("masanpham");
                String size = request.getParameter("size");
                GioHang gioHang = new GioHang(kh.getMaKhachHang(), masanpham, size, 1);
                GioHangDAO dao = new GioHangDAO();
                dao.addOneProduct(gioHang);

                url = "/khachhang/cart.jsp";
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void plusonecart1(HttpServletRequest request, HttpServletResponse response) {
        try {

            HttpSession session = request.getSession(false);
            Order order = (Order) session.getAttribute("order");

            String masanpham = request.getParameter("masanpham");
            String size = request.getParameter("size");

            List<Item> list = order.getList();
            String error = "";
            for (Item item : list) {
                if (item.getSanpham().getMasanpham().equals(masanpham) && item.getSanpham().getKichco().equals(size)) {
                    SanPhamDAO sanPhamDAO = new SanPhamDAO();

                    int soLuongSanPhamTrongKho = sanPhamDAO.kiemTraSoLuongSanPham(item.getSanpham());
                    System.out.println("soLuongSanPhamTrongKho" + soLuongSanPhamTrongKho);
                    if (soLuongSanPhamTrongKho >= item.getSoluong() + 1) {
                        item.setSoluong(item.getSoluong() + 1);
                    } else {
                        System.out.println(item.getSoluong());
                        error = "Sản phẩm đã vượt quá số lượng trong kho";
                    }
                    break;
                }

            }
            session.setAttribute("error", error);
            request.setAttribute("error", error);
            request.getRequestDispatcher("/khachhang/cart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteproductoutcart(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("khachHang");
            KhachHang kh = null;
            if (obj != null) {
                kh = (KhachHang) obj;
            }

            String error = "";
            String url = "";

            if (kh == null) {
                url = "/khachhang/login.jsp";
            } else {
                String masanpham = request.getParameter("masanpham");
                String size = request.getParameter("size");
                GioHang gioHang = new GioHang(kh.getMaKhachHang(), masanpham, size, 1);
                GioHangDAO dao = new GioHangDAO();
                dao.deleteproductoutcart(gioHang);

                url = "/khachhang/cart.jsp";
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteproductoutcart1(HttpServletRequest request, HttpServletResponse response) {
        try {

            HttpSession session = request.getSession(false);
            Order order = (Order) session.getAttribute("order");

            String masanpham = request.getParameter("masanpham");
            String size = request.getParameter("size");

            List<Item> list = order.getList();

            for (Item item : list) {
                if (item.getSanpham().getMasanpham().equals(masanpham) && item.getSanpham().getKichco().equals(size)) {
                    if (list.remove(item));
                    break;
                }

            }

            session.setAttribute("order", order);
            request.getRequestDispatcher("/khachhang/cart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showcart(HttpServletRequest request, HttpServletResponse response) {

    }

    private void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession();
            KhachHang khachhang = (KhachHang) session.getAttribute("khachHang");
//        Order order = (Order) session.getAttribute("order");
            System.out.println("Hellloooo");

            if (khachhang == null) {
                response.sendRedirect("khachhang/login.jsp");
                return;
            }

            KhachHang_MaGiamGiaDAO dao = new KhachHang_MaGiamGiaDAO();
            List<MaGiamGia> listMaGiamGia = dao.selectMaGiamGiaCuaKhachHang(khachhang.getMaKhachHang());

            request.setAttribute("listMaGiamGia", listMaGiamGia);
            request.setAttribute("prevaction", "checkout");
            request.getRequestDispatcher("khachhang/checkout.jsp").forward(request, response);

        } catch (IOException ex) {
            System.out.println("Lỗi ở gọi check out từ cart.jsp");
            ex.printStackTrace();
        }
    }

    private void buynow(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession session = request.getSession();
            KhachHang khachhang = (KhachHang) session.getAttribute("khachHang");
//        Order order = (Order) session.getAttribute("order");
            System.out.println("buy now khachhang");

            if (khachhang == null) {
                response.sendRedirect("khachhang/login.jsp");
                return;
            }

            String masanpham = request.getParameter("masanpham"); // "THT-002"
            String soluong = request.getParameter("soluong");
            String size = request.getParameter("size");

            SanPhamDAO sanPhamDAO = new SanPhamDAO();
            SanPham sp = sanPhamDAO.selectById(masanpham);
            sp.setKichco(size);

            Item item = new Item();
            item.setSanpham(sp);
            item.setSoluong(1);

            List<Item> listtemp = new ArrayList<Item>();
            listtemp.add(item);

            Order order = new Order();
            order.setList(listtemp);

            KhachHang_MaGiamGiaDAO dao = new KhachHang_MaGiamGiaDAO();
            List<MaGiamGia> listMaGiamGia = dao.selectMaGiamGiaCuaKhachHang(khachhang.getMaKhachHang());

            request.setAttribute("listMaGiamGia", listMaGiamGia);
            request.setAttribute("order", order);
            request.setAttribute("prevaction", "buynow");

            request.getRequestDispatcher("khachhang/checkout.jsp").forward(request, response);

        } catch (IOException ex) {
            System.out.println("Lỗi ở gọi check out từ cart.jsp");
            ex.printStackTrace();
        }
    }

    private void myorder(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            KhachHang khachhang = (KhachHang) session.getAttribute("khachHang");

            List<OrderHistory> list = new ArrayList<OrderHistory>();
            if (khachhang != null) {

                OrderHistoryDAO dao = new OrderHistoryDAO();
                list = dao.selectByIdMaKhachHang(khachhang.getMaKhachHang());

                request.setAttribute("list", list);

                request.getRequestDispatcher("/khachhang/historyorder.jsp").forward(request, response);

            }

        } catch (Exception e) {
        }

    }

    private void cancelorder(HttpServletRequest request, HttpServletResponse response) {
        try {
            String orderID = request.getParameter("orderID");

            ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO();
            List<SanPham> listProductOrdered = chiTietDonHangDAO.selectProductToRefundSize(orderID);
            System.out.println(listProductOrdered.size() + "aaaaaaaaaaaa");
            chiTietDonHangDAO.delete(orderID);

            DonHangDAO donHangDAO = new DonHangDAO();
            donHangDAO.delete(orderID);

            SanPhamDAO sanPhamDAO = new SanPhamDAO();
            for (SanPham sanPham : listProductOrdered) {
                System.out.println(sanPham.getSoluong() + "aaa");
                sanPhamDAO.refundSize(sanPham);
            }

            response.sendRedirect(request.getContextPath() + "/khach-hang?hanhdong=myorder");
        } catch (Exception e) {
            System.out.println("Lỗi ở cancelorder");
            e.printStackTrace();
        }

    }

    private void myvoucher(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");

            if (khachHang == null) {
                request.getRequestDispatcher("/khach-hang?hanhdong=login").forward(request, response);;
                return;
            } else {
                KhachHang_MaGiamGiaDAO dao = new KhachHang_MaGiamGiaDAO();
                List<MaGiamGia> mylistvoucher = dao.selectMaGiamGiaCuaKhachHang(khachHang.getMaKhachHang());
                request.setAttribute("mylistvoucher", mylistvoucher);
                request.getRequestDispatcher("khachhang/myvoucher.jsp").forward(request, response);
            }

        } catch (Exception e) {
        }

    }

    private void getvoucher(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
            if (khachHang == null) {
                response.sendRedirect(request.getContextPath() + "/khachhang/login.jsp");
            } else {
                String idmagiamgia = request.getParameter("idmagiamgia");
                KhachHang_MaGiamGia khachHang_MaGiamGia = new KhachHang_MaGiamGia(khachHang.getMaKhachHang(), idmagiamgia);

                KhachHang_MaGiamGiaDAO dao = new KhachHang_MaGiamGiaDAO();
                // Kiểm tra người dùng đã có mã giảm giá chưa
                if (dao.insert(khachHang_MaGiamGia) == 0) {
                    dao.plusQuantityVoucher(khachHang_MaGiamGia.getMaKhachHang(), khachHang_MaGiamGia.getIdMaGiamGia());
                    MaGiamGiaDAO maGiamGiaDAO = new MaGiamGiaDAO();
                    maGiamGiaDAO.updateSauKhiKhachHangNhanMa(idmagiamgia);
                } else {
                    MaGiamGiaDAO maGiamGiaDAO = new MaGiamGiaDAO();
                    maGiamGiaDAO.updateSauKhiKhachHangNhanMa(idmagiamgia);
                }

            }

//            request.getRequestDispatcher("/GUI/index.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/web");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
