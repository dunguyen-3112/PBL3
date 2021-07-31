$(document).ready(function(){

    $('.table .editkButton').on('click',function(event){
            event.preventDefault();
            var href =$(this).attr('href');
            $.get(href,function(khoa,status)
            {
                $('#idEdit').val(khoa.maKhoa);
                $('#nameEdit').val(khoa.tenKhoa);
            });
            $('#editModal').modal();
    });

    $('.table .deleteButton').on('click',function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href',href);
        $('#deleteModal').modal();
    });

    $('.table .editcvButton').on('click',function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href,function(chucvu,status){
            $('#editmaChucVu').val(chucvu.maChucVu);
            $('#edittenChucVu').val(chucvu.tenChucVu);
        });
        $('#editChucVu').modal();
    });

    $('.table .edittlButton').on('click',function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href,function(theloai,status){
            $('#editidTheLoai').val(theloai.maTheLoai);
            $('#edittenTheLoai').val(theloai.tenTheLoai);
        });
        $('#editModal').modal();
    });


     $('.table .editlButton').on('click',function(event){
            event.preventDefault();
            var href = $(this).attr('href');
            $.get(href,function(lop,status){
                $('#editmaLop').val(lop.maLop);
                $('#edittenLop').val(lop.tenLop);
                $('#editmaKhoa').val(lop.maKhoa);
            });
            $('#editLopModal').modal();
        });

        $('.table .editnvButton').on('click',function(event){
            event.preventDefault();
            var href =$(this).attr('href');
            $.get(href,function(nhanvien,status){
                $('#editmaNV').val(nhanvien.maNV);
                $('#edittenNV').val(nhanvien.tenNV);
                $('#editngaySinh').val(nhanvien.ngaySinh);
                $('#editgender').val(nhanvien.gender);
                $('#editaddress').val(nhanvien.address);
                $('#editcccd').val(nhanvien.cccd);
                $('#editphone').val(nhanvien.phone);
                $('#editemail').val(nhanvien.email);
                $('#editmaChucVu').val(nhanvien.maChucVu);
                $('#editpassword').val(nhanvien.password)
            });
            $('#editModal').modal();
        });

        $('.table #photoButton').on('click',function(event) {
           event.preventDefault();
           var href = $(this).attr('href');
           $('#photoModal #employeePhoto').attr('src', href);
           $('#photoModal').modal();
        });

        $('.table .editsvButton').on('click',function(event){
            event.preventDefault();
            var href = $(this).attr('href');
            $.get(href,function(sinhvien,status){
                $('#maSinhVien').val(sinhvien.maSinhVien);
                $('#edittenSinhVien').val(sinhvien.tenSinhVien);
                $('#editngaySinh').val(sinhvien.ngaySinh);
                $('#editaddress').val(sinhvien.address);
                $('#editcccd').val(sinhvien.cccd);
                $('#editphone').val(sinhvien.phone);
                $('#editemail').val(sinhvien.email);
                $('#editmaLop').val(sinhvien.maLop);
                $('#ditgioiTinh').val(sinhvien.gioiTinh);
            });
            $('#editSinhVienModal').modal();
        });

        $('.table .editsButton').on('click',function(event){
            event.preventDefault();
            var href = $(this).attr('href');
            $.get(href,function(sach,status){
                $('#maSach').val(sach.maSach);
                $('#edittenSach').val(sach.tenSach);
                $('#editmaTheLoai').val(sach.maTheLoai);
                $('#edittacGia').val(sach.tacGia);
                $('#editnhaXuatBan').val(sach.nhaXuatBan);
                $('#editsoLuongbegin').val(sach.soLuongBegin);
                $('#editsotrang').val(sach.sotrang);
                $('#editgia').val(sach.gia);
                $('#editngayxb').val(sach.ngayxb);
            });
            $('#editModal').modal();
        });

        $('.table .editdkButton').on('click',function(event)
        {
            event.preventDefault();
            var href = $(this).attr('href');
            $.get(href,function(dkmuontra,status){
                $('#madk').val(dkmuontra.madk);
                $('#editmaSach').val(dkmuontra.maSach);
                $('#editmaSinhVien').val(dkmuontra.maSinhVien);
                $('#editmanv').val(dkmuontra.manv);
                $('#edittrangThai').val(dkmuontra.trangThai);
                $('#editngayMuon').val(dkmuontra.ngayMuon);
                $('#editngayTra').val(dkmuontra.ngayTra);
            });
            $('#editdkModal').modal();
        });

});