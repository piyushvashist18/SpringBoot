package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieInputController {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${movie.input}")
	private String movieInputQueue;
	
	@PostMapping("/movie/{payload}")
	public boolean writeToMovieInput(@PathVariable String payload) {
		jmsTemplate.convertAndSend(movieInputQueue, payload);
		return true;
	}
}
