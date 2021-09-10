package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import com.example.demo.model.Message;
import com.example.demo.model.Student;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.StudentRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class indexService {

    private final GenreRepository repog;

    private final BookRepository repob;

    private final StudentRepository repos;

    private final MessageRepository repom;
    

    public List<Book> findAllBookByKeyWord(String keyword)
    {
        return repob.findAllByKeyWord(keyword);
    }

    public List<Book> findAllBookByIdGenre(Long id)
    {
        return repob.findAllByIdgenre(id);
    }

    public Genre getById(Long id)
    {
        return repog.findById(id).get();
    }

    public Book findBookById(Long id)
    {
        return repob.findById(id).get();
    }

    public boolean getStudentById(Long id,String pass)
    {
        Optional<Student> Os= repos.findById(id);
        if(!Os.isPresent()) return false;
        if(!Objects.equals(Os.get().getPassword() ,pass)) return false;
        return true;
    }
    public ResponseEntity<Message> save(Message msg)
    {
        Date d = new Date();
        msg.setTime(new java.sql.Date(d.getYear(),d.getMonth(),d.getDate()));
        System.out.println(msg);
        msg.setMcheck(false);
        return ResponseEntity.ok(repom.save(msg));
    }
}
