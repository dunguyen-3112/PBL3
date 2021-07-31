package com.example.demo.service;

import com.example.demo.model.Dkmuontra;

import java.util.List;
import java.util.Optional;

public interface DKMuonTraService {
    boolean add(Dkmuontra Dkmuontra);

    boolean deleteBymaDkmuontra(Long maDkmuontra);

    boolean update(Dkmuontra Dkmuontra);

    List<Dkmuontra> findAll();

    List<Dkmuontra> findAllBymaSach(Long maSach);

    List<Dkmuontra> findBymaSinhVien(Long id);

    Optional<Dkmuontra> findBymadk(Long id);

    List<Dkmuontra> findByKeyword(String keyword);

    boolean deleteAllBymaSach(Long maSach);

    boolean deleteAllBymaSinhvien(Long maSinhvien);
}
