package com.fastcampus.web3.controller;


import com.fastcampus.web3.dto.UserDTO;
import com.fastcampus.web3.service.UserService;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public String login(String toURL, Model model) {
        model.addAttribute("toURL", toURL);
        return "/web3/loginForm";
    }

    @PostMapping("/login")
    public String checkLogin(HttpServletRequest request, HttpServletResponse response, String id, String pwd, String toURL, boolean remember) throws Exception {
        if (!isValid(id, pwd)) {
            return "redirect:/login/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", id);

        if (remember) {
            Cookie cookie = new Cookie("id", id);
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("id", id);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        toURL = toURL == null || toURL.equals("") ? "/" : toURL;
        return "redirect:" + toURL;
    }


    private boolean isValid(String id, String pwd) throws Exception {
        List<UserDTO> users = service.findAll();
        return users.stream()
                .anyMatch(user -> user.getId().equals(id) && user.getPwd().equals(pwd));
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
