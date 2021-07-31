package com.example.demo.controller;

import com.example.demo.model.Sach;
import com.example.demo.service.DKMuonTraService;
import com.example.demo.service.SachService;
import com.example.demo.service.TheLoaiService;
import com.example.demo.tools.Message;
// import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping(path = "qltv/sachs")
public class SachController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final SachService sachService;

    private final TheLoaiService theLoaiService;

    private final DKMuonTraService dkMuonTraService;

    public SachController(SachService sachService, TheLoaiService theLoaiService, DKMuonTraService dkMuonTraService) {
        this.sachService = sachService;
        this.theLoaiService = theLoaiService;
        this.dkMuonTraService = dkMuonTraService;
    }

    @GetMapping("/update")
    @ResponseBody
    public Optional<Sach> update(Long id)
    {
        log.info("id::"+id);
        return sachService.findBymaSach(id);
    }

    @GetMapping
    public String getAll(Model model, String keyword,Long id)
    {
        model.addAttribute("theloais",theLoaiService.findAll());
        if(id != null)
        {
            model.addAttribute("dkmuontras",dkMuonTraService.findAllBymaSach(id));
            return "DKMuonTra";
        }
        if(keyword == null)
            model.addAttribute("sachs", sachService.findAll());
        else
            model.addAttribute("sachs", sachService.findAllByKeyword(keyword));
        return "Sach";
    }

    @PostMapping("/save")
    public String save(Sach Sach, MultipartFile file, Model model)
    {
        if(!sachService.add(Sach,file))
        {
            model.addAttribute("messages",new Message("Lưu Thất Bại!!!","sachs"));
            return "message";
        }
        return "redirect:/qltv/sachs";
    }

    @GetMapping("/chitiet")
    public String detail()
    {
        return "BookDetail";
    }

    @GetMapping("/delete")
    public String delete(Long id)
    {
        sachService.deleteBymaSach(id);
        return "redirect:/qltv/sachs";
    }

    @PostMapping("/update")
    public String update(Sach sach, Model model,MultipartFile file)
    {
        if(!sachService.update(sach,file))
        {
            model.addAttribute("messages", new Message("Chỉnh sửa thất bại !!!", "sachs"));
            return "message";
        }
        return "redirect:/qltv/sachs";
    }

}
