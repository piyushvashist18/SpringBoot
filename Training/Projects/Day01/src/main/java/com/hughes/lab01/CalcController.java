package com.hughes.lab01;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController =  @Controller + @ResponseBody
@RestController
@RequestMapping("/calc")
public class CalcController {

	@Autowired
	private Calculator calculator;

	@Autowired
	@Qualifier("why")
	private List<String> mathOperations;
	
	@GetMapping("/ops")
	public List<String> getAllOperations() {
		return mathOperations;
	}
	
	
	@GetMapping("/sum/{a}/{b}")
	public CalcOperationResult sum(@PathVariable("a") int num1, @PathVariable("b") int num2) {
		int result = calculator.add(num1, num2);
		CalcOperationResult calcOperationResult = new CalcOperationResult();
		calcOperationResult.setNumber1(num1);
		calcOperationResult.setNumber2(num2);
		calcOperationResult.setOperation("Addition");
		calcOperationResult.setResult(result);
		return calcOperationResult;
	}
	
	
	@PostMapping("/product")
	public int product(@RequestParam int num1, @RequestParam int num2) {
		return calculator.multiply(num1, num2);
	}
	
	@PutMapping("/subtract/{a}/{b}")
	public int subtract(@PathVariable("a") int num1, @PathVariable("b") int num2) {
		return calculator.subtract(num1, num2);
	}
	
	@GetMapping("/square/{num}")
	public int square(@PathVariable int num) {
		return calculator.square(num);
	}
	
	@GetMapping("/add/{a}/{b}")
	public int add(@PathVariable("a") int num1, @PathVariable("b") int num2) {
		return calculator.add(num1, num2);
	}
	
}
