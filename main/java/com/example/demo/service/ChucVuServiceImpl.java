package com.example.demo.service;

import com.example.demo.model.Chucvu;
import com.example.demo.repository.ChucvuRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChucVuServiceImpl implements ChucVuService{

    Logger log = LoggerFactory.getLogger(System.class);

    private final ChucvuRepository chucvuRepository;

    public ChucVuServiceImpl(ChucvuRepository chucvuRepository) {
        this.chucvuRepository = chucvuRepository;
    }

    @Override
    public String add(Chucvu chucVu) {
        try{
            chucvuRepository.save(chucVu);
            return "OK";
        }catch (Exception e){
            return "Lưu thất bại !!!\n"+"Vui lòng kiểm tra lại thông tin !!!";
        }
    }

    @Override
    public String deleteBymaChucvu(Long maChucVu) {
        try{
            log.info("ID::"+maChucVu);
            chucvuRepository.deleteById(maChucVu);
            return "OK";
        }catch (Exception e){
            return "Xóa thất bại !!!\n"+"Vui lòng kiểm tra lại thông tin !!!";
        }
    }

    @Override
    @Transactional
    public String update(Chucvu chucVu) {
        Chucvu chucVu1 = new Chucvu();
        try {
            chucVu1 = chucvuRepository.findById(chucVu.getMaChucVu()).get();
            if (chucVu.getTenChucVu() != null &&
                    chucVu.getTenChucVu().length() > 0 &&
                    !Objects.equals(chucVu.getTenChucVu(), chucVu1.getTenChucVu()))
            {
                chucVu1.setTenChucVu(chucVu.getTenChucVu());
                return "OK";
            }
            else
                return "!!!Chỉnh sửa thất bại !!!\n"+"Vui lòng kiểm tra lại thông tin !!!";
        }catch (Exception e){
            throw new IllegalStateException("!!!Chỉnh sửa thất bại !!!\n"+"Vui lòng kiểm tra lại thông tin !!!");
        }
    }

    @Override
    public List<Chucvu> findAll() {
        return chucvuRepository.findAll();
    }

    @Override
    public Optional<Chucvu> findBymaChucvu(Long maChucVu) {
        return chucvuRepository.findById(maChucVu);
    }
}
