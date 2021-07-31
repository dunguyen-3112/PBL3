package com.example.demo.repository;

import com.example.demo.model.Nhanvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanvienRepository extends JpaRepository<Nhanvien, Long>{

    List<Nhanvien> findAllBymaChucVu(Long machucvu);

    @Query(value = "select * from nhanvien e where e.tennv like %:keyword%", nativeQuery = true)
    List<Nhanvien> findByKeyword(@Param("keyword") String keyword);

    Nhanvien findByemail(String email);

    void deleteByMaNV(Long maNhanVien);


}