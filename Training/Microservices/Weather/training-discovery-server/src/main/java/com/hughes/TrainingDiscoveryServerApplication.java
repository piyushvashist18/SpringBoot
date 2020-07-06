package com.hughes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TrainingDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingDiscoveryServerApplication.class, args);
	}

}
