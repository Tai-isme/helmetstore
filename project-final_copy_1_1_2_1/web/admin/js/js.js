$(document).ready(function() {
    $('#dataTable').DataTable({
        "pageLength": 5, // Giới hạn 5 dòng mỗi trang (có thể thay đổi: 10, 25, 50, v.v.)
        "lengthChange": true, // Cho phép người dùng thay đổi số dòng mỗi trang
        "searching": true, // Bật ô tìm kiếm
        "ordering": true, // Bật sắp xếp cột
        "paging": true, // Bật phân trang
        "info": true, // Hiển thị thông tin "Showing 1 to 5 of X entries"
        "language": {
            "paginate": {
                "previous": "Trước",
                "next": "Sau"
            },
            "lengthMenu": "Hiển thị _MENU_ dòng mỗi trang",
            "info": "Hiển thị _START_ đến _END_ của _TOTAL_ dòng",
            "search": "Tìm kiếm:"
        }
    });
});