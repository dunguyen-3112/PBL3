package com.example.demo.controller;

import com.example.demo.service.NhanVienService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    private final NhanVienService nhanVienService;

    public HomeController(NhanVienService nhanVienService) {
        this.nhanVienService = nhanVienService;
    }

    @GetMapping
    public String Home(Model model)
    {
        Object user =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = "";

        if(user instanceof UserDetails)
        {
            name = "Xin chào "+nhanVienService.findByemail(((UserDetails)user).getUsername()).getTenNV();
        }
        model.addAttribute("name",name);
        return "Home";
    }

    @GetMapping("login")
    public String login()
    {
        return "login";
    }
}
