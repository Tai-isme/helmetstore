<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="url" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <title>Chi Tiết Đơn Hàng</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin 2 - Dashboard</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/admin/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container mt-4">
            <h2 class="mb-3">Chi Tiết Đơn Hàng</h2>

            <!-- Thông tin đơn hàng -->
            <div class="card mb-4">
                <div class="card-body">
                    <h5>Order ID: ${donHang.madonhang}<strong></strong></h5>
                    <p>Ngày đặt hàng: ${donHang.ngaydathang}</p>
                    <p>Trạng thái: <span class="text-success">${donHang.trangthaidonhang}</span></p>
                </div>
            </div>

            <!-- Thông tin khách hàng -->
            <div class="card mb-4">
                <div class="card-body">
                    <h5>Thông Tin Khách Hàng</h5>
                    <p><strong>Ma khach hang: ${khachHang.maKhachHang}</strong> </p>
                    <p><strong>Họ và tên: ${khachHang.hoVaTen}</strong> </p>
                    <p><strong>Số điện thoại: ${khachHang.soDienThoai}</strong> </p>
                    <p><strong>Email: ${khachHang.email}</strong> </p>
                    <p><strong>Dia chi giao hang: ${donHang.diachigiaohang}</strong> </p>
                    
                    <form action="${url}/admin" method="GET">
                        <input type="hidden" name="hanhdong" value="editKhachHangInOrder">
                        <input type="hidden" name="makhachhang" value="${khachHang.maKhachHang}">
                        <input type="hidden" name="madonhang" value="${donHang.madonhang}">
                        <input type="submit" class="btn btn-primary" value="Edit">
                    </form>

                </div>
            </div>

            <!-- Danh sách sản phẩm trong đơn hàng -->
            <h4>Danh Sách Sản Phẩm</h4>
            <table class="table table-bordered">
                <thead class="table-light">
                    <tr>
                        <th>Hình ảnh</th>
                        <th>Ma san pham</th>
                        <th>Tên sản phẩm</th>
                        <th>Loại</th>
                        <th>Mau sac</th>
                        <th>Size</th>
                        <th>Số lượng</th>
                        <th>Giá nhap</th>
                        <th>Giá ban</th>
                        <th>Giam gia</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="listOderDetails" items="${listOderDetails}">
                        <tr>

                            <td><img src="${url}/GUI/imgsanpham/${listOderDetails.sanPham.hinhanhsanpham}" width="50px"></td>
                            <td>${listOderDetails.sanPham.masanpham}</td>
                            <td>${listOderDetails.sanPham.tensanpham}</td>
                            <td>${listOderDetails.categories.categoryName}</td>
                            <td>${listOderDetails.sanPham.mausac}</td>
                            <td>${listOderDetails.chiTietDonHang.size}</td>
                            <td>${listOderDetails.chiTietDonHang.soluongsanphammua}</td>
                            <td>${listOderDetails.sanPham.gianhap}</td>
                            <td>${listOderDetails.sanPham.giaban}</td>
                            <td>${listOderDetails.sanPham.giamgia}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Tổng tiền -->
            <h3 class="text-end">Tổng: <span class="text-danger"> ${donHang.tongtien} VNĐ</span></h3>

            <!-- Nút Quay lại -->

            <a href="${url}/admin?hanhdong=order" class="btn btn-secondary">Quay lại</a>
        </div>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/chart-area-demo.js"></script>
        <script src="js/demo/chart-pie-demo.js"></script>
    </body>
</html>