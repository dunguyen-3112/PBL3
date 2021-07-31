package com.example.demo.controller;

import com.example.demo.model.Khoa;
import com.example.demo.service.KhoaService;
import com.example.demo.service.LopService;
import com.example.demo.tools.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "qltv/khoas")
public class KhoaController {

    private final KhoaService khoaService;

    private final LopService lopService;

    public KhoaController(KhoaService khoaService, LopService lopService) {
        this.khoaService = khoaService;
        this.lopService = lopService;
    }

    @GetMapping
    public String getAll(Model model,Long id)
    {
        model.addAttribute("khoas", khoaService.findAll());
        if(id != null)
        {
            model.addAttribute("lops",lopService.findAllBymaKhoa(id));
            return "Lop";
        }
        return "Khoa";
    }

    @PostMapping("/save")
    public String save(Khoa khoa,Model model) {
        if(!khoaService.add(khoa)){
            model.addAttribute("messages",new Message("Lưu Thất bại","khoas"));
            return "message";
        }
        return "redirect:/qltv/khoas";
    }

    @GetMapping("/update")
    @ResponseBody
    public Optional<Khoa> update(Long maKhoa)
    {
        return khoaService.findBymaKhoa(maKhoa);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.POST})
    public String update(Khoa khoa,Model model)
    {
        if(!khoaService.update(khoa)){
            model.addAttribute("messages",new Message("Chỉnh sửa Thất bại","khoas"));
            return "message";
        }
        return "redirect:/qltv/khoas";
    }

    @GetMapping("/delete")
    public String delete(Long maKhoa,Model model)
    {
        if(!khoaService.deleteBymaKhoa(maKhoa)){
            model.addAttribute("messages",new Message("Xóa Thất bại","khoas"));
            return "message";
        }
        return "redirect:/qltv/khoas";
    }

}