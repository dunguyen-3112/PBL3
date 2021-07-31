package com.example.demo.service;

import com.example.demo.model.Sach;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface SachService {
    Boolean add(Sach Sach, MultipartFile file);

    Boolean deleteBymaSach(Long maDausach);

    Boolean update(Sach Sach,MultipartFile image);

    List<Sach> findAll();

    Optional<Sach> findBymaSach(Long masach);

    List<Sach> findAllByKeyword(String keyword);

    List<Sach> findAllBymaTheLoai(Long maTheLoai);
}
