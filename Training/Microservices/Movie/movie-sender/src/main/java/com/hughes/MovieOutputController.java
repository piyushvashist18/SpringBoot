package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieOutputController {
	
	@Autowired
	private List<String> movieResultDataStore;
}

