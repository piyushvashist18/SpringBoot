package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SquareController {

	@Autowired
	private Environment env;
	
	@GetMapping("/square/{num}")
	public String getSquare(@PathVariable int num) {
		return num * num + "(from port: " + env.getProperty("local.server.port") + ")";
	}
}
