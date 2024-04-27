package com.fastcampus.web3.controller;

import com.fastcampus.web3.validator.UserValidator;
import com.fastcampus.web3.dto.UserDto;
import com.fastcampus.web3.service.UserService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService service;

    @InitBinder
    public void toDate(WebDataBinder binder) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
        binder.setValidator(new UserValidator());
    }

    @GetMapping("/add")
    public String add() {
        return "/web3/registerForm";
    }

    @PostMapping("/save")
    public String save(@Valid UserDto user, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                List<ObjectError> errors = result.getAllErrors();

                StringBuilder errorMsg = new StringBuilder();
                for (ObjectError e : errors) {
                    errorMsg.append(e.getDefaultMessage());
                }

                model.addAttribute("errorMsg", errorMsg.toString());
                return "/web3/registerForm";
            }
            service.register(user);
        } catch (Exception e) {
            model.addAttribute("errorMsg", "서버상의 버그가 발견되었습니다. 나중에 시도해주세요");
            return "/web3/registerForm";
        }

        return "/web3/loginForm";
    }
}
