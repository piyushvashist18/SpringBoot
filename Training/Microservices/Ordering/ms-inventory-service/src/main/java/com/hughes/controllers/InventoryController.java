package com.hughes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hughes.entities.Inventory;
import com.hughes.services.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	InventoryService invService;

	@GetMapping("/get/{name}")
	public Inventory getItem(@PathVariable String name) {
		return invService.getItem(name);
	}

	@GetMapping("/get/all")
	public List<Inventory> getAllItems() {
		return invService.getAllItems();
	}

	@PostMapping("/add")
	public Inventory addItem(@RequestBody Inventory inventory) {
		return invService.addItem(inventory);
	}

	@PutMapping("/update")
	public Inventory updateItem(@RequestBody Inventory inventory) {
		return invService.updateItem(inventory);
	}

}
