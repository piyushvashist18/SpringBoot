package com.hughes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WordController {

	@Autowired
	private WordService wordService;
	
	@PostMapping("/check")
	public String checkPalindrome(@RequestParam String word, HttpServletRequest request) {
		String message = wordService.checkPalindrome(word);
		request.setAttribute("message", message);
		return "index";
	}
	
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
}
