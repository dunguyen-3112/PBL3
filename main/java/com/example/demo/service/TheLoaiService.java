package com.example.demo.service;

import com.example.demo.model.Theloai;

import java.util.List;
import java.util.Optional;

public interface TheLoaiService {
    boolean add(Theloai TheLoai);

    boolean deleteBymaTheloai(Long maTheloai);

    boolean update(Theloai TheLoai);

    List<Theloai> findAll();

    Optional<Theloai> findBymaTheloai(Long maTheloai);
}
