<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


    </head>

    <body>

        <div class="hero_area">

            <!-- header -->
            <%@include file="/GUI/header.jsp" %>
            <!-- header -->

            <section class="bg-light p-3 p-md-4 p-xl-5">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-12 col-xxl-11">

                            <div class="card border-light-subtle shadow-sm">
                                <div class="row g-0">
                                    <div class="col-12 col-md-6">
                                        <img class="img-fluid rounded-start w-100 h-100 object-fit-cover" loading="lazy" src="<%=url1%>/GUI/imgsanpham/kkk.jpg" alt="">
                                    </div>
                                    <div class="col-12 col-md-6 d-flex align-items-center justify-content-center">

                                        <div class="col-12 col-lg-11 col-xl-10">
                                            <div class="card-body p-3 p-md-4 p-xl-5">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="mb-5">
                                                            <div class="text-center mb-4">
                                                                <a href="#!">
                                                                    <img src="<%=url1%>/GUI/images/logo.png" alt="BootstrapBrain Logo" width="175" height="57">
                                                                </a>
                                                            </div>
                                                            <h4 class="text-center">Login</h4>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                </div>
                                                <form action="<%= url%>/khach-hang" method="POST">
                                                    <input class="form-control" name="hanhdong" value="login" type="hidden">
                                                    <div class="row gy-3 overflow-hidden">
                                                        <c:if test="${not empty sessionScope.error}">
                                                            <div class="alert alert-${sessionScope.alert}">
                                                                ${sessionScope.error}
                                                            </div>
                                                            <c:remove var="error" scope="session"/>
                                                            <c:remove var="alert" scope="session"/>
                                                        </c:if>

                                                        <div class="col-12">
                                                            <div class="form-floating mb-3">
                                                                <input type="text" class="form-control" name="username" id="username" placeholder="Username" value="${username}" >
                                                                <label for="username" class="form-label">User name</label>
                                                            </div>
                                                        </div>
                                                        <div class="col-12">
                                                            <div class="form-floating mb-3">
                                                                <input type="password" class="form-control" name="password" id="password" value="" placeholder="Password" >
                                                                <label for="password" class="form-label">Password</label>
                                                            </div>
                                                        </div>
                                                        <div class="col-12">
                                                            <div class="d-grid">
                                                                <button class="btn btn-dark btn-lg" type="submit">Sign in</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div class="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-center mt-5">
                                                            <a href="<%=url1%>/khachhang/register.jsp" class="link-secondary text-decoration-none">Create new account</a>
                                                            <a href="<%=url1%>/khachhang/resetpassword.jsp" class="link-secondary text-decoration-none">Forgot password</a>
                                                        </div> 
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </section>

    <script src="<%= url1%>/GUI/js/jquery-3.4.1.min.js"></script>
    <script src="<%= url1%>/GUI/js/bootstrap.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
    </script>
    <script src="<%= url1%>/GUI/js/custom.js"></script>
</div>
<!-- end hero area -->
</body>

</html>