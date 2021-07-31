package com.example.demo.controller;

import com.example.demo.model.Lop;
import com.example.demo.service.KhoaService;
import com.example.demo.service.LopService;
import com.example.demo.service.SinhVienService;
import com.example.demo.tools.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "qltv/lops")
public class LopController {

    private final KhoaService khoaService;

    private final LopService lopService;

    private final SinhVienService sinhVienService;

    public LopController(KhoaService khoaService, LopService lopService, SinhVienService sinhVienService) {
        this.khoaService = khoaService;
        this.lopService = lopService;
        this.sinhVienService = sinhVienService;
    }

    @GetMapping
    public String getAll(Model model,String keyword,Long id)
    {
        model.addAttribute("khoas",khoaService.findAll());

        if(id != null)
        {
            model.addAttribute("lops", lopService.findAll());
            model.addAttribute("sinhviens",sinhVienService.findAllBymaLop(id));
            return "SinhVien";
        }
        if(keyword != null)
            model.addAttribute("lops",lopService.findAllByKeyword(keyword));
        else
            model.addAttribute("lops", lopService.findAll());
        return "Lop";
    }

    @PostMapping("/save")
    public String save(Lop lop, Model model) {
        if(!lopService.add(lop))
        {
            model.addAttribute("messages",new Message("Lưu thất bại!!!","lops"));
            return "message";
        }
        return "redirect:/qltv/lops";
    }

    @GetMapping("/delete")
    public String delete(Long maLop,Model model) {
        if(!lopService.deleteBymaLop(maLop))
        {
            model.addAttribute("messages",new Message("Lưu thất bại!!!","lops"));
            return "message";
        }
        return "redirect:/qltv/lops";
    }

    @RequestMapping(value = "/update" ,method = RequestMethod.GET)
    @ResponseBody
    public Optional<Lop> update(Long maLop)
    {
        return lopService.findBymaLop(maLop);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String Update(Lop lop,Model model)
    {
        if(!lopService.update(lop))
        {
            model.addAttribute("messages",new Message("Lưu thất bại!!!","lops"));
            return "message";
        }
        return "redirect:/qltv/lops";
    }
}
