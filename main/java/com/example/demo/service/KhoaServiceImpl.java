package com.example.demo.service;

import com.example.demo.model.Khoa;
import com.example.demo.model.Lop;
import com.example.demo.repository.KhoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class KhoaServiceImpl implements KhoaService{

    private final KhoaRepository khoaRepository;

    private final LopServiceImpl lopService;

    private final SinhVienServiceImpl sinhVienService;

    public KhoaServiceImpl(KhoaRepository khoaRepository, LopServiceImpl lopService, SinhVienServiceImpl sinhVienService) {
        this.khoaRepository = khoaRepository;
        this.lopService = lopService;
        this.sinhVienService = sinhVienService;
    }

    @Override
    public boolean add(Khoa Khoa) {
        boolean check = false;
        try{
            khoaRepository.save(Khoa);
            check = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean deleteBymaKhoa(Long maKhoa) {
        boolean check = false;
        try{
            List<Lop> lops =lopService.findAllBymaKhoa(maKhoa);
            for (Lop i:lops)
            {
                sinhVienService.deleteAllBymaLop(i.getMaLop());
            }
            lopService.deleteAll(lops);
            khoaRepository.delete(khoaRepository.findById(maKhoa).get());
            check = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return check;
    }

    @Override
    @Transactional
    public boolean update(Khoa Khoa) {
        boolean check = false;
        try {
            Khoa khoa1 = khoaRepository.findById(Khoa.getMaKhoa()).get();
            if (!Objects.equals(Khoa.getTenKhoa(), khoa1.getTenKhoa()))
                khoa1.setTenKhoa(Khoa.getTenKhoa());
            check = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Khoa> findAll() {
        try{
            return khoaRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Optional<Khoa> findBymaKhoa(Long maKhoa) {
        Optional<Khoa> khoa = null;
        try{
            khoa =  khoaRepository.findById(maKhoa);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return khoa;
    }
}
