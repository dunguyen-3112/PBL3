package com.example.demo.service;

import com.example.demo.model.Registered;
import com.example.demo.model.Student;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.RegisteredRepository;
import com.example.demo.repository.StudentRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private static String path = "D:\\images\\sv\\";

    private final StudentRepository reposv;

    private final RegisteredRepository repore;

    private final ClassRepository repoclass;

    public List<Student> getAll() {
        return reposv.findAllByTop();
    }

    public Student save(Student sinhVien) {
        return reposv.save(sinhVien);
    }

    public ResponseEntity<?> savefile(MultipartFile uploadfile, Long id) {
        FileOutputStream fos = null;
        Optional<Student> Ostudent  = reposv.findById(id);
        if(!Ostudent.isPresent())
        return ResponseEntity.notFound().build();
        try {
            if ((uploadfile != null) && (!uploadfile.isEmpty())) {
                fos = new FileOutputStream(path + id + ".jpg");
                fos.write(uploadfile.getBytes());
                fos.close();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Student> delete(Long id) {
        Optional<Student> Ostudent = reposv.findById(id);
        if (!Ostudent.isPresent())
            return ResponseEntity.notFound().build();
        if(!repoclass.findById(Ostudent.get().getIdClass()).isPresent())return ResponseEntity.notFound().build();
        List<Registered> res = repore.findAllByIdStudent(id);
        res.forEach(e->{
            repore.deleteById(e.getId());
        });
        File file = new File(path + id + ".jpg");
        file.delete();
        reposv.delete(Ostudent.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Student> update(Student student) {
        Optional<Student> Osinhvien = reposv.findById(student.getId());
        if (!Osinhvien.isPresent())
            return ResponseEntity.notFound().build();
        if (!Objects.equals(student.getAddress(), Osinhvien.get().getAddress()))
            Osinhvien.get().setAddress(student.getAddress());
        if (!Objects.equals(student.getCode(), Osinhvien.get().getCode()))
            Osinhvien.get().setCode(student.getCode());
        if (!Objects.equals(student.getBirthdate(), Osinhvien.get().getBirthdate()))
            Osinhvien.get().setBirthdate(student.getBirthdate());
        if (!Objects.equals(student.getEmailNumber(), Osinhvien.get().getEmailNumber()))
            Osinhvien.get().setEmailNumber(student.getEmailNumber());
        if (!Objects.equals(student.getIdClass(), Osinhvien.get().getIdClass()))
            Osinhvien.get().setIdClass(student.getIdClass());
        if (!Objects.equals(student.getPhoneNumber(), Osinhvien.get().getPhoneNumber()))
            Osinhvien.get().setPhoneNumber(student.getPhoneNumber());
        if (!Objects.equals(student.getName(), Osinhvien.get().getName()))
            Osinhvien.get().setName(student.getName());
        if (!Objects.equals(student.isGender(), Osinhvien.get().isGender()))
            Osinhvien.get().setGender(student.isGender());
        if(!Objects.equals(student.getPassword(),Osinhvien.get().getPassword()))
        Osinhvien.get().setPassword(student.getPassword());
        return ResponseEntity.ok(reposv.save(Osinhvien.get()));
    }

    public ResponseEntity<Student> getById( Long id) {
        Optional<Student> Osinhvien = reposv.findById(id);
        if (!Osinhvien.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(Osinhvien.get());
    }

    public List<Student> getAllByIdClass(Long id) {
        return reposv.findAllByIdClass(id);
    }

    public List<Student> getAllBykeyword(String keyword) {
        return reposv.findAllByKeyword(keyword);
    }

    public List<Student> getAllBySortId(boolean i) {
        Direction d = Direction.ASC,d1=Direction.DESC;
        if(!i) return reposv.findAll(Sort.by(d, "id"));
        else return reposv.findAll(Sort.by(d1, "id"));
    }

    public List<Student> getAllBySortName(boolean i) {
        Direction d = Direction.ASC,d1=Direction.DESC;
        if(!i) return reposv.findAll(Sort.by(d, "name"));
        else return reposv.findAll(Sort.by(d1, "name"));
    }

    public boolean Security(Long id,String pass)
    {
        Optional<Student> Os= reposv.findById(id);
        if(!Os.isPresent()) return false;
        if(!Objects.equals(Os.get().getPassword() ,pass)) return false;
        return true;
    }

    public List<Student> getAllBycheck(boolean check) {
        return reposv.findAllBycheck(check);
    }
}
