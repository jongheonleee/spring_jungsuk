package com.fastcampus.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/web2/loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String dealWithLoginForm(HttpServletRequest request, HttpServletResponse response, String id, String pwd, String remember, String from,  Model model) {
        if (!isValidUser(id, pwd)) {
            return "/web2/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", id);

        if (isCheckedCookie(remember)) {
            createCookie(response, id);
        } else {
            deleteCookie(response);
        }

        model.addAttribute("id", id);
        return "/web2/board";
    }

    private boolean isValidUser(String id, String pwd) {
        return "asdf".equals(id) && "1234".equals(pwd);
    }

    private boolean isCheckedCookie(String clicked) {
        return "on".equals(clicked);
    }


    private void createCookie(HttpServletResponse response, String id) {
        Cookie cookie = new Cookie("id", id);
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
    }

    private void deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("id", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "/web2/loginForm";
    }
}
