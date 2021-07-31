package com.example.demo.controller;

import com.example.demo.model.Dkmuontra;
import com.example.demo.service.DKMuonTraService;
import com.example.demo.tools.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@Controller
@RequestMapping(path = "qltv/dkmuontras")
public class DKMuonTraController {

    private final DKMuonTraService dkMuonTraService;

    public DKMuonTraController(DKMuonTraService dkMuonTraService) {
        this.dkMuonTraService = dkMuonTraService;
    }

    @GetMapping
    public String getAll(Model model,String name)
    {
        if(name != null)
            model.addAttribute("dkmuontras",dkMuonTraService.findByKeyword(name));
        else
            model.addAttribute("dkmuontras",dkMuonTraService.findAll());
        return "DKMuonTra";
    }

    @PostMapping("/save")
    public String save(Model model, Dkmuontra dkmuontra)
    {
        if(dkMuonTraService.add(dkmuontra))
            return "redirect:/qltv/dkmuontras";
        else {
            model.addAttribute("messages",new Message("Lưu Thất Bại!!!","dkmuontras"));
            return "message";
        }
    }

    @GetMapping("/update")
    @ResponseBody
    public Optional<Dkmuontra> update(Long id)
    {
        return dkMuonTraService.findBymadk(id);
    }

    @PostMapping("/update")
    public String update(Dkmuontra dkmuontra,Model model)
    {
        if(!dkMuonTraService.update(dkmuontra))
        {
            model.addAttribute("messages",new Message("Chỉnh Sửa Thất Bại!!!","dkmuontras"));
            return "message";
        }else
        {
            return "redirect:/qltv/dkmuontras";
        }
    }

    @GetMapping("/delete")
    public String delete(Long id,Model model)
    {
        if(!dkMuonTraService.deleteBymaDkmuontra(id))
        {
            model.addAttribute("messages",new Message("Xóa Thất Bại!!!","dkmuontras"));
            return "message";
        }
        else
        {
            return "redirect:/qltv/dkmuontras";
        }

    }
}
