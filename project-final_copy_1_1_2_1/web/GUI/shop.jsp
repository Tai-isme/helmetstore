<%@page import="utils.Money"%>
<%@page import="java.util.ArrayList"%>
<%
    String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    SanPhamDAO sanPhamDAO = new SanPhamDAO();
    List<String> listColor = (List<String>) session.getAttribute("listColor");

//    List<String> listColor = sanPhamDAO.selectColor();
%>

<%@page import="model.SanPham"%>
<%@page import="java.util.List"%>
<%@page import="database.SanPhamDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <!-- Basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Mobile Metas -->
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <!-- Site Metas -->
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="shortcut icon" href="images/favicon.png" type="image/x-icon" />

        <title>THT Helmet Store</title>

        <!-- slider stylesheet -->
        <link
            rel="stylesheet"
            type="text/css"
            href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
            />

        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="<%=url1%>/GUI/css/bootstrap.css" />

        <!-- Custom styles for this template -->
        <link href="<%=url1%>/GUI/css/style.css" rel="stylesheet" />
        <!-- responsive style -->
        <link href="<%=url1%>/GUI/css/responsive.css" rel="stylesheet" />

        <!-- Boostrap t? bài c? -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    </head>

    <body>
         <%@include file="header.jsp" %>
        <div class="hero_area">
            <!-- header section strats -->
           
            <!-- end header section -->
        </div>
        <!-- end hero area -->

        <!-- shop section -->

        <section class="shop_section layout_padding">
            <div class="container">
                <div class="heading_container heading_center">
                    <h2>Products</h2>
                </div>

                <div class="filter-container">
                    <form action="<%=url1%>/san-pham" method="get">
                        <input type="hidden" name="hanhdong" value="searchByConditions">

                        <%
                            String types = request.getAttribute("types") + "";
                            types = types.equals("null") || types.equals("") ? "Types" : types;
                        %>  
                        <select class="filter" id="category-filter" name="types">

                            <%
                                if (types.equals("Types")) {
                            %>
                            <option value="">Types</option>
                            <option value="Fullface">Fullface</option>
                            <option value="Three-Square">Three-Square</option>
                            <option value="Half">Half</option>
                            <%
                            } else {
                            %>
                            <option value="<%=types%>"><%=types%> </option>

                            <option value="">Types</option>
                            <option value="Fullface">Fullface</option>
                            <option value="Three-Square">Three-Square</option>
                            <option value="Half">Half</option>
                            <%
                                }
                            %>




                        </select>

                        <%
                            String Color = request.getAttribute("color") + "";
                            Color = Color.equals("null") || Color.equals("") ? "Color" : Color;
                        %>     
                        <select class="filter" id="brand-filter" name="color">

                            <%
                                if (Color.equals("Color")) {
                            %>
                            <option value="">Color</option>
                            <%
                                for (String color : listColor) {
                            %>
                            <option value="<%=color%>"><%=color%></option>
                            <%
                                }
                            } else {
                            %>
                            <option value="<%=Color%>"><%=Color%></option>
                            <option value="">Color</option>

                            <%
                                for (String color : listColor) {
                            %>
                            <option value="<%=color%>"><%=color%></option>
                            <%
                                    };
                                } %>

                        </select>



                        <%
                            String size = request.getAttribute("size") + "";
                            size = size.equals("null") || size.equals("") ? "Size" : size;
                        %>
                        <select class="filter" id="status-filter" name="size">
                            <%
                                if (size.equals("Size")) {
                            %>
                            <option value="">Size</option>
                            <option value="S">S</option>
                            <option value="M">M</option>
                            <option value="L">L</option>
                            <option value="XL">XL</option>
                            <%
                            } else {
                            %>
                            <option value="<%=size%>"><%=size%> </option>

                            <option value="">Size</option>
                            <option value="S">S</option>
                            <option value="M">M</option>
                            <option value="L">L</option>
                            <option value="XL">XL</option>
                            <%
                                }
                            %>

                        </select>

                        <%
                            String price = request.getAttribute("price") + "";
                            price = price.equals("null") || price.equals("") ? "Price" : price;
                        %>     
                        <select class="filter" id="price-filter" name="price">
                            <%
                                if (price.equals("Price")) {
                            %>
                            <option value="">Price</option>
                            <option value="0-500000">$0-50000</option>
                            <option value="500000-1000000">$500000-1000000</option>
                            <option value="1000000-3000000">$1000000-3000000</option>
                            <%
                            } else {
                            %>
                            <option value="<%=price%>"><%=price%> </option>
                            <option value="">Price</option>

                            <option value="0-500000">$0-50000</option>
                            <option value="500000-1000000">$500000-1000000</option>
                            <option value="1000000-3000000">$1000000-3000000</option>
                            <%
                                }
                            %>


                        </select>
                        <button>Submit</button>
                    </form>


                    <form action="<%= url1%>/san-pham" method="GET" class="d-flex align-items-center">
                        <input type="hidden" name="hanhdong" value="sort">

                        <!--4 cái thông sô search bên trái-->
                        <input type="hidden" name="types" value="<%=types%>">
                        <input type="hidden" name="color" value="<%=Color%>">
                        <input type="hidden" name="size" value="<%=size%>">
                        <input type="hidden" name="price" value="<%=price%>">

                        <input type="hidden" name="keyword" value="<%=request.getAttribute("keyword")%>">
                        <label for="sort-filter" class="me-2">Sort by:</label>
                        <select class="filter" id="sort-filter" name="sortPrices" onchange="this.form.submit()">
                            <option value="">Default</option>
                            <option value="price-asc"<%= "price-asc".equals(request.getParameter("sortPrices")) ? "selected" : ""%>>Price: Low to High</option>
                            <option value="price-desc"<%= "price-desc".equals(request.getParameter("sortPrices")) ? "selected" : ""%>>Price: High to Low</option>
                        </select>
                    </form>

                </div>

                <div class="row ">
                    <%  SanPhamDAO dao = new SanPhamDAO();

                        ArrayList<SanPham> list = (ArrayList< SanPham>) session.getAttribute("list");
                        if (list == null) {
                            list = dao.selectAll();
                        }
                        float soLuongPage = (float) list.size() / 16 + 1;
                        int indexTrang = 0;
                        String value = request.getParameter("value");

                        System.out.println(
                                "value = " + value);

                        if (value
                                == null || value.equals(
                                        "null")) {
                            indexTrang = 1;
                        } else {
                            indexTrang = Integer.parseInt(request.getParameter("value"));
                        }

                        if (list != null && !list.isEmpty()) {
                            int i = 0;
                            for (SanPham sanPham : list) {

                                if (i < indexTrang * 16 && i >= (indexTrang - 1) * 16) {


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
                                        <span> <%= Money.getMoney(sanPham.getGiaban()) %> </span>
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

                            <div class="new">
                                <span> New </span>
                            </div>
                            </a>
                        </div>
                    </div>        

                    <%
                                }
                                i++;
                            }
                        }
                    %>
                </div>



                <!--Chuyển trang để hiện thêm sản phẩm-->
                <div class="d-flex justify-content-center mt-5" >
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="">Previous</a></li>
                                <%
                                    if (soLuongPage > 0) {
                                        for (int i = 1; i < soLuongPage; i++) {

                                %>   
                            <li class="page-item"><a class="page-link" href="<%=url%>/GUI/shop.jsp?value=<%=i%>"><%=i%></a></li>
                                <%                            }
                                    }
                                %>
                            <li class="page-item"><a class="page-link" href="">Next</a></li>
                        </ul>
                    </nav>
                </div>



              
            </div>
        </section>

        <!-- end shop section -->

        <!-- info section -->
        <%@include file="footer.jsp" %>

        <script src=""></script>
        <script src="<%=url1%>js/bootstrap.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
        <script src="<%=url1%>js/custom.js"></script>
    </body>
</html>