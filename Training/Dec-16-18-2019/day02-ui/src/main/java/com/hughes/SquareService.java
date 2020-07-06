package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SquareService {

	@Value("${square.base-url}")
	private String baseUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String getSquare(int number) {
		String url = baseUrl + "/" + number;
		System.out.println(url);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response.getBody();
	}
	
}
