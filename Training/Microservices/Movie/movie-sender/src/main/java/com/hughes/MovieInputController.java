package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieInputController {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value($)
	p
	
	@GetMapping("/movie/{title}/{year}")
	public String defaultResp(@PathVariable String title, @PathVariable String year) {
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setYear(year);
		jmsTemplate.convertAndSend("OMDBReq",title+":"+year);
		
		
		return "You'll receive the movie value soon";		
	}
}