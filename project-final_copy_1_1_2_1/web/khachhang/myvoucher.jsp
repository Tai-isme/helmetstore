
<%@page import="model.MaGiamGia"%>
<%@page import="model.MaGiamGia"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%
    String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="<%=url1%>/GUI/css/voucher.css">
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
    </head>
    <body>
        <%@include file="../GUI/header.jsp"%>
        <h1 style="text-align: center">My Voucher</h1>
        <div card-container>

            <%                    List<MaGiamGia> list = (List<MaGiamGia>) request.getAttribute("mylistvoucher");
                for (MaGiamGia magiamgia : list) {
            %>
            
            <div class="card-item mb-3">
                <img class="card-img" alt="anh voucher" src="<%=url1%>/GUI/imgvoucher/<%=magiamgia.getHinhanhvoucher()%>">
                <div class="card-body">
                    <p style="font-size: 24px;font-weight: 500 " class="card-title"><%=magiamgia.getTenMaGiamGia() %></p>
                    <p class="card-text"> <h5>EXP: <small class="text-muted"><%=magiamgia.getNgayHetHan()%></small></h5> </p>
                </div>
                <div class="text-center d-flex justify-content-center align-items-center">Quantity: <%=magiamgia.getSoLuong()%> </div>

            </div>
                
            <%
                }
            %>
        </div>
        <!--            <div class="card-content">
                        
                        <div class="card-item" style="">
                            
        
                                <div class="row g-0">
                                    <div class="col-md-3">
                                        <img src="../uploads/1.jpeg" class="img-fluid rounded-start" alt="...">
                                    </div>
        
                                    <div class="col-md-7">
                                        <div class="card-body">
                                            <h5 class="card-title">Discount xx%</h5>
        
                                            <p class="card-text"> <h5>EXP: <small class="text-muted">15-02-2025</small></h5> </p>
                                        </div>
        
                                    </div>
        
                                    <div class="col-md-2">
                                        <form action="<%=url%>/ma-giam-gia" method="get">
                                            <input class="form-control" type="hidden"  name="hanhdong" value="get">
                                            <input class="form-control" type="hidden"  name="idmagiamgia" value="1">
                                            <button class="form-control" > Get </button>
                                        </form>
                                    </div>
                                </div>
                            
                        </div>
                    </div>-->


    </body>
</html>
