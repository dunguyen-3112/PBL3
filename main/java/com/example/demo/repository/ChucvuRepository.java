package com.example.demo.repository;

import com.example.demo.model.Chucvu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChucvuRepository extends JpaRepository<Chucvu, Long> {

    public void deleteByMaChucVu(Long maChucVu);

}