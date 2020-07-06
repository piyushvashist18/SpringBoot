package com.hughes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.hughes.entities.Statement;
import com.hughes.repos.StatementRepository;

@Service
public class StatementOpsService {

	@Autowired
	private StatementRepository statementRepository;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${queues.producer}")
	private String producerQueue;
	
	@JmsListener(destination = "${queues.consumer}")
	public void receiveNotificationFromAccounts(String message) {
		long accountNumber = Long.parseLong(message.split(",")[0].trim());
		int amount = Integer.parseInt(message.split(",")[1].trim());
		String type = message.split(",")[2];
		Statement statement = new Statement();
		statement.setAccountNumber(accountNumber);
		statement.setAmount(amount);
		statement.setType(type);
		String response = "failure, " + accountNumber + ", " + amount;
		try {
			statementRepository.save(statement);
			response = "success, " + accountNumber + ", " + amount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		jmsTemplate.convertAndSend(producerQueue, response);
	}
}
