package com.example.demo.controller;

import com.example.demo.model.Class;
import com.example.demo.service.ClassService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;

@CrossOrigin(origins = "http://14.175.254.53:8080")
@RestController
@AllArgsConstructor
@RequestMapping(path = "qltv/1/lops")
public class ClassController {

    private final ClassService serc;

    static Logger log = LoggerFactory.getLogger(ClassController.class.getName());

    @GetMapping
    @ResponseBody
    public List<Class> getAll() {
        return serc.getAll();
    }

    @PostMapping
    public Class save(@RequestBody Class Class) {
        return serc.save(Class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Class> delete(@PathVariable("id") Long id) {
        log.info("id:"+id);
        return serc.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getById(@PathVariable("id") Long id) {
        return serc.getById(id);
    }


    @GetMapping("/idsort=/{id}")
    public List<Class> getByIdFaculy(@PathVariable("id") Long id) {
        return serc.getByIdFaculy(id);
    }

    @GetMapping("/keyword=/{id}")
    public List<Class> getBykeyword(@PathVariable("id") String id) {
        return serc.getBykeyword(id);
    }

    @PutMapping
    public ResponseEntity<Class> Update(@RequestBody Class Class) {
        return serc.Update(Class);
    }

    @GetMapping("/sortid/{i}")
    public List<Class> getAllBySortID(@PathVariable("i") boolean i) {
        return serc.getAllBySortID(i);
    }

    @GetMapping("/sortname/{i}")
    public List<Class> getAllBySortName(@PathVariable("i") boolean i) {
        return serc.getAllBySortName(i);
    }
}
