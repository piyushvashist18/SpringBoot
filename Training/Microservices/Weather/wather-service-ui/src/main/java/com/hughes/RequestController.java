package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/weather")
@RestController
public class RequestController {

	@Autowired
	private WeatherService weatherService;
	
	@Value("${weather.base-url}")
	private String baseUrl;
	
	@GetMapping("/{city}")
	public ResponseEntity<String> getCityTemp(@PathVariable String city) {
		System.out.println("City:" + city);
		return ResponseEntity.ok(weatherService.getCityTemp(city));
	}
	
//	@GetMapping("/{city}")
//	@HystrixCommand(fallbackMethod = "handleServiceDown")
//	public String getCityTemp(@PathVariable String city) {
//		String url = baseUrl + "/weather/" + city;
//		ResponseEntity<String> respBody = restTemplate.getForEntity(url, String.class);
//		return respBody.getBody();		
//	}
	
	public ResponseEntity<String> handleServiceDown(String city) {
		return ResponseEntity.ok("Backend service is down from UI");
	}
}
