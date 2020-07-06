package com.hughes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
//@EnableHystrixDashboard
public class WednesdayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WednesdayServiceApplication.class, args);
	}

}
