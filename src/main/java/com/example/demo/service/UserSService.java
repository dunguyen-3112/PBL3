package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.UserS;
import com.example.demo.repository.RegisteredRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserSRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSService {

    private static String path = "D:\\images\\nv\\";

    private final UserSRepository repou;

    private final RoleRepository repor;

    private final RegisteredRepository repore;

    public List<UserS> getAll() {
        return repou.findAll();
    }

    @Transactional
    public ResponseEntity<UserS> delete(Long id) {
        Optional<UserS> Ouser = repou.findById(id);
        if (!Ouser.isPresent())
            return ResponseEntity.notFound().build();
        try {
            File file = new File(path + id + ".jpg");
            file.delete();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        Optional<Role> r = repor.findById(Ouser.get().getIdRole());
        if (!r.isPresent())
            return ResponseEntity.notFound().build();
        r.get().setNumber(r.get().getNumber() - 1);
        repor.save(r.get());
        repore.deleteAll(repore.findAllByIdUser(Ouser.get().getId()));
        repou.delete(Ouser.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UserS> uploadfle(MultipartFile uploadfile, @PathVariable("id") Long id) {
        FileOutputStream fos;
        Optional<UserS> Ouser = repou.findById(id);
        if (!Ouser.isPresent())
            return ResponseEntity.notFound().build();
        try {
            if ((uploadfile != null) && (!uploadfile.isEmpty())) {
                fos = new FileOutputStream(path + id + ".jpg");
                fos.write(uploadfile.getBytes());
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UserS> update(UserS user, MultipartFile file) {
        Optional<UserS> Ouser = repou.findById(user.getId());
        if (!Ouser.isPresent())
            return ResponseEntity.notFound().build();
        if (!Objects.equals(user.getName(), Ouser.get().getName()))
            Ouser.get().setName(user.getName());

        if (!Objects.equals(user.getBirthdate(), Ouser.get().getBirthdate()))
            Ouser.get().setBirthdate(user.getBirthdate());

        if (!Objects.equals(user.isGender(), Ouser.get().isGender()))
            Ouser.get().setGender(user.isGender());

        if (!Objects.equals(user.getCode(), Ouser.get().getCode()))
            Ouser.get().setCode(user.getCode());

        if (!Objects.equals(user.getPhoneNumber(), Ouser.get().getPhoneNumber()))
            Ouser.get().setPhoneNumber(user.getPhoneNumber());

        if (!Objects.equals(user.getAddress(), Ouser.get().getAddress()))
            Ouser.get().setAddress(user.getAddress());

        if (!Objects.equals(user.getIdRole(), Ouser.get().getIdRole())) {
            Optional<Role> r1 = repor.findById(Ouser.get().getIdRole());
            Optional<Role> r2 = repor.findById(user.getIdRole());
            if (!r1.isPresent())
                return ResponseEntity.notFound().build();
            if (!r2.isPresent())
                return ResponseEntity.notFound().build();
            r1.get().setNumber(r1.get().getNumber() - 1);
            r2.get().setNumber(r2.get().getNumber() + 1);
            repor.save(r1.get());
            repor.save(r2.get());
            Ouser.get().setIdRole(user.getIdRole());
        }

        if (!Objects.equals(user.getEmailNumber(), Ouser.get().getEmailNumber()))
            Ouser.get().setEmailNumber(user.getEmailNumber());

        if (!Objects.equals(user.getPassword(), Ouser.get().getPassword()))
            Ouser.get().setPassword(user.getPassword());
        return ResponseEntity.ok(repou.save(Ouser.get()));
    }

    public UserS save(UserS user) {
        Optional<Role> r1 = repor.findById(user.getIdRole());
        if (!r1.isPresent())
            return null;
        r1.get().setNumber(r1.get().getNumber() + 1);
        repor.save(r1.get());
        return repou.save(user);
    }

    public ResponseEntity<UserS> getById(Long id) {
        Optional<UserS> userS = repou.findById(id);
        if (!userS.isPresent())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(userS.get());
    }

    public List<UserS> getByIdRole(Long id) {
        return repou.findAllByIdRole(id);

    }

    public List<UserS> getByKeyword(String keyword) {
        return repou.findAllByKeyword(keyword);
    }

    public List<UserS> getAllBySortId(boolean i) {
        Direction d = Direction.ASC, d1 = Direction.DESC;
        System.out.println(i);
        if (!i)
            return repou.findAll(Sort.by(d, "id"));
        else
            return repou.findAll(Sort.by(d1, "id"));
    }

    public List<UserS> getAllBySortName(boolean i) {
        Direction d = Direction.ASC, d1 = Direction.DESC;
        if (!i)
            return repou.findAll(Sort.by(d, "name"));
        else
            return repou.findAll(Sort.by(d1, "name"));
    }

    public List<UserS> getAllBycheck(boolean check) {
        return repou.findAllBycheck(check);
    }
}
