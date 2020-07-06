package com.hughes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleForCfgController {

	@Value("${welcome.message}")
	private String message;
	
	@GetMapping("/welcome")
	public String getWelcomeMessage() {
		return message;
	}
}
