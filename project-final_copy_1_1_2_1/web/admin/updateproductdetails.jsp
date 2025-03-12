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
                            <h3 class="text-center mb-4">Update Product Details</h3>
                            <form action="${url}/admin" method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="hanhdong" value="updatesanpham">

                                <!-- hien thi hinh anh cu-->
                                <div class="mb-3">
                                    <label for="curentimg" class="form-label">Image</label>
                                    <img id="currentImage" style="width: 100px; border-radius: 50%" src="${pageContext.request.contextPath}/GUI/imgsanpham/${sp.hinhanhsanpham}"  alt="curentimg">
                                    <input type="hidden" name="oldImage" value="${sp.hinhanhsanpham}">
                                </div>
                                <!-- anh xem truoc -->
                                <img id="previewImage" style="width: 100px; border-radius: 10px; display: none;">

                                <!-- update hinh moi -->
                                <div class="mb-3">
                                    <label for="hinhanhsanphamFile" class="form-label">Upload New Image</label>
                                    <input type="file" name="hinhanhsanpham" class="form-control" id="hinhanhsanphamFile" accept="image/*">
                                </div>

                                <!-- ID (readonly) -->
                                <div class="mb-3">
                                    <label for="masanpham" class="form-label">Product ID</label>
                                    <input type="text" class="form-control" id="productId" name="masanpham" value="${requestScope.sp.masanpham}" readonly>
                                </div>

                                <!-- Name -->
                                <div class="mb-3">
                                    <label for="tensanpham" class="form-label">Product Name</label>
                                    <input type="text" class="form-control" id="productName" name="tensanpham" value="${requestScope.sp.tensanpham}" >
                                </div>

                                <!-- Color -->
                                <div class="mb-3">
                                    <label for="color" class="form-label">Color</label>
                                    <input type="text" class="form-control" id="color" name="mausac" value="${requestScope.sp.mausac}" >
                                </div>

                                <div class="mb-3">
                                    <label for="categoryID" class="form-label">Type (*)</label>
                                    <select class="form-select" id="kieumau" name="categoryID" required>
                                        <c:forEach var="category" items="${categories}">
                                            <option value="${category.categoryId}" >${category.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="size" class="form-label">Size (*)</label>
                                    <select class="form-select" id="size" name="size" required>
                                        <option value="S" selected>S</option>
                                        <option value="M" >M</option>
                                        <option value="L">L</option>
                                    </select>
                                </div>

                                <!-- Quantity -->
                                <div class="mb-3">
                                    <label for="quantity" class="form-label">Quantity</label>
                                    <input type="number" class="form-control" id="quantity" name="soluong" value="${requestScope.sp.soluong}" >
                                </div>

                                <!-- Input Price -->
                                <div class="mb-3">
                                    <label for="inputPrice" class="form-label">Input Price</label>
                                    <input type="number" class="form-control" id="inputPrice" name="gianhap" value="${requestScope.sp.gianhap}"  >
                                </div>

                                <!-- Selling Price -->
                                <div class="mb-3">
                                    <label for="sellingPrice" class="form-label">Selling Price</label>
                                    <input type="number" class="form-control" id="sellingPrice" name="giaban" value="${requestScope.sp.giaban}" >
                                </div>

                                <!-- Discount -->
                                <div class="mb-3">
                                    <label for="discount" class="form-label">Discount (%)</label>
                                    <input type="number" class="form-control" id="discount" name="giamgia" value="${requestScope.sp.giamgia}" >
                                </div>

                                <!-- Description -->
                                <div class="mb-3">
                                    <label for="description" class="form-label">Description</label>
                                    <textarea class="form-control" id="description" name="mota" rows="3" >${requestScope.sp.mota}</textarea>
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
        <script>
            document.getElementById("hinhanhsanphamFile").addEventListener("change", function (event) {
                let file = event.target.files[0];
                let previewImage = document.getElementById("previewImage");
                let currentImage = document.getElementById("currentImage");

                if (file) {
                    let reader = new FileReader();
                    reader.onload = function (e) {
                        previewImage.src = e.target.result;
                        previewImage.style.display = "block";
                        currentImage.style.display = "none";
                    };
                    reader.readAsDataURL(file);
                } else {
                    previewImage.style.display = "none";
                    currentImage.style.display = "block"; // Hiển thị lại ảnh cũ nếu không chọn ảnh mới
                }
            });
        </script>
    </body>
</html>