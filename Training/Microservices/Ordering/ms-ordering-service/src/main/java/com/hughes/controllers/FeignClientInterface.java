package com.hughes.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hughes.entities.Inventory;

@FeignClient("ms-inventory-service")
@RequestMapping("/inventory")
public interface FeignClientInterface {
	
	@GetMapping("/get/{name}")
	public Inventory getItem(@PathVariable String name);
	
	@PutMapping("/update")
	public Inventory updateItem(@RequestBody Inventory inventory);
	
}
