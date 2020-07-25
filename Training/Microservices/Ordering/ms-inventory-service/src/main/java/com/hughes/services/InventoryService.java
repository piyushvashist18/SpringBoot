package com.hughes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hughes.entities.Inventory;
import com.hughes.repos.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepo;

	public Inventory addItem(Inventory inventory) {
		return inventoryRepo.save(inventory);
	}

	public Inventory updateItem(Inventory updatedInventory) {
		Inventory inventory = getItem(updatedInventory.getItem());

		inventory.setQuantity(updatedInventory.getQuantity());
		inventory.setPrice(updatedInventory.getPrice());

		return inventoryRepo.save(inventory);
	}

	public Inventory getItem(String item) {
		System.out.println("Received Query for item:" + item);

		return inventoryRepo.findByItem(item);
	}

	public List<Inventory> getAllItems() {
		return inventoryRepo.findAll();
	}

}
