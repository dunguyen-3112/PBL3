package com.example.demo.service;

import com.example.demo.model.Nhanvien;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface NhanVienService {
    Nhanvien add(Nhanvien Nhanvien) throws IOException;

    boolean deleteBymaNhanvien(Long maNhanvien);

    Optional<Nhanvien> findBymaNhanvien(Long maNhanvien);

    boolean update(Nhanvien Nhanvien, MultipartFile image) throws IOException;

    List<Nhanvien> findAll();

    List<Nhanvien> findAllByChucVu(Long machucvu);

    List<Nhanvien> findAllByTen(String tennv);

    List<Nhanvien> getByKeyword(String keyword);

    Nhanvien findByemail(String email);
}
