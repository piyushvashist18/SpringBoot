package com.hughes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.hughes.entities.Account;
import com.hughes.repos.AccountRepository;


@Service
public class AccountService {

	@Autowired
	private List<AccountOperation> accountOperationStore;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${statement.base-url}")
	private String baseUrl;

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${queues.producer}")
	private String producerQueue;
	
	public void modifyUpdatePendingToYes(long accountNumber, int amount, String type) {
		Account account = accountRepository.findById(accountNumber)
						 .orElseThrow(RuntimeException::new);
		account.setUpdatePending("yes");
		accountRepository.save(account);
		String message = accountNumber + ", " + amount + ", " + type;
		jmsTemplate.convertAndSend(producerQueue, message);
	}
	
	@JmsListener(destination = "${queues.consumer}")
	public void receiveNotificationFromStatements(String message) {
		//assuming message contains success/failure, accountnumber, amount
		long accountNumber = Long.parseLong(message.split(",")[1].trim());
		int amount = Integer.parseInt(message.split(",")[2].trim());
		String status = message.split(",")[0].trim();
		Account account = accountRepository.findById(accountNumber)
				 .orElseThrow(RuntimeException::new);
		account.setUpdatePending("no");
		if(status.equals("success")) {
			account.setBalance(account.getBalance() + amount);
		}
		accountRepository.save(account);
		accountOperationStore.add(new AccountOperation(accountNumber, "deposit", status));
	}
	
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
		String url = baseUrl + "/audit?accountnumber="+ accountNumber +"&amount="+ amount +"&type=" + type;
		restTemplate.getForEntity(url, String.class);		
	}
	
}