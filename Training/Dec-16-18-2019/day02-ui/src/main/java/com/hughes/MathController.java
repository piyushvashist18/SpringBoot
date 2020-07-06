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
	//@HystrixCommand(fallbackMethod = "getResultFromSquareServiceBackup")
	public String getResultFromSquareService(@PathVariable int num) {
		System.out.println("********Calling getResultFromSquareService()");
		String square = squareService.getSquare(num);
		return "Square of " + num + " : " + square;
	}
	
	public String getResultFromSquareServiceBackup(int num) {
		System.out.println("---------In the fallback getResultFromSquareServiceBackup()");
		return "Oops!! The square service seems to be down. Please try later";
	}
	
}










