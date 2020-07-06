package com.hughes.services;

public class AccountOperation {
	private long accountNumber;
	private String operation;
	private String response;
	
	public AccountOperation() {
		
	}
	
	public AccountOperation(long accountNumber, String operation, String response) {
		this.accountNumber = accountNumber;
		this.operation = operation;
		this.response = response;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
}
