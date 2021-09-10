package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Message;
import com.example.demo.service.MessageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value="qltv/1/messages")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageService serm;

    @GetMapping
    public List<Message> findAll()
    {
        return serm.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable("id")Long id)
    {
        return serm.getById(id);
    }

    @GetMapping("/check/{check}")
    public List<Message> check(@PathVariable("check")boolean check)
    {
        return serm.getAllCheck(check);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable("id")Long id)
    {
        System.out.println(id);
        return serm.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<Message> save(@RequestBody Message msg)
    {
        return serm.update(msg);
    }

    @GetMapping("/sortid/{i}")
    public List<Message> getAllBySortID(@PathVariable("i")boolean i) {
        return serm.getAllBySortID(i);
    }

    @GetMapping("/keyword=/{i}")
    public List<Message> getAllBykeyword(@PathVariable("i")String i) {
        return serm.getAllBykeyword(i);
    }

    @GetMapping("/sortname/{i}")
    public List<Message> getAllBySortName(@PathVariable("i")boolean i) {
        return serm.getAllBySortName(i);
    }
    
}
