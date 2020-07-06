package com.hughes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
	Logger logger = LoggerFactory.getLogger(WelcomeService.class);

	@Value("${welcome}")
	private String welcome;
	
	public String getMessage() {
		logger.info("*****getMessage");
		return welcome;
	}
}
