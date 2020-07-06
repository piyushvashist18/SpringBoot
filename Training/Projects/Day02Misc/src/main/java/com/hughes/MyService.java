package com.hughes;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	@Cacheable(value = "message", key = "#name")
	public String getMessage(String name) {
		System.out.println("**** Getting the message for " + name);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Hello " + name;
	}
	
	@CacheEvict(value = "message", key = "#name")
	public String removeMessage(String name) {
		return "Removed " + name;
	}
}







