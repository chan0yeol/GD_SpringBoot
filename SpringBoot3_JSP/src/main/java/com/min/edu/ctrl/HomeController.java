package com.min.edu.ctrl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String home() throws UnsupportedEncodingException {
		log.info("HomeController 처음 호출 요청");
		String name = URLEncoder.encode("한글","UTF-8");
		return "redirect:/info.do?name="+name+"&age=100";
	}
	
	@GetMapping("/info.do")
	public String info(String name, int age, Model model) {
		log.info("info 메소드 전달 값 : {}, {}", name, age);
		model.addAttribute("infoName",name);
		model.addAttribute("infoAge",age);
		
		return "info";
	}
}
