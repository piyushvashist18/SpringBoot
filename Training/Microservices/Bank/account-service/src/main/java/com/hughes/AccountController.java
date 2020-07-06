package com.hughes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hughes.services.AccountOperation;
import com.hughes.services.AccountService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private List<AccountOperation> accountOperationStore;

	@GetMapping
	public String operation(@RequestParam long accountNumber) {
		AccountOperation accOp = accountOperationStore.stream().filter(it -> it.getAccountNumber() == accountNumber)
				.findFirst().orElse(null);
		if (accOp == null) {
			return "get: Deposit operation still in progress";
		} else {
			if ("success".equals(accOp.getResponse())) {
				accountOperationStore = accountOperationStore.stream()
						.filter(it -> it.getAccountNumber() != accountNumber).collect(Collectors.toList());
				return "Deposit succeeeded";
			} else {
				return "Deposit failed";
			}
		}
	}

	@ApiOperation(value = "Deposit operation", notes = "Deposit amount to your account")
	@ApiResponses(value = { @ApiResponse(code = 200, response = String.class, message = "Amount deposited") })
	@PutMapping
	public String operation(@RequestParam long accountNumber, @RequestParam int amount, @RequestParam String type) {

		accountService.modifyUpdatePendingToYes(accountNumber, amount, type);
		return "put: Deposit operation still in progress";
	}
	
	@ApiOperation(value = "Deposit operation", notes = "Deposit amount to your account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = String.class, message = "Amount deposited")
	})
	@GetMapping("/deposit")
	public String deposit(@RequestParam("accountnumber") long accountNumber, 
			@ApiParam(allowEmptyValue = false, required = true) @RequestParam int amount, 
			@ApiParam(example = "Salary, Interest etc.,") @RequestParam String type) {
		accountService.deposit(accountNumber, amount, type);
		return "Amount deposited";
	}
	
	@GetMapping("/withdraw")
	public String withdraw(@RequestParam("accountnumber") int accountNumber, @RequestParam long amount, @RequestParam String type) {
		accountService.withdraw(accountNumber, amount, type);
		return "Amount withdrawn";
	}
}