package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/status/{accountNumber}")
	public String getStatus(@PathVariable int accountNumber) {
		String url = "http://localhost:7003/account?accountNumber=" + accountNumber;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response.getBody();
	}
	
	@PutMapping
	public String operate(@RequestParam int accountNumber, @RequestParam String operation, @RequestParam int amount) {
		String url = "http://localhost:7003/account?accountNumber=" + accountNumber + "&amount=" + amount + "&operation=" + operation + "&type=Salary";
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
		return response.getBody();
	}
	
}
