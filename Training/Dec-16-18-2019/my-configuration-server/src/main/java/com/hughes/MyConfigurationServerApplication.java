package com.hughes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MyConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyConfigurationServerApplication.class, args);
	}

}
