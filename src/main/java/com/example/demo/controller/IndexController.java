package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import com.example.demo.model.Message;
import com.example.demo.service.BookService;
import com.example.demo.service.GenreService;
import com.example.demo.service.MessageService;
import com.example.demo.service.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="qltv/0")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class IndexController {

    private final GenreService serg;

    private final BookService serb;

    private final StudentService serst;

    private final MessageService serm;
    
    @GetMapping("theloais")
    public List<Genre> findAllGenre()
    {
        return serg.getAll();
    }

    @GetMapping("sachs")
    public List<Book> findAllBook()
    {
        return serb.getAll();
    }

    @GetMapping("sachs/{keyword}")
    public List<Book> findAllBookByKeyWord(@PathVariable("keyword")String keyword)
    {
        return serb.findAllBookByKeyWord(keyword);
    }

    @GetMapping("sachs/idGenre=/{id}")
    public List<Book> findAllBookByIdGenre(@PathVariable("id")Long id)
    {
        return serb.getByIdgenre(id);
    }

    @GetMapping("theloais/{id}")
    public ResponseEntity<Genre> findById(@PathVariable("id")Long id)
    {
        return serg.getById(id);
    }

    @GetMapping("sachs/id=/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable("id")Long id)
    {
        return serb.getById(id);
    }

    @GetMapping("sinhviens/{id}/{pass}")
    public boolean getStudentById(@PathVariable("id")Long id,@PathVariable("pass") String pass)
    {
        return serst.Security(id, pass);
    }
    @PostMapping("message")
    public ResponseEntity<Message> save(@RequestBody Message msg)
    {
        return serm.save(msg);
    }
}
