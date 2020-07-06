package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieResultExtractor {

	@Value("${omdb.base.url}")
	private String omdbBaseUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
	@Value("${movie.output}")
	private String movieOutputQueue;
	
	
	@JmsListener(destination = "${movie.input}")
	public void receive(String movieName) {
		String url = omdbBaseUrl + "&t=" + movieName;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		String movieResult = response.getBody();
		jmsTemplate.convertAndSend(movieOutputQueue, movieResult);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
