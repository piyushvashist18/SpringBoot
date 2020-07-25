package com.hughes.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ms-inventory-service")
public interface FeignClientInterface {
	
	@GetMapping("/inventory/check")
	public String checkInventory(@RequestParam("count") int count,
			@RequestParam String item);

}
