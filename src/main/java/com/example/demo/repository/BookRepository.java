package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByIdgenre(Long id);

    @Query(value ="select * from book e where e.name like %:keyword% or e.author like %:keyword% or e.id like %:keyword%", nativeQuery = true)
    List<Book> findAllByKeyWord(String keyword);

    @Query(nativeQuery = true, value="select * from book where status=?1")
    List<Book> findAllByCheck(boolean i);

    @Query( value="select * from book limit 100",nativeQuery = true)
    List<Book> findAllByTop();

    List<Book> findAllByIdUser(Long id);

}