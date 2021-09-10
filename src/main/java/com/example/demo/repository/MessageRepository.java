package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message,Long>{

    @Query(value="select count(id) from message where mcheck = ?1",nativeQuery = true)
    int findAllMessByCheck(boolean b);

    @Query(value="select * from message where message like %:i%",nativeQuery = true)
    List<Message> findAllByKeyWord(String i);

    @Query(value="select * from message where mcheck = ?1",nativeQuery = true)
    List<Message> findAllByStatus(boolean b);
    
}
