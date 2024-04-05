package com.fastcampus.web3.controller;

import com.fastcampus.web3.dto.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
