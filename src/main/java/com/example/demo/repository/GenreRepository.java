package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query(value="select * from genre e where e.name like %:i%",nativeQuery = true)
    List<Genre> findAllByKeyword(String i);

}