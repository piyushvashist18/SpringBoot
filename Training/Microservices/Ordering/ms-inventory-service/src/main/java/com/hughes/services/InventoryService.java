package com.hughes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hughes.entities.Inventory;
import com.hughes.repos.InventoryRepository;

@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepo;
	
	public Inventory add(Inventory inventory) {
		return inventoryRepo.save(inventory);
	}

	public Inventory getItemDetails(String item) {
		System.out.println("Received Query for item:" + item);
		return inventoryRepo.findByItem(item);
	}

}
