package com.hughes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieOutputController {

	@Autowired
	private List<String> movieResultDataStore;
	
	@GetMapping("/movie/history")
	public List<String> getMovieResultList() {
		return movieResultDataStore;
	}
}
