package com.hughes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WordController {

	@Autowired
	private Environment env;
	
	@Autowired
	private WordService wordService;
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello Monday from port: " + env.getProperty("local.server.port");
	}
	
	@GetMapping("/")
	public String process() {
		return "output";
	}
	
	@PostMapping("/check")
	public String checkPalindrome(@RequestParam String word, HttpServletRequest request) {
		String message = wordService.checkPalindrome(word);
		request.setAttribute("message", message);
		return "output";
	}
	
}
