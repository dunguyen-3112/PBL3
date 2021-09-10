package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String string);

    @Query(value="select * from role e where e.name like %:i%",nativeQuery = true)
    List<Role> findAllByKeyword(String i);

}