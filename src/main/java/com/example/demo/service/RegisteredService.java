package com.example.demo.service;

import com.example.demo.model.Registered;
import com.example.demo.model.UserS;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RegisteredRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserSRepository;
import com.example.demo.model.Book;
import com.example.demo.model.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegisteredService {

    private final RegisteredRepository repore;

    private final BookRepository repob;

    private final StudentRepository repos;

    private final UserSRepository repou;

    static Logger log = LoggerFactory.getLogger(RegisteredService.class.getName());

    public List<Registered> getAll() {
        return repore.findAll();
    }

    public Registered save(Registered re) {
        Object O = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserS user1 = repou.findByEmailNumber(((UserDetails)O).getUsername());
        Date date = new Date();
        re.setRegistrationDate(new java.sql.Date(date.getYear(), date.getMonth(), date.getDate()));
        Optional<Book> b = repob.findById(re.getIdBook());
        Optional<Student> student = repos.findById(re.getIdStudent());
        if ((b.isPresent()) && (student.isPresent()) && repob.findById(re.getIdBook()).get().isStatus()) {
            repob.findById(re.getIdBook()).get().setStatus(false);
            re.setIdUser(user1.getId());
            return repore.save(re);
        }
        return null;
    }

    public ResponseEntity<Registered> update(Registered re) {
        Optional<Registered> re1 = repore.findById(re.getId());
        if (!re1.isPresent())
            return ResponseEntity.notFound().build();
        Optional<Student> student = repos.findById(re.getIdStudent());
        if (!student.isPresent())
            return ResponseEntity.notFound().build();
        Optional<Book> b = repob.findById(re.getIdBook());
        if (!b.isPresent())
            return ResponseEntity.notFound().build();
        if (re1.get().getTime()!= re.getTime()) {
            re1.get().setTime(re.getTime());
        }
        if (!Objects.equals(re1.get().getRegistrationDate(),re.getRegistrationDate()))
            re1.get().setRegistrationDate(re.getRegistrationDate());
        if (!Objects.equals(re.isStatus(), re1.get().isStatus())
                && ((b.get().isStatus() && (re.isStatus())) || ((!b.get().isStatus() && (!re.isStatus()))))) {
            re1.get().setStatus(re.isStatus());
            if (re.isStatus()) {
                b.get().setStatus(false);
            } else {
                b.get().setStatus(true);
            }
        }
        repore.save(re1.get());
        return ResponseEntity.ok(re);
    }

    public ResponseEntity<Registered> delete(Long id) {
        Optional<Registered> re = repore.findById(id);
        if (!re.isPresent())
            return ResponseEntity.notFound().build();
        Optional<UserS> u = repou.findById(re.get().getIdUser());
        if (!u.isPresent())
            return ResponseEntity.notFound().build();
        Optional<Student> student = repos.findById(re.get().getIdStudent());
        if (!student.isPresent())
            return ResponseEntity.notFound().build();
        Optional<Book> b = repob.findById(re.get().getIdBook());
        if (!b.isPresent())
            return ResponseEntity.notFound().build();
        repore.deleteById(re.get().getId());
        b.get().setStatus(true);
        repob.save(b.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Registered> getById(Long id) {
        Optional<Registered> re = repore.findById(id);
        if (!re.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(re.get());
    }

    public List<Registered> getAllByTime(int id) {
        return repore.findAllByTime(id);
    }

    public List<Registered> getAllBySortID(boolean i) {
        Direction d = Direction.ASC, d1 = Direction.DESC;
        if (!i)
            return repore.findAll(Sort.by(d, "id"));
        else
            return repore.findAll(Sort.by(d1, "id"));
    }

    public List<Registered> getAllBySortRegistrationDate(boolean i) {
        Direction d = Direction.ASC, d1 = Direction.DESC;
        if (!i)
            return repore.findAll(Sort.by(d, "registrationDate"));
        else
            return repore.findAll(Sort.by(d1, "registrationDate"));
    }

    public List<Registered> getAllByByKeyword(String keyword) {
        return repore.findAllByKeyword(keyword);
    }

    public List<Registered> getAllByCheck() {
        Date d = new Date();
        List<Registered> list = repore.findAll();
        List<Registered> list2 = new ArrayList<>();
        list.forEach(e -> {
            Date d1 = e.getRegistrationDate();
            d1.setMonth(d1.getMonth()+e.getTime());
            log.info("M:"+d1.getMonth());
            if(d1.before(d)) list2.add(e);
        });
        return list2;
    }

    public boolean check(Long idB,Long idU) {
        Object O = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserS user1 = repou.findByEmailNumber(((UserDetails) O).getUsername());
        Optional<Registered> Ob = repore.findById(idB);
        Optional<UserS> Ou = repou.findById(idU);
        if (!Ob.isPresent())
            return false;
        if (!Ou.isPresent())
            return false;
        if (user1.getIdRole() == 1)
            return true;
        else {
            if (!Objects.equals(Ob.get().getIdUser(), user1.getId()))
                return false;
            else
                return true;
        }
    }

}
