package com.hughes;

import org.springframework.stereotype.Component;

@Component
public class WeatherServiceFallback implements WeatherService {

	@Override
	public String getCityTemp(String city) {
		return "Oops!!";
	}

}
