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

	public int getSquare(int number) {

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String url = baseUrl + "/" + number;
		ResponseEntity<Integer> response = restTemplate.getForEntity(url, Integer.class);
		return response.getBody();
	}

	public String getStringSquare(int number) {
		String url = baseUrl + "/string/" + number;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response.getBody();
	}

}
