package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Registered;
import com.example.demo.model.Role;
import com.example.demo.model.UserS;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RegisteredRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserSRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository repo;

    private final BookRepository repob;

    private final RegisteredRepository repore;

    private final UserSRepository repou;

    public List<Role> getAll() {
        return repo.findAll();
    }

    public ResponseEntity<Role> getbyId( long id) {
        Optional<Role> OChucvu = repo.findById(id);
        if (!OChucvu.isPresent())
            return ResponseEntity.notFound().build();
        Role chucvu = OChucvu.get();
        return ResponseEntity.ok().body(chucvu);
    }

    public Role save(Role chucvu)
    {
        return repo.save(chucvu);
    }

    public ResponseEntity<Role> delete(Long id)
    {
        Optional<Role> Ochucvu = repo.findById(id);
        if (!Ochucvu.isPresent())
            return ResponseEntity.notFound().build();
        List<UserS> us= repou.findAllByIdRole(id);
        us.forEach(e->{
            List<Book> books = repob.findAllByIdUser(e.getId());
            repob.deleteAll(books);
            books.forEach(e1->{
                repore.deleteAll(repore.findAllByIdBook(e1.getId()));
            });
            List<Registered> res = repore.findAllByIdUser(e.getId());
            repore.deleteAll(res);
            repou.deleteById(e.getId());
        });
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    } 

    public ResponseEntity<Role> update(Role chucvu)
    {
        Optional<Role> OChucvu = repo.findById(chucvu.getId());
        if (!OChucvu.isPresent())
            return ResponseEntity.notFound().build();
        if (!Objects.equals(OChucvu.get().getName(), chucvu.getName()))
            OChucvu.get().setName(chucvu.getName());
        repo.save(OChucvu.get());
        return ResponseEntity.ok(OChucvu.get());
    }

    public List<Role> getAllBySortName(boolean i) {
        Direction d = Direction.ASC,d1=Direction.DESC;
        if(!i) return repo.findAll(Sort.by(d, "name"));
        else return repo.findAll(Sort.by(d1, "name"));
    }

    public List<Role> getAllBySortID(boolean i) {
        Direction d = Direction.ASC,d1=Direction.DESC;
        if(!i) return repo.findAll(Sort.by(d, "id"));
        else return repo.findAll(Sort.by(d1, "id"));
    }

    public List<Role> getAllByKeyword(String i) {
        return repo.findAllByKeyword(i);
    }
}