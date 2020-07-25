package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderingService {

	@Value("${account.base-url}")
	private String baseUrl;

	@Autowired
	private RestTemplate restTemplate;

	public String deposit(long accountNumber, int amount) {
		String url = baseUrl + "/deposit?accountnumber="+ accountNumber +"&amount="+ amount +"&type=deposit";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response.getBody();
	}
	
	public String withdraw(long accountNumber, int amount) {
		String url = baseUrl + "/withdraw?accountnumber="+ accountNumber +"&amount="+ amount +"&type=withdraw";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response.getBody();
	}
	
	public String getStatus(int accountNumber) {
		String url = baseUrl + "?accountNumber=" + accountNumber;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response.getBody();
	}
	
	public String operate(int accountNumber, String operation, int amount) {
		String url = baseUrl + "?accountNumber=" + accountNumber + "&amount=" + amount + "&operation=" + operation + "&type=Salary";
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
		return response.getBody();
	}

}
