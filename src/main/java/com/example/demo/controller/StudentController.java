package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

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

@RestController
@RequestMapping(path = "qltv/1/sinhviens")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService serst;

    @GetMapping
    @ResponseBody
    public List<Student> getAll() {
        return serst.getAll();
    }

    @PostMapping
    public Student save(@RequestBody Student sinhVien) {
        return serst.save(sinhVien);
    }

    @PutMapping("{id}/uploadfile")
    public ResponseEntity<?> savefile(@RequestParam("uploadfile") MultipartFile uploadfile,
            @PathVariable("id") Long id) {
        return serst.savefile(uploadfile, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") Long id) {
        return serst.delete(id);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        return serst.update(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") Long id) {
        return serst.getById(id);
    }

    @GetMapping("/idsort=/{id}")
    public List<Student> getAllByIdClass(@PathVariable("id") Long id) {
        return serst.getAllByIdClass(id);
    }

    @GetMapping("/keyword=/{id}")
    public List<Student> getAllBykeyword(@PathVariable("id") String keyword) {
        return serst.getAllBykeyword(keyword);
    }

    @GetMapping("/check/{check}")
    public List<Student> getAllBycheck(@PathVariable("check") boolean check) {
        return serst.getAllBycheck(check);
    }

    @GetMapping("/sortid/{i}")
    public List<Student> getAllBySortId(@PathVariable("i")boolean i) {
        return serst.getAllBySortId(i);
    }

    @GetMapping("/sortname/{i}")
    public List<Student> getAllBySortName(@PathVariable("i")boolean i) {
        return serst.getAllBySortName(i);
    }
}
