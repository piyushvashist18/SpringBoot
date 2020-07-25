package com.hughes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	FeignClientInterface feignClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/create")
	public String crateOrder() {
		
		String response = feignClient.checkInventory(1, "Test");
		
		if(response != null) {
			return "Order Placed Successfully with feign response:" + response;
		} 
		
		return "Unable to place orders at this time";
	}
	
	@GetMapping("/view")
	public String viewOrder() {
		
		String response = restTemplate.exchange("http://ms-inventory-service//inventory/check?count=1&item=Test", 
				HttpMethod.GET, null, String.class).getBody();
		
		if(response != null) {
			return "Order Placed Successfully with rest response:" + response;
		} 
		
		return "Unable to place orders at this time";
	}
	
}