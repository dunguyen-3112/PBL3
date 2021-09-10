package com.example.demo.repository;

import com.example.demo.model.Class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {

    @Query(value = "select * from class  where name like %:keyword% ", nativeQuery = true)
    List<Class> findByKeyword(@Param("keyword") String keyword);

    List<Class> findAllByIdFaculty(Long id);

}