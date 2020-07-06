package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OMDBReqListener {
	
	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	private RestTemplate restTemplate;
	
	@JmsListener(destination="OMDBReq")
	public void receiveMessage(String input) {
		
		System.out.println("Input:" + input);
		
		String inputArr[] = input.split(":");
		
		System.out.println("title: " + inputArr[0] + " year: " + inputArr[1]);
		
		pushToRespQueue(inputArr[0], inputArr[1]);		
	}
	
	public String pushToRespQueue(String name, String year) {
		String url = "http://www.omdbapi.com/?i=tt3896198&apikey=52d1c7f&t=" + name + "&y=" + year;
		ResponseEntity<Movie> resp = restTemplate.getForEntity(url, Movie.class);
		Movie movie = resp.getBody();
		jmsTemplate.convertAndSend("OMDBResp", movie.getActors() + ":" + movie.getDirector());
		return "Submitted to response queue";		
	}
}