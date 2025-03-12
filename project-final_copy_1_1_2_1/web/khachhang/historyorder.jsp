<%-- 
    Document   : historyorder
    Created on : Mar 8, 2025, 11:07:00 PM
    Author     : Thien
--%>
<%@page import="utils.Money"%>
<%@page import="model.SanPham"%>
<%@page import="java.util.List"%>
<%@page import="model.OrderHistory"%>
<%
    String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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

        <!-- Font Awesome 6 -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    </head>
    <body>
        <%@include file="../GUI/header.jsp" %>
        <div class="container my-5">
            <h2 class="text-center mb-4">History and Management</h2>
            <div class="container my-4 ">


                <%                    List<OrderHistory> list = (List<OrderHistory>) request.getAttribute("list");

                    if (list != null && !list.isEmpty()) {
                        for (OrderHistory oh : list) {

                %>

                <div class="card p-3 mb-2">
                    <!-- Dòng trên cùng: Tên shop, Yêu thích, Chat, Xem Shop, Trạng thái đơn hàng -->
                    <div class="row align-items-center ">
                        <div class="col-md-6 d-flex align-items-center">
                            <!-- Tên shop -->
                            <h6 class="mb-0">THT Store</h6>
                            <!-- Link "Xem Shop" -->
                            <a href="<%=url%>/san-pham?hanhdong=searchByConditions&types=&color=&size=&price=" class="btn btn-link ms-2">View Shop</a>
                        </div>
                        <div class="col-md-3">
                            <h6>OrderID: <%=oh.getMadonhang()%> </h6> 
                            <h6>OrderDate:<br> <%=oh.getNgaydathang().toString()%> </h6>
                        </div>
                        <!-- Trạng thái giao hàng + Đánh giá -->
                        <div class="col-md-3 text-center">
                            <span class="text-success fw-bold"><%=oh.getTrangthaidonhang()%> </span>
                            <a href="#" class="ms-3 text-danger">Preview</a>
                        </div>
                    </div>
                    <hr>

                    <%
                        List<SanPham> listSP = oh.getListsanpham();
                        for (SanPham sp : listSP) {
                    %>  

                    <!-- Phần chi tiết sản phẩm -->
                    <div class="row align-items-center">
                        <!-- Ảnh sản phẩm -->
                        <div class="col-md-2 text-center">
                            <!-- Thay ảnh minh hoạ bằng ảnh thật nếu có -->
                            <img src="<%=url%>/GUI/imgsanpham/<%=sp.getHinhanhsanpham()%> " alt="Sản phẩm" class="img-fluid rounded">
                        </div>
                        <!-- Thông tin sản phẩm -->
                        <div class="col-md-4">
                            <h5 class="mb-1">
                                Nón Bảo Hiểm <%=sp.getMasanpham() %>
                            </h5>
                            <small class="text-muted">Type: <%=sp.getKieumau()%></small> <br>
                            <small class="text-muted">Size: <%=sp.getKichco()%></small> <br>
                            <small class="text-muted">Quantity: x<%=sp.getSoluong()%></small>
                            <!-- Giá gốc và giá khuyến mãi -->

                        </div>

                        <div class="col-md-4">

                        </div>
                        <div class="col-md-2 text-center">
                            <p class="mb-0">
                                <!-- <span class="text-decoration-line-through text-muted me-2">498.000</span> -->
                                <span class="text-danger fs-6 fw-bold"><%= Money.getMoney(sp.getGiaban())%></span>
                            </p>
                        </div>
                    </div>




                    <%
                        }
                    %>
                    <div class="col-md-12 row">
                        <!-- Thành tiền -->
                        <div class="col-md-9 text-end">
                            <%
                                if (!oh.getTrangthaidonhang().equals("Đã giao")) {

                            %>
                            <form action="khach-hang" method="get">
                                <input type="hidden" name="hanhdong" value="cancelorder">
                                <input type="hidden" name="orderID" value="<%=oh.getMadonhang()%>">
                                <input type="submit" class="btn btn-danger" value="Cancel Order" style="width: 140px" >
                            </form>


                            <%    }
                            %>

                        </div>
                        <div class=" col-md-3 text-end fw-bold">

                            Total: <span class="text-danger fs-4"><%=Money.getMoney(oh.getTongtien())%></span>
                        </div>
                    </div>

                </div>
                <%
                        }
                    }
                %>


            </div>

            <div class="text-center mt-4">
                <button class="btn btn-secondary"> <a href="#" style="color: white">Back to the shop</a></button>
            </div>
        </div>

        <script src="<%= url1%>/GUI/js/jquery-3.4.1.min.js"></script>
        <script src="<%= url1%>/GUI/js/bootstrap.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
        </script>
        <script src="<%= url1%>/GUI/js/custom.js"></script>
    </body>
</html>
