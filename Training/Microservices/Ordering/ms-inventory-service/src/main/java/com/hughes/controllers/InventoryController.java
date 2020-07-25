package com.hughes.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import com.hughes.entities.Inventory;
import com.hughes.services.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	InventoryService invService;
	
	@GetMapping("/get/item/{name}")
	public Inventory getItemDetails(@PathVariable String name) {
		return invService.getItemDetails(name);
	}
	
	@PostMapping("/add")
	public Inventory addInventory(@RequestBody Inventory inventory) {		
		return invService.add(inventory);
	}

}
