package com.hughes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class WeatherController {
	
	Logger logger = LoggerFactory.getLogger(WeatherController.class);

	@GetMapping("/weather/{city}")
	@HystrixCommand(fallbackMethod = "handleServiceDown")
	@ApiOperation(value = "getCityTemp", notes = "Get Temp of your city")
	@ApiResponses(value = { @ApiResponse(code = 200, response = String.class, message = "Returned temperature") })
	public String getCityTemp(@PathVariable String city) {
		logger.trace("Returning getTemperature");
		return "Temperature of "+ city +" is: " + String.valueOf(Math.ceil(Math.random()*100));		
	}
	
	public String handleServiceDown(String city) {
		return "Backend service is down";
	}
}
