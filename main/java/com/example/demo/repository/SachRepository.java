package com.example.demo.repository;

import com.example.demo.model.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SachRepository extends JpaRepository<Sach, Long> {

    List<Sach> findAllBymaTheLoai(Long maTheLoai);

    @Query(value = "select * from sach e where e.ten_sach like %:keyword%", nativeQuery = true)
    List<Sach> findByKeyword(@Param("keyword") String keyword);

}