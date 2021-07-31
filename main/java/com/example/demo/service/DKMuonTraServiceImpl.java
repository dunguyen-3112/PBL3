package com.example.demo.service;

import com.example.demo.model.Dkmuontra;
import com.example.demo.model.Nhanvien;
import com.example.demo.model.Sach;
import com.example.demo.model.Sinhvien;
import com.example.demo.repository.DkmuontraRepository;
import com.example.demo.repository.NhanvienRepository;
import com.example.demo.repository.SachRepository;
import com.example.demo.repository.SinhvienRepository;
import org.apache.tomcat.jni.Time;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Dates;

import java.time.LocalDate;
import java.util.*;

@Service
public class DKMuonTraServiceImpl implements DKMuonTraService{

    private final DkmuontraRepository dkmuontraRepository;

    private final SachRepository sachRepository;

    private final SinhvienRepository sinhvienRepository;

    private final NhanvienRepository nhanvienRepository;

    public DKMuonTraServiceImpl(DkmuontraRepository dkmuontraRepository,
                                SachRepository sachRepository,
                                SinhvienRepository sinhvienRepository,
                                NhanvienRepository nhanvienRepository) {
        this.dkmuontraRepository = dkmuontraRepository;
        this.sachRepository = sachRepository;
        this.sinhvienRepository = sinhvienRepository;
        this.nhanvienRepository = nhanvienRepository;
    }

    @Override
    @Transactional
    public boolean add(Dkmuontra Dkmuontra) {
        Date date = new Date();
        Dkmuontra.setNgayMuon(new java.sql.Date(date.getYear(), date.getMonth(), date.getDate()));
        boolean check = false;
        try{
            Sach sach = sachRepository.findById(Dkmuontra.getMaSach()).get();
            Sinhvien sinhvien = sinhvienRepository.findById(Dkmuontra.getMaSinhVien()).get();
            Nhanvien nhanvien = nhanvienRepository.findById(Dkmuontra.getManv()).get();
            if((sach != null) && (sinhvien != null) &&(nhanvien != null) &&
                    sachRepository.findById(Dkmuontra.getMaSach()).get().isTT()
                    && (Dkmuontra.getNgayTra().after(Dkmuontra.getNgayMuon()))) {
                sach.setSoLuongNow(sach.getSoLuongNow() - 1);
                dkmuontraRepository.save(Dkmuontra);
                sachRepository.findById(Dkmuontra.getMaSach()).get().setTT(false);
                check = true;
            }
            else
                check = false;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    @Transactional
    public boolean deleteBymaDkmuontra(Long maDkmuontra) {
        Nhanvien nhanvien =  nhanvienRepository.findByemail(((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername());
        boolean check = false;
        try{
            Dkmuontra dkmuontra = dkmuontraRepository.findById(maDkmuontra).get();
            System.out.println("\n"+nhanvien.getMaNV() +"--------"+ dkmuontra.getManv()+"\n");
            if(nhanvien.getMaChucVu() == 2L || (Objects.equals(nhanvien.getMaNV(),dkmuontra.getManv())))
            {
                dkmuontraRepository.deleteById(maDkmuontra);
                Sach sach = sachRepository.findById(dkmuontra.getMaSach()).get();
                sach.setSoLuongNow(sach.getSoLuongNow() + 1);
                if(!sach.isTT()) sach.setTT(true);
                check = true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    @Transactional
    public boolean update(Dkmuontra dkmuontra) {
        Date date = new Date();
        boolean check = false;
        try{
            Dkmuontra dkmuontra1 = dkmuontraRepository.findById(dkmuontra.getMadk()).get();
            Sach sach = sachRepository.findById(dkmuontra.getMaSach()).get();
            Sinhvien sinhvien = sinhvienRepository.findById(dkmuontra.getMaSinhVien()).get();
            Nhanvien nhanvien = nhanvienRepository.findById(dkmuontra.getManv()).get();
            if((sach != null) && (sinhvien != null) &&(nhanvien != null) && (dkmuontra1 != null)) {
                if (!Objects.equals(dkmuontra1.getNgayTra(), dkmuontra.getNgayTra())
                        && dkmuontra.getNgayTra().after(dkmuontra.getNgayMuon())
                        && dkmuontra.getNgayTra().after(new java.sql.Date(date.getYear(), date.getMonth(), date.getDate()))) {
                    dkmuontra1.setNgayTra(dkmuontra.getNgayTra());
                }
                if (!Objects.equals(dkmuontra.getTrangThai(), dkmuontra1.getTrangThai()) &&((sach.isTT() &&
                        (dkmuontra.getTrangThai() == 1)) ||((!sach.isTT() && (dkmuontra.getTrangThai() == 0))))) {
                    dkmuontra1.setTrangThai(dkmuontra.getTrangThai());
                    if (dkmuontra.getTrangThai() == 1) {
                        sach.setSoLuongNow(sach.getSoLuongNow() - 1);
                        sach.setTT(false);
                    }
                    else {
                        sach.setSoLuongNow(sach.getSoLuongNow() + 1);
                        sach.setTT(true);
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
    public List<Dkmuontra> findAll() {
        return dkmuontraRepository.findAll();
    }

    @Override
    public List<Dkmuontra> findAllBymaSach(Long maSach) {
        return dkmuontraRepository.findAllBymaSach(maSach);
    }

    @Override
    public List<Dkmuontra> findBymaSinhVien(Long id) {
        return dkmuontraRepository.findAllBymaSinhVien(id);
    }

    @Override
    public Optional<Dkmuontra> findBymadk(Long id) {
        return dkmuontraRepository.findById(id);
    }

    @Override
    public List<Dkmuontra> findByKeyword(String keyword) {
        List<Sach> saches = sachRepository.findByKeyword(keyword);
        List<Dkmuontra> dkmuontras = new ArrayList<>();
        for (Sach i:saches)
            dkmuontras.addAll(dkmuontraRepository.findAllBymaSach(i.getMaSach()));
        return dkmuontras;
    }

    @Override
    public boolean deleteAllBymaSach(Long maSach) {
        try {
            Iterator<Dkmuontra> dks = findAll().iterator();
            while(dks.hasNext()){
                Dkmuontra dk = dks.next();
                if(Objects.equals(dk.getMaSach(),maSach))
                    dkmuontraRepository.delete(dk);
            }

        }catch(Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAllBymaSinhvien(Long maSinhvien) {
        try {
            Iterator<Dkmuontra> dks = findAll().iterator();
            while(dks.hasNext())
            {
                Dkmuontra dk = dks.next();
                if(Objects.equals(dk.getMaSinhVien(),maSinhvien))
                    dkmuontraRepository.delete(dk);
            }
        }catch(Exception e)
        {
            return false;
        }
        return true;
    }
}
