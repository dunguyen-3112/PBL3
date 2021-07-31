package com.example.demo.repository;

import com.example.demo.model.Sinhvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhvienRepository extends JpaRepository<Sinhvien, Long> {
    @Query(value = "select * from sinhvien e where e.ten_sinh_vien like %:keyword%", nativeQuery = true)
    List<Sinhvien> findByKeyword(@Param("keyword") String keyword);

    void deleteAllBymaLop(Long maLop);

    List<Sinhvien> findAllBymaLop(Long maLop);

}