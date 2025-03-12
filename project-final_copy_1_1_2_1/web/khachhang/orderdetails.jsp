<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();;
%>
<c:set var="url" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <title>Chi Tiết Đơn Hàng</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="shortcut icon" href="<%= url1%>/GUI/images/favicon.png" type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>


        <!-- slider stylesheet -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="<%= url1%>/GUI/css/bootstrap.css" />

        <!-- Custom styles for this template -->
        <link href="<%= url1%>/GUI/css/style.css" rel="stylesheet" />
        <!-- responsive style -->
        <link href="<%= url1%>/GUI/css/responsive.css" rel="stylesheet" />

        <!-- Voucher style -->
        <link rel="stylesheet" href="<%=url1%>/GUI/css/voucher.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    </head>
    <body>
        <%@include file="../GUI/header.jsp" %>
        
        <div class="container mt-4 mb-5">
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
                    <p><strong>Họ và tên: ${khachHang.hoVaTen}</strong> </p>
                    <p><strong>Số điện thoại: ${khachHang.soDienThoai}</strong> </p>
                    <p><strong>Email: ${khachHang.email}</strong> </p>
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
                      
                        <th>Giá ban</th>
                    
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="listOderDetails" items="${listOderDetails}">
                        <tr>
                            <td><img src="<%=url%>/GUI/imgsanpham/${listOderDetails.sanPham.hinhanhsanpham}" width="50px"></td>
                            <td>${listOderDetails.sanPham.masanpham}</td>
                            <td>${listOderDetails.sanPham.tensanpham}</td>
                            <td>${listOderDetails.categories.categoryName}</td>
                            <td>${listOderDetails.sanPham.mausac}</td>
                            <td>${listOderDetails.chiTietDonHang.size}</td>
                            <td>${listOderDetails.chiTietDonHang.soluongsanphammua}</td>
                            
                            <td>${listOderDetails.sanPham.giaban}</td>
                            
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Tổng tiền -->
            <h3 class="text-end">Tổng: <span class="text-danger"> ${donHang.tongtien}VNĐ</span></h3>

            <!-- Nút Quay lại -->
            <a href="<%=url%>/GUI/index.jsp" class="btn btn-secondary">Quay lại</a>
            
        </div>
            <%@include file="../GUI/footer.jsp" %>
    </body>
</html>