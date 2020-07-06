package com.hughes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hughes.services.AccountService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/bank")
public class BankController {
	@Autowired
	private AccountService accountService;
	
	@ApiOperation(value = "Deposit operation", notes = "Deposit amount to your account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = String.class, message = "Amount deposited")
	})
	@PostMapping("/deposit")
	public String deposit(@RequestParam("accountnumber") long accountNumber, 
			@ApiParam(allowEmptyValue = false, required = true) @RequestParam int amount, 
			@ApiParam(example = "Salary, Interest etc.,") @RequestParam String type) {
		accountService.deposit(accountNumber, amount, type);
		return "Amount deposited";
	}
	
	@PostMapping("/withdraw")
	public String withdraw(@RequestParam("accountnumber") int accountNumber, @RequestParam long amount, @RequestParam String type) {
		accountService.withdraw(accountNumber, amount, type);
		return "Amount withdrawn";
	}
}
