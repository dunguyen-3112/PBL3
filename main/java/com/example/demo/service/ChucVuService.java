package com.example.demo.service;

import com.example.demo.model.Chucvu;

import java.util.List;
import java.util.Optional;


public interface ChucVuService {
    String add(Chucvu Chucvu);

    String deleteBymaChucvu(Long maChucvu);

    String update(Chucvu Chucvu);

    List<Chucvu> findAll();

    Optional<Chucvu> findBymaChucvu(Long maChucvu);
}
