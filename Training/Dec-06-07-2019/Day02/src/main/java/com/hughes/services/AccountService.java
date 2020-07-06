package com.hughes.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hughes.entities.Account;
import com.hughes.entities.Statement;
import com.hughes.repos.AccountRepository;
import com.hughes.repos.StatementRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private StatementRepository statementRepository;
	
	public void deposit(long accountNumber,long amount, String type) {
		Optional<Account> optAccount = accountRepository.findById(accountNumber);
		if(optAccount.isPresent()) {
			Account account = optAccount.get();
			account.setBalance(account.getBalance() + amount);
			accountRepository.save(account);
			createStatement(accountNumber, amount, type);
		}
	}
	
	@Transactional
	public void withdraw(long accountNumber,long amount, String type) {
		Optional<Account> optAccount = accountRepository.findById(accountNumber);
		if(optAccount.isPresent()) {
			Account account = optAccount.get();
			account.setBalance(account.getBalance() - amount);
			accountRepository.save(account);
			createStatement(accountNumber, amount, type);
		}
	}

	private void createStatement(long accountNumber, long amount, String type) {
		Statement statement = new Statement();
		statement.setAccountNumber(accountNumber);
		statement.setType(type);
		statement.setAmount(amount);
		statementRepository.save(statement);
	}
}
