package com.hughes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SquareServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SquareServiceApplication.class, args);
	}

}
