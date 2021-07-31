package com.example.demo.service;

import com.example.demo.model.Theloai;
import com.example.demo.repository.SachRepository;
import com.example.demo.repository.TheloaiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TheLoaiServiceImpl implements TheLoaiService {

    private final TheloaiRepository theloaiRepository;

    public TheLoaiServiceImpl(TheloaiRepository theloaiRepository, SachRepository sachRepository) {
        this.theloaiRepository = theloaiRepository;
    }

    @Override
    public boolean add(Theloai TheLoai) {
        boolean check = false;
        try {
            theloaiRepository.save(TheLoai);
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean deleteBymaTheloai(Long maTheloai) {
        boolean check = false;
        try {
            theloaiRepository.delete(theloaiRepository.findById(maTheloai).get());
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    @Transactional
    public boolean update(Theloai TheLoai) {
        boolean check = false;
        try {
            Theloai theLoai = theloaiRepository.findById(TheLoai.getMaTheLoai()).get();
            if (!Objects.equals(theLoai.getTenTheLoai(), TheLoai.getTenTheLoai())) {
                theLoai.setTenTheLoai(TheLoai.getTenTheLoai());
            }
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Theloai> findAll() {
        try {
            List<Theloai> categories = theloaiRepository.findAll();
            return categories;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Theloai> findBymaTheloai(Long maTheloai) {
        Optional<Theloai> theloai = null;
        try {
            theloai = theloaiRepository.findById(maTheloai);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return theloai;
    }
}
