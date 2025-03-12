<%@page import="utils.Money"%>
<%@page import="model.MaGiamGia"%>
<%@page import="database.MaGiamGiaDAO"%>
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

        <!-- Voucher style -->
        <link rel="stylesheet" href="<%=url1%>/GUI/css/voucher.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    </head>

    <body>
        <%@include file="header.jsp" %>
        <div class="hero_area">
            



            <!-- shop section -->

            <section class="shop_section layout_padding">
                <div class="container">
                    <div class="heading_container heading_center">
                        <h2>
                            News arrivals
                        </h2>
                    </div>
                    <div class="row">
                        <%  SanPhamDAO dao = new SanPhamDAO();

                            /* List<SanPham> list = dao.selectAll();*/
                            List<SanPham> list = (List<SanPham>) request.getAttribute("listsanpham");
                            if (list != null && !list.isEmpty()) {
                                int count = 0;
                                for (SanPham sanPham : list) {

                                    if (count == 8) {
                                        break;
                                    }
                                    count++;
                        %>
                        <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="box">
                            <a href="<%=url%>/san-pham?hanhdong=viewproductdetail&masanpham=<%=sanPham.getMasanpham()%>">
                                <div class="img-name">
                                    <h6 class="col-12 text-center"><%=sanPham.getTensanpham()%> <%=sanPham.getMasanpham()%>  </h6>
                                </div>
                                <div class="img-box">
                                    <img src="<%=url1%>/GUI/imgsanpham/<%=sanPham.getHinhanhsanpham()%>" alt="" />
                                </div>
                                <div class="detail-box row">
                                    <h6 class="col-12 text-center">
                                        Price
                                        <span>   <%=Money.getMoney(sanPham.getGiaban()) %> </span>
                                    </h6>
                                </div>

                            </a>
                            <form action="<%=url1%>/khach-hang" method="get">
                                <input type="hidden" name="hanhdong" value="addtocart">

                                <input type="hidden" name="masanpham" value="<%=sanPham.getMasanpham()%>">
                                <input type="hidden" name="size" value="M">
                                <input type="hidden" name="soluong" value="1">
                                <input type="hidden" name="nextpage" value="shop.jsp">

                                <!--                                insert into giohang(makhachhang, masanpham, size, soluong)
                                                                values ('1','THT-001','',1)-->

                                <div class="text-center">
                                    <button class="img-name bg-primary">
                                        <a style="color: white" href="<%=url%>/san-pham?hanhdong=viewproductdetail&masanpham=<%=sanPham.getMasanpham()%>"> Add to card </a>
                                    </button>
                                </div>
                            </form> 

                            
                            </a>
                        </div>
                    </div>   
                        <%
                                }
                            }
                        %>


                    </div>
                    <div class="btn-box">
                        <a href="<%=url%>/GUI/shop.jsp">
                            View All Products
                        </a>
                    </div>
                </div>
            </section>
            <!-- end shop section -->

            <!-- gift section -->
            <%--<%@include file="../GUI/voucherLink.jsp" %>--%>

            <div class="row">
                <h2 style="text-align: center">
                            Voucher for you
                        </h2>
                <div  class="col-2"> </div>
                <%

                    List<MaGiamGia> listMaGiamGia = (List<MaGiamGia>) request.getAttribute("listmagiamgia");

                    if (listMaGiamGia != null && !listMaGiamGia.isEmpty()) {
                        for (MaGiamGia maGiamGia : listMaGiamGia) {
                %>   

                <div  class="col-4 row">
                    <div class="col-10 row card-item">
                        <img class="card-img row " height="40px"  alt="anh voucher" src="<%=url1%>/GUI/imgvoucher/<%=maGiamGia.getHinhanhvoucher()%>">
                        <div class="card-body row">
                            <p style="font-size: 24px;font-weight: 500 " class="card-title"><%=maGiamGia.getTenMaGiamGia()%></p>
                            <p class="card-text"> <h5>EXP: <small class="text-muted"><%=maGiamGia.getNgayHetHan()%></small></h5> </p>
                        </div>

                    </div>
                    <div class="col-2 d-flex justify-content-center align-items-center">
                        <form class="card-getvalue" action="khach-hang" method="get">
                            <input type="hidden" name="hanhdong" value="getvoucher" >

                            <input type="hidden" name="idmagiamgia" value="<%=maGiamGia.getIdMaGiamGia()%>" >
                            <input type="submit" class="btn btn-primary" value="Get" >
                        </form>
                    </div>  
                </div>

                <div  class="col-1"> </div>
                <%
                        }
                    }
                %>              



            </div>

            <!-- end gift section -->


           
        </div>
 <%@include file="footer.jsp" %>
        <!-- end hero area -->


        <script src="<%= url1%>/GUI/js/jquery-3.4.1.min.js"></script>
        <script src="<%= url1%>/GUI/js/bootstrap.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
        </script>
        <script src="<%= url1%>/GUI/js/custom.js"></script>
    </body>


</html>