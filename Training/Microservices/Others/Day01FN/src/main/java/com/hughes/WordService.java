package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WordService {

	@Value("${palindrome.base.url}")
	private String palindromeBaseUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String checkPalindrome(String word) {
		String url = palindromeBaseUrl + "/check/" + word;
		ResponseEntity<Boolean> response = 
				restTemplate.getForEntity(url, Boolean.class);
		boolean output = response.getBody();
		if(output) {
			return word + " is a palindrome";
		}
		return word + " is not a palindrome";	
		
	}
}
