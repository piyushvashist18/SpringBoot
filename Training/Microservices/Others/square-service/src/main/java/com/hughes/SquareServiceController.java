package com.hughes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SquareServiceController {

	@GetMapping("/squareservice/{num}")
	@ResponseBody
	public int computeSquare(@PathVariable int num) {
		return num*num;
	}
	
	@Autowired
	private Environment env;
	
	@GetMapping("/squareservice/string/{num}")
	@ResponseBody
	public String getSquare(@PathVariable int num) {
		return num * num + "(from port: " + env.getProperty("local.server.port") + ")";
	}
}
