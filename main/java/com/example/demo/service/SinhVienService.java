package com.example.demo.service;

import com.example.demo.model.Sinhvien;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface SinhVienService {
    List<Sinhvien> getAll();

    Optional<Sinhvien> getBymaSinhvien(Long maSinhvien);

    List<Sinhvien> getByKeyword(String keyword);

    boolean add(Sinhvien Sinhvien);

    boolean update(Sinhvien Sinhvien, MultipartFile image);

    Boolean deleteAllBymaLop(Long maLop);

    boolean deleteBymaSinhVien(Long id);

    List<Sinhvien> findAllBymaLop(Long maLop);
}
