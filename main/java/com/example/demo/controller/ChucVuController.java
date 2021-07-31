package com.example.demo.controller;

import com.example.demo.model.Chucvu;
import com.example.demo.service.ChucVuService;
import com.example.demo.service.NhanVienService;
import com.example.demo.tools.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "qltv/chucvus")
public class ChucVuController {

    private final ChucVuService chucVuService;

    private final NhanVienService nhanVienService;

    public ChucVuController(ChucVuService chucVuService, NhanVienService nhanVienService) {
        this.chucVuService = chucVuService;
        this.nhanVienService = nhanVienService;
    }

    @GetMapping
    public String getAll(Model model,Long id) {
        model.addAttribute("chucvus", chucVuService.findAll());
        if(id != null)
        {
            model.addAttribute("nhanviens", nhanVienService.findAllByChucVu(id));
            return "NhanVien";
        }
        return "ChucVu";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Chucvu chucVu) {
        chucVuService.add(chucVu);
        return "redirect:/qltv/chucvus";
    }


    @PostMapping("/update")
    public String updateChucVu(@ModelAttribute("chucvu") Chucvu chucVu, Model model) {

        String m = chucVuService.update(chucVu);
        if (m != "OK") {
            model.addAttribute("messages", new Message(m, "chucvus"));
            return "message";
        }
        return "redirect:/qltv/chucvus";
    }

    @GetMapping("/update")
    @ResponseBody
    public Optional<Chucvu> UpdateChucVu(Long maChucVu) {
        return chucVuService.findBymaChucvu(maChucVu);
    }

    @GetMapping("/delete")
    public String deleteChucVu(Long maChucVu, Model model) {
        String message = chucVuService.deleteBymaChucvu(maChucVu);
        if (message != "OK") {
            model.addAttribute("messages", new Message(message, "chucvus"));
            return "message";
        }
        return "redirect:/qltv/chucvus";
    }
}