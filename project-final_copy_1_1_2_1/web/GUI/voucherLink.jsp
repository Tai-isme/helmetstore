<%@page import="java.util.List"%>
<%@page import="model.MaGiamGia"%>
<%@page import="database.MaGiamGiaDAO"%>
<h1 style="text-align: center">Voucher for you</h1>


<div  class="row">
    <div  class="col-1">


                </div>
<%
    MaGiamGiaDAO maGiamGiaDAO = new MaGiamGiaDAO();
    List<MaGiamGia> listMaGiamGia = maGiamGiaDAO.selectAll();

    if (listMaGiamGia != null && !listMaGiamGia.isEmpty()) {
        for (MaGiamGia maGiamGia : listMaGiamGia) {
%>      

    <div  class="col-3">
                    <div class="card-item  ">
                        <img class="card-img" alt="anh voucher" src="<%=url1%>/GUI/imgvoucher/<%=maGiamGia.getHinhanhvoucher()%>">
                        <div class="card-body">
                            <p style="font-size: 24px;font-weight: 500 " class="card-title"><%=maGiamGia.getTenMaGiamGia()  %> </p>
                            <p class="card-text"> <h5>EXP: <small class="text-muted"><%=maGiamGia.getNgayHetHan() %></small></h5> </p>
                        </div>
                        <form class="card-getvalue" action="">
                            <button class="btn btn-primary getValue">Get</button>
                        </form>
                    </div>

                </div>

                <div  class="col-2">
</div>



<%
        }
    }
%>
                <div  class="col-1">
</div>
</div>
