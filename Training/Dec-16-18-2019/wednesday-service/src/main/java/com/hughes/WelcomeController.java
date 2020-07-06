package com.hughes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class WelcomeController {
	Logger logger = LoggerFactory.getLogger(WelcomeController.class);
			
	@Autowired
	private WelcomeService welcomeService;
	
	@GetMapping("/bye")
	@HystrixCommand
	public String bye() {
		return "Bye Bye";
	}
	
	@GetMapping("/")
	@HystrixCommand
	public String getWelcome() {
		logger.info("*****getWelcome*****");
		return welcomeService.getMessage();
	}
}





