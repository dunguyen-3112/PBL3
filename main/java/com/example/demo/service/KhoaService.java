package com.example.demo.service;

import com.example.demo.model.Khoa;

import java.util.List;
import java.util.Optional;

public interface KhoaService {
    boolean add(Khoa Khoa);

    boolean deleteBymaKhoa(Long maKhoa);

    boolean update(Khoa Khoa);

    List<Khoa> findAll();

    Optional<Khoa> findBymaKhoa(Long maKhoa);
}
