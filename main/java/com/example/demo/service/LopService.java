package com.example.demo.service;

import com.example.demo.model.Lop;

import java.util.List;
import java.util.Optional;

public interface LopService {
    boolean add(Lop Lop);

    boolean deleteBymaLop(Long maLop);

    boolean update(Lop Lop);

    List<Lop> findAll();

    Optional<Lop> findBymaLop(Long maLop);

    List<Lop> findAllByKeyword(String keyword);


    List<Lop> findAllBymaKhoa(Long maKhoa);

    boolean deleteAll(List<Lop> allBymaKhoa);
}
