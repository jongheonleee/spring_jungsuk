package com.fastcampus.ch2;

import java.text.DateFormat;

import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */

// 기본 생성자 호출해서 껍질 생성
// 세터로 필드 채움

//@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/yoil", method = RequestMethod.GET)
	public String yoil(MyDate myDate, Model model) {
		
		return "ch2/yoil";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "/ch2/home";
	}

//	@RequestMapping(value = "/board", method=RequestMethod.GET)
	public String getBoard() {
		return "board0/board";
	}

//	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public String getIndex() {
		return "board0/index";
	}
	
}
