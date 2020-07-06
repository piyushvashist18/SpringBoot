package com.hughes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
@RequestMapping("/bank")
public class BankController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private BankService bankService;
	
	@Value("${welcome}")
	private String welcome;
	
	@GetMapping("/welcome")
	public String getWelcome() {
		logger.info("Returning welcome");
		return welcome;
	}
	
	@GetMapping("/deposit/{accountNum}/{amount}")
	@HystrixCommand(fallbackMethod = "handleServiceDown")
	public String deposit(@PathVariable int accountNum, @PathVariable int amount) {
		return bankService.deposit(accountNum, amount);
	}
	
	@GetMapping("/withdraw/{accountNum}/{amount}")
	@HystrixCommand(fallbackMethod = "handleServiceDown")
	public String withdraw(@PathVariable int accountNum, @PathVariable int amount) {
		return bankService.withdraw(accountNum, amount);
	}
	
	public String handleServiceDown(int accountNum, int amount) {
		return "Backend Service is down";
	}
	
	@GetMapping("/status/{accountNumber}")
	public String getStatus(@PathVariable int accountNumber) {
		return bankService.getStatus(accountNumber);
	}
	
	@PutMapping
	public String operate(@RequestParam int accountNumber, @RequestParam String operation, @RequestParam int amount) {
		return bankService.operate(accountNumber, operation, amount);
	}
	
}
