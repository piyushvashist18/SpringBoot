package com.hughes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hughes.entities.Statement;
import com.hughes.repos.StatementRepository;

@Service
public class StatementService {
	
	@Autowired
	private StatementRepository statementRepository;
	

	public void auditStatement(long accountNumber, long amount, String type) {
		Statement statement = new Statement();
		statement.setAccountNumber(accountNumber);
		statement.setType(type);
		statement.setAmount(amount);
		statementRepository.save(statement);
	}
}
