package com.example.demo.controller;

import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserSRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.model.*;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(path = { "/" })
@CrossOrigin(origins = "http://14.175.159.10:8080")
public class HomeController {

    private final UserSRepository repou;

    private final MessageRepository repom;

    private final RoleRepository reporole;

    static Logger log = LoggerFactory.getLogger(HomeController.class.getName());

    @GetMapping("qltv/0")
    public String notHome() {
        return "Home";
    }

    @GetMapping(path={"qltv/1"})
    public String Home(Model model) {
        Object user =
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       UserS user1 = repou.findByEmailNumber(((UserDetails)user).getUsername());
        model.addAttribute("avatar","http://14.175.159.10:8080/nv/"+user1.getId()+".jpg");
        model.addAttribute("IDU",user1.getId());
        model.addAttribute("noti",repom.findAllMessByCheck(false));
        model.addAttribute("user", user1);
        model.addAttribute("role",reporole.findById(user1.getIdRole()).get().getName());
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    // @GetMapping("403")
    // public String accessDenied() {
    // return "403";
    // }
}
