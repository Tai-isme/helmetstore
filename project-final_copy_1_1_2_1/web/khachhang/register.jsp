<%
    String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();;
%>

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
  <link rel="shortcut icon" href="<%= url1 %>/GUI/images/favicon.png" type="image/x-icon">

  <title>
    THT Helmet Store

  </title>

  <!-- slider stylesheet -->
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

  <!-- bootstrap core css -->
  <link rel="stylesheet" type="text/css" href="<%= url1 %>/GUI/css/bootstrap.css" />

  <!-- Custom styles for this template -->
  <link href="<%= url1 %>/GUI/css/style.css" rel="stylesheet" />
  <!-- responsive style -->
  <link href="<%= url1 %>/GUI/css/responsive.css" rel="stylesheet" />
</head>
<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<!-- Login 8 - Bootstrap Brain Component -->

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
                            <img class="img-fluid rounded-start w-100 h-100 object-fit-cover" loading="lazy" src="<%= url %>/GUI/imgsanpham/kkk.jpg" alt="">
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
                                                <h4 class="text-center">Register</h4>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                    </div>
                                    <form action="#!">
                                        <div class="row gy-3 overflow-hidden">

                                            <div class="col-12">
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control" name="username" id="username" value="" placeholder="Username" required>
                                                    <label for="username" class="form-label">Username</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating mb-3">
                                                    <input type="password" class="form-control" name="password" id="password" value="" placeholder="Password" required>
                                                    <label for="password" class="form-label">Password</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating mb-3">
                                                    <input type="password" class="form-control" name="confirmpassword" id="confirmpassword" value="" placeholder="Confirm password" required>
                                                    <label for="confirmpassword" class="form-label">Confirm password</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating mb-3">
                                                    <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com" required>
                                                    <label for="email" class="form-label">Email</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                            </div>
                                            <div class="col-12">
                                                <div class="d-grid">
                                                    <button class="btn btn-dark btn-lg" type="submit">Sign up</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-center mt-5">
                                                        <a href="login.jsp" class="link-secondary text-decoration-none">Have an account?</a>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

  <script src="<%= url1 %>/GUI/js/jquery-3.4.1.min.js"></script>
  <script src="<%= url1 %>/GUI/js/bootstrap.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
  </script>
  <script src="<%= url1 %>/GUI/js/custom.js"></script>