
<%@page import="utils.Money"%>
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



        <!--Slider sản phẩm-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    </head>

    <body>
        <div class="hero_area">
            <!-- header -->
            <%@include file="/GUI/header.jsp" %>
            <%
            %>
            <!-- header -->
            <!-- content -->
            <section class="py-5">
                <div class="container">

                    <!--Lấy sản phẩm hiện lên-->
                    <%                        Integer soluongSanPhamDaBan = (Integer) request.getAttribute("soluongSanPhamDaBan");
                        SanPham sp1 = (SanPham) request.getAttribute("sanpham");
                        String error = request.getAttribute("error") + "";
                        error = error.equals("null") ? "" : error;

                        if (sp1 == null) {
                            System.out.println("sp1 bị null");
                        } else {
                            System.out.println("sp1sdsd bị null");
                        }
                    %>
                    <div class="row gx-5">
                        <aside class="col-lg-6">
                            <!--../GUI/imgsanpham/1.png-->
                            <div id="carouselExampleIndicators" class="carousel slide">
                                <div class="carousel-indicators" style="display: none" >
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" aria-label="Slide 4"></button>
                                </div>
                                <div class="carousel-inner" >
                                    <div class="carousel-item active">
                                        <img width="400px"src="<%=url%>/GUI/imgsanpham/<%=sp1.getHinhanhsanpham()%>" class="d-block w-100" alt="...">
                                    </div>
                                    <div class="carousel-item">
                                        <img width="400" src="<%=url%>/GUI/imgsanpham/<%=sp1.getHinhanhsanpham()%>" class="d-block w-100" alt="...">
                                    </div>
                                    <div class="carousel-item">
                                        <img width="400px" src="<%=url%>/GUI/imgsanpham/<%=sp1.getHinhanhsanpham()%>" class="d-block w-100" alt="...">
                                    </div>

                                    <div class="carousel-item">
                                        <img width="400px" src="<%=url%>/GUI/imgsanpham/<%=sp1.getHinhanhsanpham()%>" class="d-block w-100" alt="...">
                                    </div>
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true" ><ion-icon name="caret-back-outline"></ion-icon></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"><ion-icon name="caret-back-outline"></ion-icon></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                            <div class="row">
                                <div class="col-3"> <img width="120px" src="<%=url%>/GUI/imgsanpham/<%=sp1.getHinhanhsanpham()%>"> </div>
                                <div class="col-3"> <img width="120px" src="<%=url%>/GUI/imgsanpham/<%=sp1.getHinhanhsanpham()%>"> </div>
                                <div class="col-3"> <img width="120px" src="<%=url%>/GUI/imgsanpham/<%=sp1.getHinhanhsanpham()%>"> </div>
                                <div class="col-3"> <img width="120px" src="<%=url%>/GUI/imgsanpham/<%=sp1.getHinhanhsanpham()%>"> </div>


                            </div>
                            <!-- thumbs-wrap.// -->
                            <!-- gallery-wrap .end// -->
                        </aside>
                        <main class="col-lg-6">
                            <div class="ps-lg-3">
                                <h4 class="title text-dark">
                                    <%= sp1.getTensanpham() + " " + sp1.getMasanpham()%>
                                </h4>
                                <div class="d-flex flex-row my-3">
                                    <div class="text-warning mb-1 me-2">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fas fa-star-half-alt"></i>
                                        <span class="ms-1">
                                            4.5
                                        </span>
                                    </div>
                                    <span class="text-muted"><i class="fas fa-shopping-basket fa-sm mx-1"></i><%=soluongSanPhamDaBan%></span>
                                    <!--<span class="text-success ms-2">In stock</span>-->
                                </div>

                                <div class="mb-3">
                                    <span class="h5"> <%= Money.getMoney(sp1.getGiaban())%> </span>
                                    <span class="text-muted">/per box</span>
                                </div>

                                <p>
                                    <%= sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota() + sp1.getMota()%>
                                </p>

                                <div class="row">
                                    <dt class="col-3">Type:</dt>
                                    <dd class="col-9"><%= sp1.getKieumau()%></dd>

                                    <dt class="col-3">Color</dt>
                                    <dd class="col-9"><%= sp1.getMausac()%></dd>

                                    <dt class="col-3">Status</dt>
                                    <dd class="col-9">In Stock</dd>

                                    <dt class="col-3">Brand</dt>
                                    <dd class="col-9">THT Store</dd>
                                </div>

                                <hr />

                                <form class="row mb-4" action="<%=url%>/khach-hang" method="get">
                                    <input type="hidden" name="masanpham" value="<%=sp1.getMasanpham()%>">
                                    <input type="hidden" name="soluong" value="1">
                                    <!--<input type="hidden" name="nextpage" value="product-details.jsp">-->
                                    <div class=" col-12">
                                        <label class="form-label">Chọn kích cỡ:</label>
                                        <%
                                            List<SanPham> listsoluongsize = (List<SanPham>) request.getAttribute("listsoluongsize");
                                            for (SanPham sanphamconsize : listsoluongsize) {
                                        %>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="size" id="inlineRadio1" value="<%=sanphamconsize.getKichco()%>" <%=(sanphamconsize.getSoluong() <= 0) ? "disabled" : ""%> required="" >
                                            <label class="form-check-label" for="inlineRadio1"><%=sanphamconsize.getKichco()%></label>
                                        </div>
                                        <%
                                            }
                                        %>


                                        <!--                                        <div class="form-check form-check-inline">
                                                                                    <input class="form-check-input" type="radio" name="size" id="inlineRadio1" value="S">
                                                                                    <label class="form-check-label" for="inlineRadio1">S</label>
                                                                                </div>
                                                                                <div class="form-check form-check-inline">
                                                                                    <input class="form-check-input" type="radio" name="size" id="inlineRadio2" value="M">
                                                                                    <label class="form-check-label" for="inlineRadio2">M</label>
                                                                                </div>
                                                                                <div class="form-check form-check-inline">
                                                                                    <input class="form-check-input" type="radio" name="size" id="inlineRadio3" value="L" disabled>
                                                                                    <label class="form-check-label" for="inlineRadio3">L (sold out)</label>
                                                                                </div>-->
                                        <!--<label class="mb-2">Size</label>-->
                                        <!--                                        <select class="form-select border border-secondary" style="width: 65px" name="size">
                                                                                    <option  value="S">S</option>
                                                                                    <option value="M">M</option>
                                                                                    <option value="L">L</option>
                                                                                </select>-->
                                    </div>
                                    <!-- col.// -->
                                    <div class="row mt-3">
                                        <div class="form-group col-3">
