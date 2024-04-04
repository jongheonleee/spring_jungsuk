package com.fastcampus.web;


import com.fastcampus.ch2.HomeController;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "/web2/index";
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(String from, Model model) {
        System.out.println(from);
        model.addAttribute("from", from);
        return "/web2/index";
    }

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public String board(HttpSession session, Model model) {
        if ("asdf".equals(session.getAttribute("id"))) {
            return "/web2/board";
        }

        return "/web2/loginForm";
    }

}
