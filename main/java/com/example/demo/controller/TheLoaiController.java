package com.example.demo.controller;

import com.example.demo.model.Theloai;
import com.example.demo.service.SachService;
import com.example.demo.service.TheLoaiService;
import com.example.demo.tools.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "qltv/theloais")
public class TheLoaiController {

    private final TheLoaiService theLoaiService;

    private final SachService sachService;

    public TheLoaiController(TheLoaiService theLoaiService, SachService sachService) {
        this.theLoaiService = theLoaiService;
        this.sachService = sachService;
    }

    @GetMapping
    public String getAll(Model model,Long id){
        model.addAttribute("theloais",theLoaiService.findAll());
        if(id != null)
        {
            model.addAttribute("sachs",sachService.findAllBymaTheLoai(id));
            return "Sach";
        }
        return "TheLoai";
    }

    @PostMapping("/save")
    public String save(Theloai theLoai, Model model) {
        if(!theLoaiService.add(theLoai))
        {
            model.addAttribute("messages",new Message("Lưu thất bại!!!","theloais"));
            return "message";
        }
        return "redirect:/qltv/theloais";
    }

    @GetMapping("/delete")
    public String delete(Long id,Model model) {
        if(!theLoaiService.deleteBymaTheloai(id))
        {
            model.addAttribute("messages",new Message("Xóa thất bại!!!","theloais"));
            return "message";
        }
        return "redirect:/qltv/theloais";
    }

    @GetMapping("/update")
    @ResponseBody
    public Optional<Theloai> update(Long id)
    {
        return theLoaiService.findBymaTheloai(id);
    }

    @PostMapping("/update")
    public String Update(Theloai theloai, Model model)
    {
        if(!theLoaiService.update(theloai))
        {
            model.addAttribute("messages",new Message("Chỉnh sửa thất bại!!!","theloais"));
            return "message";
        }
        return "redirect:/qltv/theloais";
    }
}