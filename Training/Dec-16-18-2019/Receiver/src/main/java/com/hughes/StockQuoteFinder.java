package com.hughes;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class StockQuoteFinder {

	@JmsListener(destination="StockQueue", containerFactory="stockFactory")
	public void receiveMessage(String symbol) {
		double price = Math.random() * 1000;
		System.out.println("Price of " + symbol + ": " + price);
	}
}
