package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class PalindromeController {

	@Autowired
	private PalindromeLogic palindromeLogic;
	
	@GetMapping("/palindrome/{word}")
	public boolean isPalindrome(@PathVariable String word) {
		return palindromeLogic.check(word);
	}
}
