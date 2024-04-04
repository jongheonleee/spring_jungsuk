package com.fastcampus.web2;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    ApplicationContext ac; // 서블릿 ac가 들어옴

    @RequestMapping("/")
    public String index() {
        // checkInnerAC();
        // 홈 페이지로 바로 이동
        return "/web2/index";
    }

    private void checkInnerAC() {
        System.out.println("ac = " + ac);
        System.out.println(ac.getBeanDefinitionCount());
        for (String name : ac.getBeanDefinitionNames()) {
            System.out.print(name + ", ");
        }
        System.out.println();

        ApplicationContext rootAc = ac.getParent();
        System.out.println("rootAc = " + rootAc);
        System.out.println(rootAc.getBeanDefinitionCount());
        for (String name : rootAc.getBeanDefinitionNames()) {
            System.out.print(name + ", ");
        }

        System.out.println();
    }
}
