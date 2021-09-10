package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.demo.model.UserS;

@Repository
public interface UserSRepository extends JpaRepository<UserS, Long>{

    @Query(value = "select * from user e where e.name like %:keyword%", nativeQuery = true)
    List<UserS> findAllByKeyword(@Param("keyword") String keyword);

    List<UserS> findAllByEmailNumber(String emailNumber);

    UserS findByEmailNumber(String username);

    List<UserS> findAllByIdRole(Long id);

    @Query(value = "delete from user where id_role = ?1", nativeQuery = true)
    void deleteAllByIdRole(Long id);

    @Query(value = "select * from user where gender = ?1", nativeQuery = true)
    List<UserS> findAllBycheck(boolean gender);

}