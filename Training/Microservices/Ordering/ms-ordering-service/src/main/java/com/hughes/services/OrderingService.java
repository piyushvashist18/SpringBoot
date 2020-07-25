package com.hughes.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hughes.Configuration;
import com.hughes.controllers.FeignClientInterface;
import com.hughes.entities.Inventory;
import com.hughes.entities.OrderInfo;
import com.hughes.repos.OrderingRepository;

@Service
public class OrderingService {

	@Autowired
	private OrderingRepository orderinRepo;
	
	@Autowired
	FeignClientInterface feignClient;
	
	@Autowired
	Configuration config;

	public String createOrder(OrderInfo ordInfo) {
		
		if(ordInfo.getQuantity() > config.getMoq()) {
			
			//Validate Item and Available Quantity in Inventory
			Inventory inv = feignClient.getItem(ordInfo.getItem());
			
			if(inv.getQuantity() > ordInfo.getQuantity()) {
				
				// Calculate total order amount using price of each item
				double totalAmount = ordInfo.getQuantity() * inv.getPrice();			
				ordInfo.setTotalAmount(totalAmount);
				
				// Capture order into database
				ordInfo.setCreateDate(new Date());
				ordInfo = orderinRepo.save(ordInfo);
				
				// Update Inventory
				inv.setQuantity(inv.getQuantity() - ordInfo.getQuantity());
				feignClient.updateItem(inv);
				
				return "Order Placed successfully: " + ordInfo.toString();
			} else {
				return "Availble Quantity of " + ordInfo.getItem() + " is: " + inv.getQuantity();
			}
			
		} else {
			return "Minimun Order Quantity for this item is: " + config.getMoq();
		}
		
	}
	
	public List<OrderInfo> getAllOrders() {
		return orderinRepo.findAll();
	}

}
