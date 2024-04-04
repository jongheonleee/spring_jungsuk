package com.fastcampus.web2;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response, String toURL) {
        // 세션 확인
            // 존재, 보드 리스트 페이지
            // 없음, 로그인 폼 페이지
        if (isSession(request)) {
            return "/web2/board";
        }

        return "redirect:/login/login?toURL="+ request.getRequestURL();
    }

    private boolean isSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return "asdf".equals(session.getAttribute("id"));
    }

}
