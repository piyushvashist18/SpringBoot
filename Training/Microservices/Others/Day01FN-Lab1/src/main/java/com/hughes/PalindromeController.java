package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PalindromeController {
	
	@Autowired
	PalindromeLogic pl;
	
	@GetMapping("/palindrome/check/{word}")
	public boolean isPalindrome(@PathVariable String word) {
		return pl.check(word);
	}
	
}
