package com.hughes.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hughes.entities.Inventory;

public interface InventoryRepository extends MongoRepository<Inventory, String>{

	public Inventory findByItem(String item);

}
