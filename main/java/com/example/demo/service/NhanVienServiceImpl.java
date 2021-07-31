package com.example.demo.service;

import com.example.demo.model.Chucvu;
import com.example.demo.model.Nhanvien;
import com.example.demo.repository.ChucvuRepository;
import com.example.demo.repository.NhanvienRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    private static String path = "D:\\images\\nv\\";

    private final NhanvienRepository nhanvienRepository;

    private final ChucvuRepository chucvuRepository;

    public NhanVienServiceImpl(NhanvienRepository nhanvienRepository, ChucvuRepository chucvuRepository) {
        this.nhanvienRepository = nhanvienRepository;
        this.chucvuRepository = chucvuRepository;
    }

    @Override
    @Transactional
    public Nhanvien add(Nhanvien nhanvien) {
        Nhanvien Nhanvien = nhanvienRepository.save(nhanvien);
        if (nhanvien != null) {
            FileOutputStream fos = null;
            FileInputStream fis = null;
            byte[] data = new byte[1024];
            try {
                fos = new FileOutputStream(path + nhanvien.getMaNV() + ".jpg");
                if (nhanvien.getGender() == 1)
                    fis = new FileInputStream(path + "male.jpg");
                else
                    fis = new FileInputStream(path + "female.jpg");
                while (fis.read(data) > 0) {
                    fos.write(data);
                }
                fis.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Chucvu chucvu = chucvuRepository.getById(nhanvien.getMaChucVu());
            chucvu.setSoLuong(chucvu.getSoLuong() + 1);
        }
        return Nhanvien;
    }

    @Override
    @Transactional
    public boolean deleteBymaNhanvien(Long maNhanvien) {
        boolean check = false;
        try {
            Nhanvien nhanvien = nhanvienRepository.findById(maNhanvien).get();
            nhanvienRepository.delete(nhanvien);
            File file = new File(path + maNhanvien + ".jpg");
            file.delete();
            Chucvu chucvu = chucvuRepository.getById(nhanvien.getMaChucVu());
            chucvu.setSoLuong(chucvu.getSoLuong() - 1);
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    @Transactional
    public boolean update(Nhanvien Nhanvien, MultipartFile image) {
        byte[] data = new byte[1024];
        FileInputStream fis;
        FileOutputStream fos;
        Nhanvien Nhanvien1 = null;
    try{
        Nhanvien1 = nhanvienRepository.findById(Nhanvien.getMaNV()).get();
        if (!Objects.equals(Nhanvien.getTenNV(), Nhanvien1.getTenNV()))
            Nhanvien1.setTenNV(Nhanvien.getTenNV());

        if (!Objects.equals(Nhanvien.getNgaySinh(), Nhanvien1.getNgaySinh()))
            Nhanvien1.setNgaySinh(Nhanvien.getNgaySinh());

        if (!Objects.equals(Nhanvien.getCccd(), Nhanvien1.getCccd()))
            Nhanvien1.setCccd(Nhanvien.getCccd());

        if (!Objects.equals(Nhanvien.getPhone(), Nhanvien1.getPhone()))
            Nhanvien1.setPhone(Nhanvien.getPhone());

        if (!Objects.equals(Nhanvien.getAddress(), Nhanvien1.getAddress()))
            Nhanvien1.setAddress(Nhanvien.getAddress());

        if (!Objects.equals(Nhanvien.getMaChucVu(), Nhanvien1.getMaChucVu()))
        {
            Chucvu chucvu1 = chucvuRepository.getById(Nhanvien.getMaChucVu()),
            chucvu2 = chucvuRepository.getById(Nhanvien1.getMaChucVu());
            chucvu1.setSoLuong(chucvu1.getSoLuong()+1);
            chucvu2.setSoLuong(chucvu2.getSoLuong()-1);
            Nhanvien1.setMaChucVu(Nhanvien.getMaChucVu());
        }

        if(!Objects.equals(Nhanvien.getEmail(),Nhanvien1.getEmail()))
            Nhanvien1.setEmail(Nhanvien.getEmail());

        if(!Objects.equals(Nhanvien.getPassword(),Nhanvien1.getPassword()))
            Nhanvien1.setPassword(Nhanvien.getPassword());

        if (image.isEmpty()) {
            if (!Objects.equals(Nhanvien.getGender(), Nhanvien1.getGender())) {
                if(!Nhanvien1.getImage()) {
                    fos = new FileOutputStream(path + Nhanvien1.getMaNV() + ".jpg");
                    if (Nhanvien.getGender() == 1)
                        fis = new FileInputStream(path + "male.jpg");
                    else
                        fis = new FileInputStream(path + "female.jpg");
                    while (fis.read(data) > 0) {
                        fos.write(data);
                    }
                    fos.close();
                    fis.close();
                }
                Nhanvien1.setGender(Nhanvien.getGender());
            }
        } else {
            if (!Objects.equals(Nhanvien.getGender(), Nhanvien1.getGender()))
                Nhanvien1.setGender(Nhanvien.getGender());
            fos = new FileOutputStream(path + Nhanvien1.getMaNV() + ".jpg");
            fos.write(image.getBytes());
            fos.close();
            Nhanvien1.setImage(true);
        }
        return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Nhanvien> findAll() {
        return nhanvienRepository.findAll();
    }

    @Override
    public Optional<Nhanvien> findBymaNhanvien(Long maNhanvien) {
        return nhanvienRepository.findById(maNhanvien);
    }

    @Override
    public List<Nhanvien> findAllByChucVu(Long machucvu) {
        return nhanvienRepository.findAllBymaChucVu(machucvu);
    }

    @Override
    public List<Nhanvien> findAllByTen(String tennv) {
        return nhanvienRepository.findByKeyword(tennv);
    }

    @Override
    public List<Nhanvien> getByKeyword(String keyword) {
        return nhanvienRepository.findByKeyword(keyword);
    }

    @Override
    public Nhanvien findByemail(String email) {
        return nhanvienRepository.findByemail(email);
    }

}
