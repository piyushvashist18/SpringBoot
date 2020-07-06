package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/math")
public class MathController {

	@Autowired
	private SquareService squareService;
	
	@GetMapping("/square/{num}")
	@HystrixCommand(fallbackMethod = "getResultFromSquareServiceBkup")
	public String getResultFromSquareService(@PathVariable int num) {
		System.out.println("#### Calling backup method");
		int square = squareService.getSquare(num);
		return "Square of " + num + " : " + square;
	}
	
	@GetMapping("/square/string/{num}")
	@HystrixCommand(fallbackMethod = "getStringResultFromSquareServiceBkup")
	public String getStringResultFromSquareService(@PathVariable int num) {
		System.out.println("#### Calling backup method");
		String square = squareService.getStringSquare(num);
		return "Square of " + num + " : " + square;
	}
	
	public String getResultFromSquareServiceBkup(int num) {
		System.out.println("#### Backup method called");
		return "Service is down";
	}
	
	public String getStringResultFromSquareServiceBkup(int num) {
		System.out.println("#### Backup method called");
		return "String Service is down";
	}
	
}
