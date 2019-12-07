package com.hughes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hughes.services.AccountService;

@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	private AccountService accServ;
	
	@PostMapping("/deposit")
	public String deposit(@RequestParam("accountnumber") int accountNumber, @RequestParam int amount, @RequestParam String type) {
		accServ.deposit(accountNumber, amount, type);
		return "Amount deposited";
	}

	@PostMapping("/withdraw")
	public String withdraw(@RequestParam("accountnumber") int accountNumber, @RequestParam int amount, @RequestParam String type) {
		accServ.withdraw(accountNumber, amount, type);
		return "Amount withdrawn";
	}
}