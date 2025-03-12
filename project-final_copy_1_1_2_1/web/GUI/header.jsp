<%@page import="java.util.Random"%>
<%@page import="model.KhachHang"%>
<% String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<% String currentUrl = request.getRequestURI();%>

<!-- header section strats -->
<header class="header_section">
    <nav class="navbar navbar-expand-lg custom_nav-container ">
        <a class="navbar-brand" href="<%=url%>/GUI/index.jsp">
            <span>
                THT Helmet Store
            </span>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class=""></span>
        </button>

        <!--<div class="collapse navbar-collapse d-flex align-items-center" id="navbarSupportedContent">-->
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav  ">
                <li class="nav-item <%= currentUrl.contains("index.jsp") ? "active" : ""%>">
                    <a class="nav-link" href="<%=url%>/web?">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item <%= currentUrl.contains("shop.jsp") ? "active" : ""%>">
                    <a class="nav-link" href="<%=url%>/san-pham?hanhdong=searchByConditions&types=&color=&size=&price=">
                        Products
                    </a>
                </li>
                <li class="nav-item <%= currentUrl.contains("contact.jsp") ? "active" : ""%>">
                    <a class="nav-link" href="<%=url%>/GUI/contact.jsp">Contact Us</a>
                </li>
            </ul>
            <div class="user_option">

                <%
                    Object obj = session.getAttribute("khachHang");
                    KhachHang khachHang = null;
                    if (obj != null) {
                        khachHang = (KhachHang) obj;
                    } else {
//                        response.sendRedirect("web");
                    }
                %>
                <%
                    if (khachHang == null) {
                        khachHang = new KhachHang();
                        Random rd = new Random();
                        khachHang.setMaKhachHang("no" + System.currentTimeMillis() + rd.nextInt(1000) + "");
                %> 

                <a href="<%= url%>/khachhang/login.jsp">
                    <i class="fa fa-user" aria-hidden="true"></i>
                    <span <%= currentUrl.contains("index.jsp") ? "active" : ""%>>
                        Login
                    </span>
                </a>
                <%
                } else {
                %>

                <div class="col-4 text-center">
                    <ul class="navbar-nav me-auto bg-infor ">
                        <li class="nav-item dropdown">
                            <a style="font-size: 20px;"  class="nav-link dropdown-toggle text-center" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 

                                <img style="width: 30px; height: 30px; border-radius: 50%" src="<%=url%>/uploads/<%=khachHang.getHinhAvatar()%>?v=<%=System.currentTimeMillis()%>"  alt=""> 

                            </a>
                            <ul class="dropdown-menu " >
                                <li><a class="dropdown-item" href="<%=url%>/khach-hang?hanhdong=myorder">My Order</a></li>
                                <li><a class="dropdown-item" href="<%=url%>/khachhang/update.jsp">Update Information</a></li>
                                <li><a class="dropdown-item" href="<%=url%>/khachhang/resetpassword.jsp">Change Password</a></li>
                                
                                <li><a class="dropdown-item" href="<%=url%>/khach-hang?hanhdong=myvoucher">My Voucher</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="<%=url%>/khach-hang?hanhdong=logout">Log-out</a></li>
                            </ul>
                        </li>
                    </ul>	
                </div>

<a href="<%= url%>/khachhang/cart.jsp">
                    <i class="fa fa-shopping-bag" aria-hidden="true"></i>
                </a>
                <%
                    }
                %>                    
                


                <form class="d-flex ms-auto" role="search" action="<%= url%>/san-pham" method="GET">
                    <input type="hidden" name="hanhdong" value="search">
                    <div class="input-group">
                        <% String keyword = request.getAttribute("keyword") + "";
                            keyword = keyword.equals("null") ? "" : keyword;
                        %>    
                        <input name="keyword" value="<%= keyword%>" type="text" class="form-control text-dark border-0" placeholder="Searching" aria-label="Search" style="outline: none;">
                        <button class="btn btn-dark" type="submit">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </nav>
</header>
<!-- end header section -->