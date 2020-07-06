package com.hughes.lab01;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnotherConfiguration {

	@Bean(name = "why")
	public List<String> mathOperations() {
		List<String> lst = Arrays.asList("whyadd", "whysubtract", "whymultiply", "whysquare");
		return lst;
	}
}
