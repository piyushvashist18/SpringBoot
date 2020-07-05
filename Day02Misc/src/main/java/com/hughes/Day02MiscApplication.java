package com.hughes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Day02MiscApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day02MiscApplication.class, args);
	}

}
