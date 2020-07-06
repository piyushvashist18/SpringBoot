package com.hughes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "weather-service", url = "http://localhost:4000/weatherservice")
public interface WeatherService {

	@GetMapping("/weather/{city}")
	String getTemperature(@PathVariable String city);

}








