package com.example.demo.service;

import com.example.demo.model.Class;
import com.example.demo.model.Registered;
import com.example.demo.model.Student;
import com.example.demo.repository.RegisteredRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.FacultyRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClassService {

    private final ClassRepository repoclass;

    private final RegisteredRepository reporegis;

    private final StudentRepository repostudent;

    private final FacultyRepository repofa;

    public List<Class> getAll()
    {
        return repoclass.findAll();
    }

    public Class save(Class Class) {
        return repoclass.save(Class);
    }

    public ResponseEntity<Class> delete( Long id) {
        Optional<Class> OClass = repoclass.findById(id);
        if (!OClass.isPresent())
            return ResponseEntity.notFound().build();
        if(!repofa.findById(OClass.get().getIdFaculty()).isPresent()) return ResponseEntity.notFound().build();
        List<Student> sinhviens = repostudent.findAllByIdClass(id);
        sinhviens.forEach(e ->{
            repostudent.deleteById(e.getId());
            List<Registered> res = reporegis.findAllByIdStudent(e.getId());
            res.forEach(e1->{
                reporegis.deleteById(e1.getId());
            });
        });
        repoclass.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Class> getById( Long id)
    {
        Optional<Class> OClass = repoclass.findById(id);
        if (!OClass.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(OClass.get());
    }

    public List<Class> getByIdFaculy( Long id)
    {
        return repoclass.findAllByIdFaculty(id);
    }

    public List<Class> getBykeyword( String id)
    {
        return repoclass.findByKeyword(id);
    }

    public ResponseEntity<Class> Update( Class Class)
    {
        Optional<Class> OClass = repoclass.findById(Class.getId());
        if (!OClass.isPresent())
            return ResponseEntity.notFound().build();
        Class Class1 = OClass.get();
        if (!Objects.equals(Class1.getName(), Class.getName()))
            Class1.setName(Class.getName());
        if (!Objects.equals(Class1.getIdFaculty(), Class.getIdFaculty()))
            Class1.setIdFaculty(Class.getIdFaculty());
        repoclass.save(Class1);
        return ResponseEntity.ok(Class1);
    }

    public List<Class> getAllBySortID(boolean i) {
        Direction d = Direction.ASC,d1=Direction.DESC;
        if(!i)return repoclass.findAll(Sort.by(d, "id"));
        return repoclass.findAll(Sort.by(d1, "id"));
    }

    public List<Class> getAllBySortName(boolean i) {
        Direction d = Direction.ASC,d1=Direction.DESC;
        if(!i)return repoclass.findAll(Sort.by(d, "name"));
        return repoclass.findAll(Sort.by(d1, "name"));
    }
}
