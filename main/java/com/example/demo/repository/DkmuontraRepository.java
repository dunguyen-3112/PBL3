package com.example.demo.repository;

import com.example.demo.model.Dkmuontra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DkmuontraRepository extends JpaRepository<Dkmuontra, Long>{

    @Query(nativeQuery = true, value="select * from dkmuontra where ma_sach=?1")
    List<Dkmuontra> findAllBymaSach (Long maSach );

    @Query(nativeQuery = true, value="select * from dkmuontra where ma_sinh_vien=?1")
    List<Dkmuontra> findAllBymaSinhVien(Long maSinhvien);
}