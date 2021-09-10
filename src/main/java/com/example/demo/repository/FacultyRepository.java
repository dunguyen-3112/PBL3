package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Query(value="select * from faculty e where e.name like %:id%",nativeQuery = true)
    List<Faculty> findAllByKeyWord(String id);
}