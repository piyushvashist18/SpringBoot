package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherUIController {
	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/temperature/{city}")
	public String getTemperature(@PathVariable String city) {
		return weatherService.getTemperature(city);
	}
}
