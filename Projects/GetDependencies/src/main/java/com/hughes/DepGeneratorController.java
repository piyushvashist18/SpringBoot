package com.hughes;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepGeneratorController {
	
	@Autowired
	DepGeneratorService service;
	
	@Value("${repo.local}")
	private String localRepo;
	
	@Value("${repo.diff.output.dir}")
	private String outputDir;
	
	Logger logger= LoggerFactory.getLogger(DepGeneratorController.class);
	
	@GetMapping("/dependencies/get")
	public String getDependencies() {
		
		//Delete if output directory already exists
		try {
			FileUtils.deleteDirectory(new File(outputDir));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		executor.submit(() -> {
			service.getDependencies(localRepo);
			logger.trace("===========CHECKING DEPENDENCY : COMPLETED============");
			
		});
		
		return "Dependency Check Initiated: Source -> "+ localRepo + " Destination -> " + outputDir ;
	}
}
