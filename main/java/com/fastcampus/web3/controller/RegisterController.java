package com.fastcampus.web3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @GetMapping("/add")
    public String add() {

        return "/web3/registerForm";
    }

    @PostMapping("/save")
    public String save() {
        return "redirect:/";
    }
}
