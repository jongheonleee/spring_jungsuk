package com.fastcampus.web2;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("/login/*")
public class LoginController {

    @Autowired
    ApplicationContext ac;

    @GetMapping("/login")
    public String login(String toURL, Model model) {
        // 로그인 폼으로 이동
        model.addAttribute("toURL", toURL);
        return "/web2/loginForm";
    }

    @PostMapping("/login")
    public String checkLogin(HttpServletRequest request, HttpServletResponse response, String id, String pwd, String toURL, boolean remember) {
        // 아이디, 비번 확인
            // 틀림, 로그인폼 재요청
            // 맞음, 넘어감

        // 세션 생성

        // 쿠키 확인
            // 체크, 쿠키 생성
            // 체크안됨, 쿠키 삭제

        // 이전 페이지
            // 있으면, 거기로 이동
            // 없으면, 홈 페이지

        if (!isValid(id, pwd)) {
            return "redirect:/login/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", id);

        if (remember) {
            createCookie(response, id);
        } else {
            deleteCookie(response, id);
        }

        toURL = toURL == null || toURL.equals("") ? "/" : toURL;
        return "redirect:" + toURL;
    }

    private void createCookie(HttpServletResponse response, String id) {
        Cookie cookie = new Cookie("id", id);
        response.addCookie(cookie);
    }

    private void deleteCookie(HttpServletResponse response, String id) {
        Cookie cookie = new Cookie("id", id);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    private boolean isValid(String id, String pwd) {
        return "asdf".equals(id) && "1234".equals(pwd);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 세션 삭제
        // 홈 페이지로 이동
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
