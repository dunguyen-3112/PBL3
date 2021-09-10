package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@AllArgsConstructor
@RequestMapping(path = "qltv/1/sachs")
@CrossOrigin("http://14.175.254.53:8080")
public class BookController {

    private final BookService serb;

    static Logger log = LoggerFactory.getLogger(BookController.class.getName());

    @GetMapping
    @ResponseBody
    public List<Book> getAll() {
        return serb.getAll();
    }

    @PostMapping
    public Book save(@RequestBody Book book) {
        return serb.save(book);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable("id") Long id) {
        log.info("id:"+id);
        return serb.delete(id);
    }

    
    @GetMapping("/check/{i}")
    public List<Book> getAllByCheck(@PathVariable("i")boolean i) {
        return serb.getAllByCheck(i);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id) {
        return serb.getById(id);
    }

    @GetMapping("/idsort=/{id}")
    public List<Book> getByIdgenre(@PathVariable("id") Long id) {
        return serb.getByIdgenre(id);
    }

    @PutMapping
    public ResponseEntity<Book> update(@RequestBody Book book, MultipartFile file) {
        return serb.update(book, file);
    }

    @PutMapping("{id}/uploadfile")
    public ResponseEntity<Book> update(@RequestParam("uploadfile") MultipartFile uploadfile,
            @PathVariable("id") Long id) {
        return serb.update(uploadfile, id);
    }

    @GetMapping("/sortid/{i}")
    public List<Book> getAllBySortID(@PathVariable("i") boolean i) {
        return serb.getAllBySortID(i);
    }

    @GetMapping("/sortname/{i}")
    public List<Book> getAllBySortName(@PathVariable("i") boolean i) {
        return serb.getAllBySortName(i);
    }

    @GetMapping("keyword=/{keyword}")
    public List<Book> findAllBookByKeyWord(@PathVariable("keyword") String keyword) {
        return serb.findAllBookByKeyWord(keyword);
    }

    @GetMapping("check/{idB}/{idU}")
    public boolean findAllBookByKeyWord(@PathVariable("idB") Long idB,@PathVariable("idU")Long idU) {
        return serb.check(idB, idU);
    }

}
