package com.example.demo.repository;

import com.example.demo.model.Lop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LopRepository extends JpaRepository<Lop, Long> {

    @Query(value = "select * from lop e where e.ten_lop like %:keyword%", nativeQuery = true)
    List<Lop> findByKeyword(@Param("keyword") String keyword);


    List<Lop> findAllBymaKhoa(Long maKhoa);
}