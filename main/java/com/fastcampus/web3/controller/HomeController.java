package com.fastcampus.web3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "/web3/index";
    }

    @GetMapping("/test2")
    public String test2() {
        return "/web3/test2";
    }

}
