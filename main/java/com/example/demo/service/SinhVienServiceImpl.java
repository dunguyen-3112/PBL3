package com.example.demo.service;

import com.example.demo.model.Sinhvien;
import com.example.demo.repository.SinhvienRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SinhVienServiceImpl implements SinhVienService {

    private static String path = "D:\\images\\sv\\";

    private final SinhvienRepository SinhvienRepository;

    protected final DKMuonTraService dkMuonTraService;

    public SinhVienServiceImpl(SinhvienRepository sinhvienRepository, DKMuonTraService dkMuonTraService) {
        SinhvienRepository = sinhvienRepository;
        this.dkMuonTraService = dkMuonTraService;
    }

    @Override
    public List<Sinhvien> getAll() {
        return SinhvienRepository.findAll();
    }

    @Override
    public Optional<Sinhvien> getBymaSinhvien(Long maSinhvien) {
        return SinhvienRepository.findById(maSinhvien);
    }

    @Override
    public List<Sinhvien> getByKeyword(String keyword) {
        return SinhvienRepository.findByKeyword(keyword);
    }

    @Override
    public boolean add(Sinhvien Sinhvien) {
        boolean check = false;
        FileOutputStream fos = null;
        FileInputStream fis = null;
        byte[] date = new byte[1024];
        Sinhvien Sinhvien1 = SinhvienRepository.save(Sinhvien);
        if (Sinhvien1 != null) {
            try {
                if (Sinhvien1.getGioiTinh() == 1)
                    fis = new FileInputStream(path + "male.jpg");
                else
                    fis = new FileInputStream(path + "female.jpg");

                fos = new FileOutputStream(path + Sinhvien1.getMaSinhVien() + ".jpg");
                while (fis.read(date) > 0) {
                    fos.write(date);
                }
                fis.close();
                fos.close();
                check = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    @Override
    @Transactional
    public boolean update(Sinhvien Sinhvien, MultipartFile image) {
        boolean check = false;
        byte[] data = new byte[1024];
        FileInputStream fis;
        FileOutputStream fos;
        Sinhvien Sinhvien1 = null;
        try {
            Sinhvien1 = SinhvienRepository.findById(Sinhvien.getMaSinhVien()).get();
            try {
                if (image.isEmpty()) {
                    if (!Objects.equals(Sinhvien.getGioiTinh(),Sinhvien1.getGioiTinh())) {
                        if(!Sinhvien1.isImage()) {
                            fos = new FileOutputStream(path + Sinhvien.getMaSinhVien() + ".jpg");
                            if (Sinhvien.getGioiTinh() == 1)
                                fis = new FileInputStream(path + "male.jpg");
                            else
                                fis = new FileInputStream(path + "female.jpg");
                            while (fis.read(data) > 0) {
                                fos.write(data);
                            }
                            fis.close();
                            fos.close();
                        }
                        Sinhvien1.setGioiTinh(Sinhvien.getGioiTinh());
                    }
                } else {
                    if (!Objects.equals(Sinhvien.getGioiTinh(),Sinhvien1.getGioiTinh()))
                        Sinhvien1.setGioiTinh(Sinhvien.getGioiTinh());
                    fos = new FileOutputStream(path + Sinhvien1.getMaSinhVien() + ".jpg");
                    fos.write(image.getBytes());
                    fos.close();
                    Sinhvien1.setImage(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!Objects.equals(Sinhvien.getAddress(), Sinhvien1.getAddress()))
                Sinhvien1.setAddress(Sinhvien.getAddress());
            if (!Objects.equals(Sinhvien.getCccd(), Sinhvien1.getCccd()))
                Sinhvien1.setCccd(Sinhvien.getCccd());
            if (!Objects.equals(Sinhvien.getNgaySinh(), Sinhvien1.getNgaySinh()))
                Sinhvien1.setNgaySinh(Sinhvien.getNgaySinh());
            if (!Objects.equals(Sinhvien.getEmail(), Sinhvien1.getEmail()))
                Sinhvien1.setEmail(Sinhvien.getEmail());
            if (!Objects.equals(Sinhvien.getMaLop(), Sinhvien1.getMaLop()))
                Sinhvien1.setMaLop(Sinhvien.getMaLop());
            if (!Objects.equals(Sinhvien.getPhone(), Sinhvien1.getPhone()))
                Sinhvien1.setPhone(Sinhvien.getPhone());
            if (!Objects.equals(Sinhvien.getTenSinhVien(), Sinhvien1.getMaSinhVien()))
                Sinhvien1.setTenSinhVien(Sinhvien.getTenSinhVien());
            check =true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public Boolean deleteAllBymaLop(Long maLop) {
        List<Sinhvien> Sinhviens;
        try {
            if (maLop != null)
                Sinhviens = SinhvienRepository.findAllBymaLop(maLop);
            else
                Sinhviens = SinhvienRepository.findAll();

            for (int i = 0; i < Sinhviens.size(); i++) {
                delete(Sinhviens.get(i));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteBymaSinhVien(Long id) {
        try {
            Sinhvien Sinhvien = SinhvienRepository.findById(id).get();
            delete(Sinhvien);
            dkMuonTraService.deleteAllBymaSinhvien(id);
            File file = new File(path + id + ".jpg");
            file.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Sinhvien> findAllBymaLop(Long maLop) {
        return SinhvienRepository.findAllBymaLop(maLop);
    }

    private void delete(Sinhvien Sinhvien) {
        try {
            SinhvienRepository.delete(Sinhvien);
            File file = new File(path + Sinhvien.getMaSinhVien() + ".jpg");
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
