package com.example.demo.repository;

import com.example.demo.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select * from student e where ((e.name like %:keyword%) or (e.id like %:keyword%) or (e.id_class in (select id from class e1 where e1.name like %:keyword%))) ", nativeQuery = true)
    List<Student> findAllByKeyword(@Param("keyword") String keyword);

    // @Query(value="select * from student e where e.name like %:email%",nativeQuery = true)
    // List<UserS> findByEmailNumber(String email);

    List<Student> findAllByIdClass(Long id);

    @Query(value = "select * from student e where e.gender = ?1" ,nativeQuery = true) 
    List<Student> findAllBycheck(boolean gender);

    
    @Query( value="select * from student limit 100",nativeQuery = true)
    List<Student> findAllByTop();
}