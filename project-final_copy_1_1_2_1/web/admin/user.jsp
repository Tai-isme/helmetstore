<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin 2 - Tables</title>

        <!-- Custom fonts for this template -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- CSS -->
        <link href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link href="${url}/admin/css/sb-admin-2.min.css" rel="stylesheet">
        <link href="${url}/admin/css/style.css" rel="stylesheet">
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
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Tables</h1>
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <form action="${url}/admin" method="POST">
                                    <input type="hidden" name="hanhdong" value="createuser">
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fas fa-plus"></i> Add new user
                                    </button>
                                </form>
                            </div>

                            <div class="card-body">
                                <div class="table-responsive">

                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Avatar</th>
                                                <th>ID</th>
                                                <th>User name</th>
                                                <th>Pass word</th>
                                                <th>Full name</th>
                                                <th>Gender</th>
                                                <th>Birthdate</th>
                                                <th>Phone</th>
                                                <th>Email</th>
                                                <th>Country</th>
                                                <th>Address</th>
                                                <th>Delivery address</th>
                                                <th>isAdmin</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>Avatar</th>
                                                <th>ID</th>
                                                <th>User name</th>
                                                <th>Pass word</th>
                                                <th>Full name</th>
                                                <th>Gender</th>
                                                <th>Birthdate</th>
                                                <th>Phone</th>
                                                <th>Email</th>
                                                <th>Country</th>
                                                <th>Address</th>
                                                <th>Delivery address</th>
                                                <th>isAdmin</th>
                                                <th></th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach var="khachhang" items="${requestScope.userList}">
                                                <tr>
                                                    <td><img style="width: 30px; height: 30px; border-radius: 50%" src="${pageContext.request.contextPath}/uploads/${khachhang.hinhAvatar}"  alt=""></td>
                                                    <td>${khachhang.maKhachHang}</td>
                                                    <td>${khachhang.tenDangNhap}</td>
                                                    <td>${khachhang.matKhau}</td>
                                                    <td>${khachhang.hoVaTen}</td>
                                                    <td>${khachhang.gioiTinh}</td>
                                                    <td>${khachhang.ngaySinh}</td>
                                                    <td>${khachhang.soDienThoai}</td>
                                                    <td>${khachhang.email}</td>
                                                    <td>${khachhang.quocTich}</td>
                                                    <td>${khachhang.diaChiKhachHang}</td>
                                                    <td>${khachhang.diaChiNhanHang}</td>
                                                    <td>${khachhang.isAdmin}</td>
                                                    <td>
                                                        <div class="d-inline-flex gap-2">
                                                            <form action="${url}/admin" method="POST" class="d-inline">
                                                                <input type="hidden" name="hanhdong" value="edituser">
                                                                <input type="hidden" name="maKhachHang" value="${khachhang.maKhachHang}">
                                                                <button type="submit" class="btn btn-secondary btn-sm">Edit</button>
                                                            </form>

                                                            <form action="${url}/admin" method="POST" class="d-inline">
                                                                <input type="hidden" name="hanhdong" value="deleteuser">
                                                                <input type="hidden" name="maKhachHang" value="${khachhang.maKhachHang}">
                                                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                                            </form>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
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
        <!-- JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap4.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables.js"></script>

    </body>
</html>