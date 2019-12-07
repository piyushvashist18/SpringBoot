package com.hughes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hughes.entities.Account;
import com.hughes.entities.Statement;
import com.hughes.repos.AccountRepository;
import com.hughes.repos.StatementRepository;

@Component
public class AccountService {
	
	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private StatementRepository stmtRepo;
	
	public void deposit(int accountNumber,int amount, String type) {
		Optional<Account> optAccount = accRepo.findById(accountNumber);
		if(optAccount.isPresent()) {
			Account account = optAccount.get();
			account.setBalanace(account.getBalanace() + amount);
			accRepo.save(account);
			createStatement(accountNumber, amount, type);
		}
	 }

	public void withdraw(int accountNumber,int amount, String type) {
		 Optional<Account> optAccount = accRepo.findById(accountNumber);
			if(optAccount.isPresent()) {
				Account account = optAccount.get();
				account.setBalanace(account.getBalanace() - amount);
				accRepo.save(account);
				createStatement(accountNumber, amount, type);
			}
	 }
	
	private void createStatement(int accountNumber, int amount, String type) {
		Statement stmt = new Statement();
		stmt.setAccount_number(accountNumber);
		stmt.setAmount(amount);
		stmt.setType(type);
		stmtRepo.save(stmt);		
	}
}
