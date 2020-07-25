package com.hughes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hughes.entities.OrderInfo;
import com.hughes.services.OrderingService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderingService service;
	
	@PostMapping("/create")
	public Object createOrder(@RequestBody OrderInfo ordInfo) {
		return service.createOrder(ordInfo);
	}
	
	@GetMapping("/list")
	public List<OrderInfo> getAllOrders(){
		return service.getAllOrders();
	}
	
}