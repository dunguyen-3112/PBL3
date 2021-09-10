package com.example.demo.controller;

import com.example.demo.model.UserS;
import com.example.demo.service.UserSService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "qltv/1/nhanviens")
@CrossOrigin(origins = "*")
public class UserSController {

    private final UserSService seru;

    @GetMapping
    @ResponseBody
    public List<UserS> getAll() {
        return seru.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserS> delete(@PathVariable("id") Long id) {
        return seru.delete(id);
    }

    @PutMapping("{id}/uploadfile")
    public ResponseEntity<UserS> uploadfle(@RequestParam("uploadfile") MultipartFile uploadfile,
            @PathVariable("id") Long id) {
        return seru.uploadfle(uploadfile, id);
    }

    @PutMapping
    public ResponseEntity<UserS> update(@RequestBody UserS user, MultipartFile file) {
        return seru.update(user, file);
    }

    @PostMapping
    public UserS save(@RequestBody UserS user) {
        return seru.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserS> getById(@PathVariable("id") Long id) {
        return seru.getById(id);
    }

    @GetMapping("/check/{check}")
    public List<UserS> getAllBycheck(@PathVariable("check") boolean check) {
        return seru.getAllBycheck(check);
    }

    @GetMapping("/idsort=/{id}")
    public List<UserS> getByIdRole(@PathVariable("id") Long id) {
        return seru.getByIdRole(id);

    }

    @GetMapping("/keyword=/{id}")
    public List<UserS> getByKeyword(@PathVariable("id") String keyword) {
        return seru.getByKeyword(keyword);
    }

    @GetMapping("/sortid/{i}")
    public List<UserS> getAllBySortId(@PathVariable("i") boolean i) {
        return seru.getAllBySortId(i);
    }

    @GetMapping("/sortname/{i}")
    public List<UserS> getAllBySortName(@PathVariable("i") boolean i) {
        return seru.getAllBySortName(i);
    }
}
