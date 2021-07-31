package com.example.demo.controller;

import com.example.demo.model.Nhanvien;
import com.example.demo.service.ChucVuService;
import com.example.demo.service.NhanVienService;
import com.example.demo.tools.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;

@Controller
@RequestMapping(path = "qltv/nhanviens")
public class NhanVienController {

    private final NhanVienService NhanvienService;

    private final ChucVuService chucVuService;

    public NhanVienController(NhanVienService NhanvienService, ChucVuService chucVuService) {
        this.NhanvienService = NhanvienService;
        this.chucVuService = chucVuService;
    }


    @GetMapping("/{id}")
    public String getByChucVu(@PathVariable("id")Long machucvu, Model model)
    {
        model.addAttribute("nhanviens",NhanvienService.findAllByChucVu(machucvu));
        return "NhanVien";
    }

    @GetMapping
    public String getAll(Model model,String keyword)
    {
        model.addAttribute("chucvus",chucVuService.findAll());
        if(keyword == null)
            model.addAttribute("nhanviens",NhanvienService.findAll());
        else
            model.addAttribute("nhanviens",NhanvienService.getByKeyword(keyword));
        return "NhanVien";
    }

    @GetMapping("/chitiet")
    public Optional<Nhanvien> detail(Long id)
    {
        return NhanvienService.findBymaNhanvien(id);
    }

    @GetMapping("/delete")
    public String delete(Long maNV, Model mode) throws IOException {
        if(!NhanvienService.deleteBymaNhanvien(maNV))
        {
            mode.addAttribute("messages",new Message("Xóa Thất Bại !!!","nhanviens"));
            return "message";
        }
        return "redirect:/qltv/nhanviens";
    }



    @PostMapping("/update")
    public String update(Nhanvien Nhanvien, Model model, MultipartFile file) throws IOException {
        if(!NhanvienService.update(Nhanvien,file))
        {
            model.addAttribute("messages", new Message("Chỉnh sửa thất bại !!!", "nhanviens"));
            return "message";
        }
        return "redirect:/qltv/nhanviens";
    }

    @GetMapping("/update")
    @ResponseBody
    public Optional<Nhanvien> update(Long maNV)
    {
        return NhanvienService.findBymaNhanvien(maNV);

    }

    @PostMapping("/save")
    public String save(Nhanvien Nhanvien, Model model) throws IOException {

        NhanvienService.add(Nhanvien);
        return "redirect:/qltv/nhanviens";
    }
}
