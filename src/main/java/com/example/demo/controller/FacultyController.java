package com.example.demo.controller;

import com.example.demo.model.Faculty;
import com.example.demo.service.FacultyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "qltv/1/khoas")
public class FacultyController {

    private final FacultyService serf;

    @GetMapping
    @ResponseBody
    public List<Faculty> getAll() {
        return serf.getAll();
    }

    @PostMapping
    public Faculty save(@RequestBody Faculty faculty) {
        return serf.save(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        return serf.update(faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> delete(@PathVariable("id")Long id) {
        return serf.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getById(@PathVariable("id")Long id)
    {
        return serf.getById(id);
    }

    @GetMapping("/sortid/{i}")
    public List<Faculty> getAllBySortID(@PathVariable("i")boolean i) {
        return serf.getAllBySortID(i);
    }

    @GetMapping("/sortname/{i}")
    public List<Faculty> getAllBySortName(@PathVariable("i")boolean i) {
        return serf.getAllBySortName(i);
    }

    @GetMapping("/keyword=/{id}")
    public List<Faculty> getByIdFaculy(@PathVariable("id") String id)
    {
        return serf.getBykeyword(id);
    }

}