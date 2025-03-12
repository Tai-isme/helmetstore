<%
    String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();;
%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- slider stylesheet -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="<%= url1%>/GUI/css/bootstrap.css" />

        <!-- Custom styles for this template -->
        <link href="<%= url1%>/GUI/css/style.css" rel="stylesheet" />
        <!-- responsive style -->
        <link href="<%= url1%>/GUI/css/responsive.css" rel="stylesheet" />

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </head>
    <body>

        <%@include file="../GUI/header.jsp" %>
        <%            if (khachHang == null) {
        %>
        <h1 class="red">Bạn chưa đăng nhập</h1>
        <%
        } else {
        %>
        <div class="row">
            <div class="col-12">
                <!-- Page title -->
                <div class="my-5 d-flex justify-content-center">
                    <h3>My Profile</h3>
                    <hr>
                </div>
                <!-- Form START -->

                <div class="file-upload" method="post" action="<%=url%>/khach-hang" enctype="multipart/form-data">

                    <div class="container-xl px-4 mt-4">
                        <hr class="mt-0 mb-4">
                        <div class="row">


                            <div class="col-xl-4">
                                <form class="form-upload-avt" action="<%=url%>/khach-hang" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="hanhdong" value="updateAvatar"> 
                                    <input type="hidden" name="maKhachHang" value="<%= khachHang.getMaKhachHang()%>"> 
                                    <!-- Profile picture card-->
                                    <div class="card mb-4 mb-xl-0">
                                        <div class="card-header text-center">Your Avatar</div>
                                        <div class="card-body text-center">
                                            <!-- Profile picture image-->
                                            <div>
                                                <div class="d-flex justify-content-center mb-4">
                                                    
                                                    <img id="selectedAvatar" src="<%=url%>/uploads/<%=khachHang.getHinhAvatar()%>"
                                                         class="rounded-circle" style="width: 200px; height: 200px; object-fit: cover;" alt="" />
                                                </div>
                                                <div class="d-flex justify-content-center">
                                                    <div data-mdb-ripple-init class=" btn-rounded">
                                                        <label class="form-label" for="customFile">Choose new Avatar</label>
                                                        <input type="file" name="avatar" class="form-control" id="customFile" onchange="displaySelectedImage(event, 'selectedAvatar')"/>  
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="gap-3 mt-2 d-md-flex justify-content-md-center text-center">
                                                <button type="submit" class="btn btn-dark">
                                                    <label class="text-white m-0" for="" style="cursor: pointer;">Update Avatar</label>
                                                </button>


                                            </div>

                                        </div>
                                    </div>
                                </form>        
                            </div>




                            <div class="col-xl-8">
                                <form class="form-upload-information" action="<%=url%>/khach-hang" >
                                    <input type="hidden" name="hanhdong" value="updateProfile"> 
                                    <input type="hidden" name="maKhachHang" value="<%= khachHang.getMaKhachHang()%>"> 
                                    <div class="card mb-4">
                                        <div class="card-header text-center">Account Details</div>
                                        <div class="card-body">
                                            <div class="row gx-3 mb-3">
                                                <div class="col-md-6">
                                                    <label class="small mb-1" for="hoVaTen">Full name</label>
                                                    <input class="form-control" id="hoVaTen" type="text" placeholder="Enter full name" name="hoVaTen" value="<%=khachHang.getHoVaTen()%>">
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="form-label">Phone number</label>
                                                    <input type="text" class="form-control" placeholder="Enter phone number" name="soDienThoai" value="<%= khachHang.getSoDienThoai()%>">
                                                </div>
                                            </div>

                                            <div class="row gx-3 mb-3">
                                                <div class="col-md-6">
                                                    <label class="form-label">Birthdate</label>
                                                    <input type="date" class="form-control" placeholder="" name="ngaySinh" value="<%=khachHang.getNgaySinh()%>">
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="form-label">Gender</label>
                                                    <select class="form-control" name="gioiTinh">
                                                        <option value="male"> <%= khachHang.getGioiTinh()%></option>
                                                        <option value="male">Male</option>
                                                        <option value="female">Female</option>
                                                        <option value="other">Other</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row gx-3 mb-3">
                                                <div class="col-md-6">
                                                    <label for="inputEmail4" class="form-label">Email *</label>
                                                    <input type="email" class="form-control" id="inputEmail4" placeholder="Enter email address" name="email" value="<%= khachHang.getEmail()%>">
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="form-label">Country</label>
                                                    <input type="text" class="form-control" placeholder="Enter country" name="quocGia" value="<%= khachHang.getQuocTich()%>">
                                                </div>
                                            </div>

                                            <div class="mb-3">
                                                <label class="form-label">Customer address</label>
                                                <input type="text" class="form-control" placeholder="Enter address" name="diaChiKhachHang" value="<%= khachHang.getDiaChiKhachHang()%>">
                                            </div>

                                            <div class="mb-3">
                                                <label class="form-label">Delivery address</label>
                                                <input type="text" class="form-control" placeholder="Enter delivery address" name="diaChiNhanHang" value="<%= khachHang.getDiaChiNhanHang()%>">
                                            </div>

                                            <div class="mb-3 d-flex justify-content-between align-items-center">
                                                <!-- button -->
                                                <div class="gap-3 d-md-flex justify-content-md-end text-center">
                                                    <button type="submit" class="btn btn-outline-danger">Delete profile</button>
                                                    <button type="submit" class="btn btn-dark">
                                                        <label class="text-white m-0" for="" style="cursor: pointer;">Update profile</label>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>        
                            </div>

                        </div>
                    </div>
                </div> <!-- Form END -->
            </div>
        </div>
        <% }%> 
        <script>
            function displaySelectedImage(event, elementId) {
                const selectedImage = document.getElementById(elementId);
                const fileInput = event.target;

                if (fileInput.files && fileInput.files[0]) {
                    const reader = new FileReader();

                    reader.onload = function (e) {
                        selectedImage.src = e.target.result;
                    };

                    reader.readAsDataURL(fileInput.files[0]);
                }
            }
        </script>
    </body>
</html>


