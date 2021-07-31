package com.example.demo.service;

import com.example.demo.model.Sach;
import com.example.demo.repository.SachRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SachServiceImpl implements SachService {

    private final SachRepository sachRepository;

    private final DKMuonTraService dkmService;

    private static String path = "D:\\images\\s\\";

    public SachServiceImpl(SachRepository sachRepository, DKMuonTraService dkmService) {
        this.sachRepository = sachRepository;

        this.dkmService = dkmService;
    }

    @Override
    @Transactional
    public Boolean add(Sach Sach, MultipartFile file) {
        Sach.setSoLuongNow(Sach.getSoLuongBegin());
        FileOutputStream fos = null;
        FileInputStream fis = null;
        byte[] data = new byte[1024];
        boolean check = false;
        Sach Sach1 = null;
        try{
            if(Sach.getNgayxb().before(new Date())) {
                Sach1 = sachRepository.save(Sach);
                if (Sach != null) {
                    if (file.isEmpty()) {
                        fis = new FileInputStream(path + "s.jpg");
                        fos = new FileOutputStream(path + Sach.getMaSach() + ".jpg");
                        while (fis.read(data) > 0) {
                            fos.write(data);
                        }
                        fis.close();
                        fos.close();
                    } else {
                        fos = new FileOutputStream(path + Sach.getMaSach() + ".jpg");
                        fos.write(file.getBytes());
                        fos.close();
                        Sach1.setImage(true);
                    }
                }
                check = true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public Boolean deleteBymaSach(Long maSach) {
        boolean check = false;
        File file = null;
        try{
            sachRepository.delete(sachRepository.findById(maSach).get());
            file = new File(path+maSach+".jpg");
            file.delete();
            dkmService.deleteAllBymaSach(maSach);
            check = true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    @Transactional
    public Boolean update(Sach Sach,MultipartFile image) {
        boolean check = false;
        try{
            Sach sach = sachRepository.findById(Sach.getMaSach()).get();
            if(sach != null)
            {
                if(!Objects.equals(sach.getTenSach() ,Sach.getTenSach()))
                    sach.setTenSach(Sach.getTenSach());
                if(!Objects.equals(sach.getGia() ,Sach.getGia()))
                    sach.setGia(Sach.getGia());
                if(!Objects.equals(sach.getTacGia() ,Sach.getTacGia()))
                    sach.setTacGia(Sach.getTacGia());
                if(!Objects.equals(sach.getMaTheLoai() ,Sach.getMaTheLoai()))
                    sach.setMaTheLoai(Sach.getMaTheLoai());
                if(!Objects.equals(sach.getNhaXuatBan() ,Sach.getNhaXuatBan()))
                    sach.setNhaXuatBan(Sach.getNhaXuatBan());
                if(!Objects.equals(sach.getNgayxb() ,Sach.getNgayxb()))
                    sach.setNgayxb(Sach.getNgayxb());
                if(!Objects.equals(sach.getSoLuongBegin() ,Sach.getSoLuongBegin())) {
                    sach.setSoLuongNow(Sach.getSoLuongBegin() - sach.getSoLuongBegin() + sach.getSoLuongNow() );
                    sach.setSoLuongBegin(Sach.getSoLuongBegin());
                }
                if(!Objects.equals(sach.getSotrang() ,Sach.getSotrang()))
                    sach.setSotrang(Sach.getSotrang());
                if(!image.isEmpty())
                {
                    FileOutputStream fos = new FileOutputStream(path+ sach.getMaSach() + ".jpg");
                    fos.write(image.getBytes());
                    fos.close();
                    if(!sach.isImage())
                        sach.setImage(true);
                }
            }
            check = true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Sach> findAll() {
        return sachRepository.findAll();
    }

    @Override
    public Optional<Sach> findBymaSach(Long maSach) {
        return sachRepository.findById(maSach);
    }

    @Override
    public List<Sach> findAllByKeyword(String keyword) {
        return sachRepository.findByKeyword(keyword);
    }

    @Override
    public List<Sach> findAllBymaTheLoai(Long maTheLoai) {
        return sachRepository.findAllBymaTheLoai(maTheLoai);
    }
}
