<!DOCTYPE html>
<html lang="en">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <c:set var="url" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin 2 - Tables</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${url}/admin/css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link href="${url}/admin/css/style.css" rel="stylesheet" type="text/css"/>
    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <%@include file="sidebar.jsp" %>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <%@include file="topbar.jsp" %>
                    <!-- End of Topbar -->


                    <!-- Begin Page Content -->
                    <div class="container">
                        <div class="form-container">
                            <h3 class="text-center mb-4">Add User </h3>
                            <form action="${url}/admin" method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="hanhdong" value="adduser">
                                <div class="mb-3">
                                    <label for="hinhavatar" class="form-label">Avatar</label>
                                    <input type="file" name="hinhavatar" class="form-control" id="hinhavatarFile" accept="image/*">
                                    <img id="previewImage" style="width: 100px; border-radius: 10px; display: none;">
                                </div>
                                <div class="mb-3">
                                    <label for="tenDangNhap" class="form-label">Username (*)</label>
                                    <input type="text" class="form-control" id="productName" name="tenDangNhap" value="" required>
                                </div>

                                <!-- Color -->
                                <div class="mb-3">
                                    <label for="matKhau" class="form-label">Password (*)</label>
                                    <input type="text" class="form-control" id="color" name="matKhau" value="" required>
                                </div>

                                <!-- Type -->
                                <div class="mb-3">
                                    <label for="hoVaTen" class="form-label">Full name</label>
                                    <input type="text" class="form-control" id="type" name="hoVaTen" value="" >
                                </div>

                                <!-- Size -->
                                <div class="mb-3">
                                    <label for="gioiTinh" class="form-label">Gender</label>
                                    <select class="form-select" id="size" name="gioiTinh" >
                                        <option value="" selected></option>
                                        <option value="Male">Male</option>
                                        <option value="Female">Female</option>
                                        <option value="Other">Other</option>

                                    </select>
                                </div>

                                <!-- Quantity -->
                                <div class="mb-3">
                                    <label for="ngaySinh" class="form-label">Birth date</label>
                                    <input type="date" class="form-control" id="quantity" name="ngaySinh" value="" >
                                </div>

                                <!-- Input Price -->
                                <div class="mb-3">
                                    <label for="soDienThoai" class="form-label">Phone number</label>
                                    <input type="tel" class="form-control" id="inputPrice" name="soDienThoai" value=""  >
                                </div>

                                <!-- Selling Price -->
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="sellingPrice" name="email" value="" >
                                </div>

                                <!-- Discount -->
                                <div class="mb-3">
                                    <label for="quocTich" class="form-label">Country</label>
                                    <input type="text" class="form-control" id="discount" name="quocTich" value="" >
                                </div>

                                <!-- Description -->
                                <div class="mb-3">
                                    <label for="diaChiKhachHang" class="form-label">Address</label>
                                    <input type="text" class="form-control" id="description" name="diaChiKhachHang" rows="3" >
                                </div>
                                <div class="mb-3">
                                    <label for="diaChiNhanHang" class="form-label">Delivery address</label>
                                    <input type="text" class="form-control" id="description" name="diaChiNhanHang" rows="3" >
                                </div>
                                <div class="mb-3">
                                    <label for="isAdmin" class="form-label">isAdmin</label>
                                    <input type="number" class="form-control" id="description" name="isAdmin" rows="3"  value="0">
                                </div>
                                <!-- Buttons -->
                                <div class="d-flex justify-content-end gap-2">
                                    <button type="submit" class="btn btn-primary btn-custom">
                                        <i class="fas fa-save"></i> Save
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2020</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>

    </body>

</html>