<!--                                            <form action="<%= url%>/khachhang/checkout.jsp" method="get">       
                                            <button  type="submit" name="hanhdong" value="buynow" class="btn btn-warning mb-2">Buy Now</button>
                                            </form>  -->
                                            <!--<a href="<%= url%>/khachhang/checkout.jsp?masanpham=THT-002&size=">Buynow</a>-->
                                            <!--<a href="#" onclick="redirectToCheckout();">Buy Now</a>-->
                                            <button type="submit" name="hanhdong" value="buynow" class="btn btn-danger mb-2">Buy Now</button>
                                        </div>

                                        <div class="form-group col-5">

                                            <button type="submit" name="hanhdong" value="addtocart" class="btn btn-primary mb-2">Add to Cart</button>
                                            <span> <%=error%></span>
                                        </div>
                                    </div>

                                </form>






                                <!--<a href="#" class="btn btn-primary shadow-0"> <i class="me-1 fa fa-shopping-basket"></i> Add to cart </a>-->

                                <!--<a href="#" class="btn btn-light border border-secondary py-2 icon-hover px-3"> <i class="me-1 fa fa-heart fa-lg"></i> Save </a>-->
                                </form>

                        </main>
                    </div>
                </div>
            </section>
            <!-- content -->

            <section class="bg-light border-top py-4">
                <div class="container">
                    <div class="row gx-4">
                        <div class="col-lg-8 mb-4">
                            <div class="border rounded-2 px-3 py-2 bg-white">
                                <!-- Pills navs -->
                                <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                                    <li class="nav-item d-flex" role="presentation">
                                        <a class="nav-link d-flex align-items-center justify-content-center w-100 active" id="ex1-tab-1" data-mdb-toggle="pill" href="#ex1-pills-1" role="tab" aria-controls="ex1-pills-1" aria-selected="true">Product details</a>
                                    </li>
                                </ul>
                                <!-- Pills navs -->

                                <!-- Pills content -->
                                <div class="tab-content" id="ex1-content">
                                    <div class="tab-pane fade show active" id="ex1-pills-1" role="tabpanel" aria-labelledby="ex1-tab-1">
                                        <p>
                                            With supporting text below as a natural lead-in to additional content. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                                            enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                                            pariatur.
                                        </p>
                                        <div class="row mb-2">
                                            <div class="col-12 col-md-6">
                                                <ul class="list-unstyled mb-0">
                                                    <li><i class="fas fa-check text-success me-2"></i>Some great feature name here</li>
                                                    <li><i class="fas fa-check text-success me-2"></i>Lorem ipsum dolor sit amet, consectetur</li>
                                                    <li><i class="fas fa-check text-success me-2"></i>Duis aute irure dolor in reprehenderit</li>
                                                    <li><i class="fas fa-check text-success me-2"></i>Optical heart sensor</li>
                                                </ul>
                                            </div>
                                            <div class="col-12 col-md-6 mb-0">
                                                <ul class="list-unstyled">
                                                    <li><i class="fas fa-check text-success me-2"></i>Easy fast and ver good</li>
                                                    <li><i class="fas fa-check text-success me-2"></i>Some great feature name here</li>
                                                    <li><i class="fas fa-check text-success me-2"></i>Modern style and design</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Pills content -->
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="px-0 border rounded-2 shadow-0">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">Similar items</h5>

                                        <%

                                            List<SanPham> list = (List<SanPham>) request.getAttribute("list");

                                            int count4 = -1;
                                            for (SanPham sp : list) {
                                                count4++;
                                                if (count4 == 4) {
                                                    break;
                                                } else {
                                        %>
                                        <div class="d-flex mb-3">
                                            <a href="<%=url%>/san-pham?hanhdong=viewproductdetail&masanpham=<%=sp.getMasanpham()%>">
                                                <img src="<%=url%>/GUI/imgsanpham/<%=sp.getHinhanhsanpham()%>" style="min-width: 96px; height: 96px;" class="img-md img-thumbnail" />
                                            </a>
                                            <div class="info">
                                                <a href="#" class="nav-link mb-1">
                                                    <%= sp.getTensanpham() + sp.getMasanpham()%> <br />
                                                    <%= sp.getKieumau()%>
                                                </a>
                                                <strong class="text-dark"> <%= Money.getMoney(sp.getGiaban())%> </strong>
                                            </div>
                                        </div>



                                        <%
                                                }
                                            }
                                        %>




                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <!-- end hero area -->
        <script>
            function redirectToCheckout() {
                // Lấy radio button đã được chọn
                var selectedSize = document.querySelector('input[name="size"]:checked');
                // Kiểm tra xem có radio nào được chọn hay không
                if (!selectedSize) {
                    alert("Vui lòng chọn size sản phẩm");
                    return;
                }
                var size = selectedSize.value;
                var masanpham = document.querySelector('input[name="masanpham"]').value;
                // Tạo URL với tham số size và masanpham
                var url = '<%= url%>/khachhang/checkout.jsp?hanhdong=buynow&masanpham=' + encodeURIComponent(masanpham) + '&size=' + encodeURIComponent(size);
                // Chuyển hướng đến URL đó
                window.location.href = url;
            }

        </script>
    </body>hanhdong=addtocart&masanpham=THT-001
</html>khach-hang?size=S&hanhdong=addtocart