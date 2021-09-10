package com.example.demo.controller;

import com.example.demo.model.Genre;
import com.example.demo.service.GenreService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "qltv/1/theloais")
@CrossOrigin(origins = "*")
public class GenreController {

    private final GenreService rerg;

    @GetMapping
    @ResponseBody
    public List<Genre> getAll(){
        return rerg.getAll();
        
    }

    @PostMapping
    public Genre save(@RequestBody Genre theLoai) {
        return rerg.save(theLoai);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genre> delete(@PathVariable("id") Long id) {
        return rerg.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getById(@PathVariable("id") Long id) {
        return rerg.getById(id);
    }

    @PutMapping
    public  ResponseEntity<Genre> Update(@RequestBody Genre genre)
    {
        return rerg.Update(genre);
    }

    @GetMapping("/sortid/{i}")
    public List<Genre> getAllBySortID(@PathVariable("i")boolean i) {
        return rerg.getAllBySortID(i);
    }

    @GetMapping("/sortname/{i}")
    public List<Genre> getAllBySortName(@PathVariable("i")boolean i) {
        return rerg.getAllBySortName(i);
    }

    @GetMapping("/keyword=/{i}")
    public List<Genre> getAllByKeyword(@PathVariable("i")String i) {
        return rerg.getAllByKeyword(i);
    }
}