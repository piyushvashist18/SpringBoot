package com.hughes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MovieResultReader {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	private List<String> movieResultDataStore;
	
	@JmsListener(destination="${movie.output}")
	public void readMovieResult(String movieResult) {
		System.out.println("Reciever response from response queue:" + output);
		
		
		
		String outputArr[] = movieResult.split(":");
		
		System.out.println("actor: " + outputArr[0] + " director: " + outputArr[1]);
	}
}