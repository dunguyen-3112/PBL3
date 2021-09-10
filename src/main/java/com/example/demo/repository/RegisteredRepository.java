package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Registered;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredRepository extends JpaRepository<Registered, Long>{

    @Query(nativeQuery = true ,value = "select * from Registered where id_Book =?1 and status=?2")
    Optional<Registered> findByIdStatusTrue(Long idBook,boolean status);

    List<Registered> findAllByTime(int id);

    @Query(nativeQuery = true, value="select * from Registered where id_book in (select id from Book where name like %:keyword%)")
    List<Registered> findAllByKeyword (String keyword );

    List<Registered> findAllByIdStudent(Long id);

    List<Registered> findAllByIdBook(Long id);

    List<Registered> findAllByIdUser(Long id);

}