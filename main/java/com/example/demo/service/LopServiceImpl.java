package com.example.demo.service;

import com.example.demo.model.Lop;
import com.example.demo.repository.LopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LopServiceImpl implements LopService{

    private final LopRepository lopRepository;

    private final SinhVienServiceImpl sinhVienService;


    public LopServiceImpl(LopRepository lopRepository, SinhVienServiceImpl sinhVienService) {
        this.lopRepository = lopRepository;
        this.sinhVienService = sinhVienService;
    }

    @Override
    @Transactional
    public boolean add(Lop Lop) {
        boolean check = false;
        try{
            lopRepository.save(Lop);
            // Khoa khoa = khoaService.findBymaKhoa(Lop.getMaKhoa()).get();
            // khoa.setSoLuong(khoa.getSoLuong() + 1);
            check = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return check;
    }

    @Override
    @Transactional
    public boolean deleteBymaLop(Long maLop) {
        boolean check = false;
        try{
            Lop Lop = lopRepository.findById(maLop).get();
            lopRepository.delete(Lop);
            check = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return check;
    }

    @Override
    @Transactional
    public boolean update(Lop Lop) {
        boolean check = false;
        try {
            Lop Lop1 = lopRepository.findById(Lop.getMaLop()).get();
            if (Lop.getTenLop() != null &&
                    Lop.getTenLop().length() > 0 &&
                    !Objects.equals(Lop.getTenLop(), Lop1.getTenLop()))
                Lop1.setTenLop(Lop.getTenLop());
            if(!Objects.equals(Lop.getMaKhoa(),Lop1.getMaKhoa()))
                Lop1.setMaKhoa(Lop.getMaKhoa());

            if(!Objects.equals(Lop.getSoLuong(),Lop1.getSoLuong()))
            {
                
            }
            check = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Lop> findAll() {
        return lopRepository.findAll();
    }

    @Override
    public Optional<Lop> findBymaLop(Long maLop) {
        return lopRepository.findById(maLop);
    }

    @Override
    public List<Lop> findAllByKeyword(String keyword) {
        return lopRepository.findByKeyword(keyword);
    }

    @Override
    public List<Lop> findAllBymaKhoa(Long maKhoa) {
        return lopRepository.findAllBymaKhoa(maKhoa);
    }

    @Override
    public boolean deleteAll(List<Lop> lops) {

        try {
            for (Lop i:lops)
                sinhVienService.deleteAllBymaLop(i.getMaLop());
            lopRepository.deleteAll(lops);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
}
