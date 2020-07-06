package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hughes.services.StatementService;

@RestController
@RequestMapping("/statement")
public class StatementController {
	
	@Autowired
	private StatementService stmtService;
	
	@GetMapping("/audit")
	public String auditStatement(@RequestParam("accountnumber") int accountNumber, 
			@RequestParam long amount, 
			@RequestParam String type) {
		stmtService.auditStatement(accountNumber, amount, type);
		return "Statement Audited";
	}

}
