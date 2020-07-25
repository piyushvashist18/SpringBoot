package com.hughes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hughes.entities.Inventory;

@RestController
@RequestMapping("/order")
public class DiscoveryController {
	
	@Autowired
	FeignClientInterface feignClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/validate/inventory")
	public String validateOrder() {
		Inventory inv = feignClient.getItem("Book");
		
		if(inv.getQuantity() > 0) {
			return "Success";
		}
		return "false";
	}
	
	@GetMapping("/validate/inventory/rest")
	public String validateOrderRest() {
		Inventory inv = restTemplate.exchange("http://ms-inventory-service//inventory/get/Book", 
				HttpMethod.GET, null, Inventory.class).getBody();
		
		if(inv.getQuantity() > 0) {
			return "Success";
		}
		return "false";
	}
	
}