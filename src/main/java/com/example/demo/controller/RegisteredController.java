package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Registered;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.RegisteredService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "qltv/1/dkmuontras")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RegisteredController {

    private final RegisteredService serre;

    private final BookRepository repob;

    @GetMapping
    @ResponseBody
    public List<Registered> getAll()
    {
        return serre.getAll();
    }

    @PostMapping
    public Registered save(@RequestBody Registered re)
    {
        Optional<Book> Ob = repob.findById(re.getIdBook());
        if(!Ob.isPresent()) return null;
        return serre.save(re);
    }

    @PutMapping
    public ResponseEntity<Registered> update(@RequestBody Registered re)
    {
        return serre.update(re);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Registered> delete(@PathVariable("id") Long id)
    {
        return serre.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registered> getById(@PathVariable("id") Long id)
    {
        return serre.getById(id);
    }

    @GetMapping("/idsort=/{id}")
    public List<Registered> getAllByTime(@PathVariable("id") int id)
    {
        return serre.getAllByTime(id);
    }

    @GetMapping("/sortid/{i}")
    public List<Registered> getAllBySortID(@PathVariable("i")boolean i) {
         return serre.getAllBySortID(i);
    }

    @GetMapping("/sortregistrationDate/{i}")
    public List<Registered> getAllBySortRegistrationDate(@PathVariable("i")boolean i) {
        return serre.getAllBySortRegistrationDate(i);
    }


    @GetMapping("keyword=/{keyword}")
    public List<Registered> getAllByKeyword(@PathVariable("keyword")String keyword) {
        return serre.getAllByByKeyword(keyword);
    }

    @GetMapping("check")
    public List<Registered> getAllByCheck() {
        return serre.getAllByCheck();
    }

    @GetMapping("check/{idB}/{idU}")
    public boolean Check(@PathVariable("idB")Long idB,@PathVariable("idU")Long idU) {
        return serre.check(idB,idU);
    }

}
