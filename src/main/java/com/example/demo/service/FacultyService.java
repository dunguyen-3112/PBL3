package com.example.demo.service;

import com.example.demo.model.Faculty;
import com.example.demo.model.Class;
import com.example.demo.model.Student;
import com.example.demo.repository.FacultyRepository;
import com.example.demo.repository.RegisteredRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.StudentRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FacultyService {

    private final FacultyRepository repofa;

    private final ClassRepository repol;

    private final StudentRepository reposv;

    private final RegisteredRepository repore;

    public List<Faculty> getAll() {
        return repofa.findAll();
    }

    public Faculty save(Faculty faculty) {
        return repofa.save(faculty);
    }

    public ResponseEntity<Faculty> update(Faculty faculty) {
        Optional<Faculty> faculty1 = repofa.findById(faculty.getId());
        if (!faculty1.isPresent())
            return ResponseEntity.notFound().build();
        if (!Objects.equals(faculty.getName(), faculty1.get().getName()))
            faculty1.get().setName(faculty.getName());
        return ResponseEntity.ok().body(repofa.save(faculty1.get()));
    }

    public ResponseEntity<Faculty> delete(Long id) {
        Optional<Faculty> faculty1 = repofa.findById(id);
            if (!faculty1.isPresent()) 
                return ResponseEntity.notFound().build();
                List<Class> classs = repol.findAllByIdFaculty(id);
                classs.forEach(e->{
                    List<Student> student = reposv.findAllByIdClass(e.getId());
                    student.forEach(e1->{
                        repore.deleteAll(repore.findAllByIdStudent(e1.getId()));
                    });
                    reposv.deleteAll(reposv.findAllByIdClass(e.getId()));
                });
                repol.deleteAll(classs);
                repofa.delete(faculty1.get());
                return ResponseEntity.ok().build();
            
    }

    public ResponseEntity<Faculty> getById(Long id)
    {
        Optional<Faculty> Ofaculty = repofa.findById(id);
        if (!Ofaculty.isPresent()) 
            return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(Ofaculty.get());
    }

    public List<Faculty> getAllBySortID(boolean i) {
        Direction d = Direction.ASC,d1=Direction.DESC;
        if(!i)return repofa.findAll(Sort.by(d, "id"));
        return repofa.findAll(Sort.by(d1, "id"));
    }

    public List<Faculty> getAllBySortName(boolean i) {
        Direction d = Direction.ASC,d1=Direction.DESC;
        if(!i)return repofa.findAll(Sort.by(d, "name"));
        return repofa.findAll(Sort.by(d1, "name"));
    }

    public List<Faculty> getBykeyword( String id)
    {
        return repofa.findAllByKeyWord(id);
    }

}