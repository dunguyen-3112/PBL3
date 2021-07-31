package com.example.demo.repository;

import com.example.demo.model.Theloai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface TheloaiRepository extends JpaRepository<Theloai, Long> {

}