package com.hughes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private List<AccountOperation> accountOperationStore;
	
	@GetMapping
	public String operation(@RequestParam long accountNumber) {
		AccountOperation accOp = accountOperationStore
			.stream()
			.filter(it -> it.getAccountNumber() == accountNumber)
			.findFirst()
			.orElse(null);
		if(accOp == null) {
			return "Deposit operation still in progress";	
		}
		else {
			if("success".equals(accOp.getResponse())) {
				accountOperationStore = accountOperationStore
											.stream()
											.filter(it -> it.getAccountNumber() != accountNumber)
											.collect(Collectors.toList());
				return "Deposit succeeeded";
			}
			else {
				return "Deposit failed";
			}
		}
	}
	
	@PutMapping
	public String operation(@RequestParam long accountNumber, 
			@RequestParam int amount, 
			@RequestParam String type, 
			@RequestParam String operation) {
		
		accountService.modifyUpdatePendingToYes(accountNumber, amount, type);
		return "Deposit operation in progress";
	}
}







