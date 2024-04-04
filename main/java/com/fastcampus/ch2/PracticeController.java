package com.fastcampus.ch2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 웹 페이지 이동 연습 
 */


//@Controller
//@RequestMapping("/practice/*")
public class PracticeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showEnterance() {
		return "ch2/index";
	}
	
	// 인덱스 페이지
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showIndexPage() {
		return "ch2/index";
	}
	// 보드 페이지
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String showBoardPage() {
		return "ch2/board";
	}

	// 로그인 페이지
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "ch2/login";
	}
	
	// 나중에 처리하기, 현재 문제 게임 페이지에서 js가 연동안됨
	@RequestMapping(value = "/gameSite", method = RequestMethod.GET)
	public String showGameSite() {
		return "ch2/gameSite";
	}
	
	@RequestMapping(value = "/practice01", method = RequestMethod.GET)
	public String showPractice01() {
		return "ch2/practice01";
	}
	
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	
}
