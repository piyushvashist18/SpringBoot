package com.hughes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "weather-service", url = "${weather.base-url}", fallback = WeatherServiceFallback.class)
public interface WeatherService {
	
	@GetMapping("/weather/{city}")
	public String getCityTemp(@PathVariable String city);

}
