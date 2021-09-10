package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "qltv/1/chucvus")
@CrossOrigin(origins = "*")
public class RoleController {

    private final RoleService serr;

    @GetMapping
    @ResponseBody
    public List<Role> getAll() {
        return serr.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Role> getById(@PathVariable("id") long id) {
        return serr.getbyId(id);
    }

    @PostMapping
    public Role save(@RequestBody Role chucvu)
    {
        return serr.save(chucvu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> delete(@PathVariable("id")Long id)
    {
        return serr.delete(id);
    } 

    @PutMapping
    public ResponseEntity<Role> update(@RequestBody Role chucvu)
    {
        return serr.update(chucvu);
    }

    @GetMapping("/sortname/{i}")
    public List<Role> getAllBySortName(@PathVariable("i")boolean i) {
        return serr.getAllBySortName(i);
    }

    @GetMapping("/sortid/{i}")
    public List<Role> getAllBySortID(@PathVariable("i")boolean i) {
        return serr.getAllBySortID(i);
    }

    @GetMapping("/keyword=/{i}")
    public List<Role> getAllByKeyword(@PathVariable("i")String i) {
        return serr.getAllByKeyword(i);
    }
}