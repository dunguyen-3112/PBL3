package com.example.demo.controller;

import com.example.demo.model.Sinhvien;
import com.example.demo.service.*;
import com.example.demo.tools.Message;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping(path = "qltv/sinhviens")
public class SinhVienController {

    // private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final SinhVienService sinhVienService;

    private final LopService lopService;

    private final DKMuonTraService dkMuonTraService;

    private final SachService sachService;

    private final NhanVienService nhanVienService;


    public SinhVienController(SinhVienService sinhVienService, LopService lopService, DKMuonTraService dkMuonTraService,
            SachService sachService, NhanVienService nhanVienService) {
        this.sinhVienService = sinhVienService;
        this.lopService = lopService;
        this.dkMuonTraService = dkMuonTraService;
        this.sachService = sachService;
        this.nhanVienService = nhanVienService;
    }

    @GetMapping
    public String SinhViens(Model model, String keyword,Long id)
    {
        model.addAttribute("lops",lopService.findAll());
        model.addAttribute("sinhviens",sinhVienService.getAll());
        model.addAttribute("sachs",sachService.findAll());
        model.addAttribute("nhanviens",nhanVienService.findAll());
        if(id != null) {
            model.addAttribute("dkmuontras",dkMuonTraService.findBymaSinhVien(id));
            return "DKMuonTra";
        }
        if (keyword == null)
            model.addAttribute("sinhviens", sinhVienService.getAll());
        else
            model.addAttribute("sinhviens", sinhVienService.getByKeyword(keyword));
        return "SinhVien";
    }

    @GetMapping("/chitiet")
    public String detail()
    {
        return "StudentDetail";
    }

    @PostMapping("/save")
    public String save(Sinhvien sinhVien, Model model)
    {
        if(!sinhVienService.add(sinhVien)) {
            model.addAttribute("messages", new Message("Lưu Thất Bại !!!", "sinhviens"));
            return "message";
        }
        return "redirect:/qltv/sinhviens";
    }

    @GetMapping("/delete")
    public String delete(Long id,Model model)
    {
        boolean m = sinhVienService.deleteBymaSinhVien(id);
        if(m) return "redirect:/qltv/sinhviens";
        else
            model.addAttribute("messages", new Message("Xóa Thất Bại !!!", "sinhviens"));
        return "message";

    }

    @GetMapping("/update")
    @ResponseBody
    public Optional<Sinhvien> update(Long id)
    {
        return sinhVienService.getBymaSinhvien(id);
    }

    @PostMapping("/update")
    public String update(Sinhvien sinhVien, Model model, MultipartFile file)
    {
        if(sinhVienService.update(sinhVien,file))
            return "redirect:/qltv/sinhviens";
        else
            model.addAttribute("messages",new Message("Update Thất Bại!!!","sinhviens"));
        return "message";
    }

}
