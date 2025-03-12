
<%@page import="model.Item"%>
<%@page import="model.Order"%>
<%@page import="utils.Money"%>
<%@page import="model.GioHang"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.GioHangDAO"%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="model.SanPham"%>
<%@page import="java.util.List"%>
<%@page import="database.SanPhamDAO"%>
<%
    String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();;
%>
<!DOCTYPE html>
<html>

    <head>
        <!-- Basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <!-- Site Metas -->
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>
            THT Helmet Store

        </title>

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
        <div class="hero_area">
            <!-- header -->
            <%@include file="/GUI/header.jsp" %>
            <!--  section -->
            <% KhachHang khachhang = (KhachHang) session.getAttribute("khachhang");
                if (khachHang != null) {
            %>
            <section class="h-100">
                <div class="container h-100 py-5">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-10">

                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h3 class="fw-normal mb-0">Your Cart</h3>
                                <div>
                                    <a href="<%= url%>/GUI/shop.jsp">
                                        <button type="button" class="btn btn-outline-dark">Back to shop</button>
                                    </a>
                                </div>
                            </div>

                            <%
                                // Lấy các sản phẩm trong giỏ hàng của IDKhachHang
//                                GioHangDAO dao = new GioHangDAO();
//                                List<GioHang> list = new ArrayList<GioHang>();
//                                list = dao.selectGioHangCuaKhachHang(khachHang.getMaKhachHang());

//                                List<GioHang> list = (List<GioHang>) request.getAttribute("list");
                                Order order = (Order) session.getAttribute("order");
                                
                                String error = request.getAttribute("error")+"";
//                                error = "Sản phẩm đã vượt quá số lượng trong kho";
                                error = error.equals("null")?"":error;
                                    List<Item> list = order.getList();
                                    if (list != null && !list.isEmpty()) {
                                        for (Item item : list) {
                                            SanPham gioHang = item.getSanpham();
//                                        SanPhamDAO sanPhamDao = new SanPhamDAO();
//                                        SanPham sp = sanPhamDao.selectById(gioHang.getMasanpham());
                                            System.out.println("sp = " + gioHang.toString());
                            %>
                            <!--<h1>Helooooooooooooooooooooooooo</h1>-->
                            <div class="card rounded-3 mb-4">
                                <div class="card-body p-4">
                                    <div class="row d-flex justify-content-between align-items-center">
                                        <div class="col-md-2 col-lg-2 col-xl-2">
                                            <img
                                                src="<%= url1%>/GUI/imgsanpham/<%=gioHang.getHinhanhsanpham()%>"
                                                class="img-fluid rounded-3" alt="Cotton T-shirt">
                                        </div>
                                        <div class="col-md-3 col-lg-3 col-xl-3">
                                            <p class="lead fw-normal mb-2"><%= gioHang.getMasanpham()%></p>
                                            <p><span class="text-muted">Size: </span> <%=gioHang.getKichco()%> <span class="text-muted">Color: </span><%= gioHang.getMausac()%></p>
                                            <span class="red"><%=error%></span>
                                        </div>

                                        <div class="col-md-3 col-lg-3 col-xl-2 d-flex align-items-center">
                                            <div class="d-flex align-items-center" style="width: 120px;">
                                                <a href="<%=url%>/khach-hang?hanhdong=minusonecart&masanpham=<%=gioHang.getMasanpham()%>&size=<%=gioHang.getKichco()%>"> <i class="fas fa-minus" style="color: black"></i></a>
                                                <h6 style="margin: 0px 8px 0px 8px"><%= item.getSoluong()%></h6>
                                                <!--                                                    <input type="number" class="form-control form-control-sm text-center" 
                                                                                                           value="<%= item.getSoluong()%>" min="1" style="width: 50px; padding: 2px;">-->
                                                <a href="<%=url%>/khach-hang?hanhdong=plusonecart&masanpham=<%=gioHang.getMasanpham()%>&size=<%=gioHang.getKichco()%>"><i class="fas fa-plus" style="color: black"></i></a>
                                            </div>
                                        </div>




                                        <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                            <h5 class="mb-0">$<%= Money.getMoney(gioHang.getGiaban())%></h5>
                                        </div>
                                        <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                            <a href="<%=url%>/khach-hang?hanhdong=deleteproductoutcart&masanpham=<%=gioHang.getMasanpham()%>&size=<%=gioHang.getKichco()%>" class="text-danger"><i class="fas fa-trash fa-lg" style="color: black"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <%
                                        }
                                    }
                            } else {

                            %>
                            <div class="d-flex justify-content-center align-items-center mb-4">
                                <h3 class="fw-normal mb-0">Empty cart</h3>
                            </div>
                            <%}%>
                            <div class="card">
                                <div class="card-body">
                                    <form action="<%=url%>/khach-hang" method="post">
                                        <input name="hanhdong" value="checkout">
                                        <input type="submit" class="btn btn-primary" value ="Check out" >
                                    </form>
                              
                                    <!--                                    <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-dark btn-block btn-lg"
                                                                                 data-mdb-ripple-color="dark" class="btn btn-outline-dark"><a href="<%= url%>/khachhang/checkout.jsp">Check out</a></button>-->

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!-- end  section -->

            <script src="<%= url1%>/GUI/js/jquery-3.4.1.min.js"></script>
            <script src="<%= url1%>/GUI/js/bootstrap.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
            </script>
            <script src="<%= url1%>/GUI/js/custom.js"></script>
        </div>
        <!-- end hero area -->
    </body>

</html>