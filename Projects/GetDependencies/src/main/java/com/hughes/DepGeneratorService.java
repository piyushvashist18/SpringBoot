package com.hughes;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class DepGeneratorService {
	

	@Value("${repo.local}")
	private String localRepo;
	
	@Value("${repo.bitbucket.list}")
	private String bitbucketRepoList;
	
	@Value("${repo.diff.output.dir}")
	private String outputDir;
	
	Logger logger= LoggerFactory.getLogger(DepGeneratorService.class);
	Logger diffLogger = LoggerFactory.getLogger("dep-diff-logger");
	
	public void getDependencies(String dir) {
		logger.trace("CHECKING DEPENDENCY :" + dir);
		
		try {
			
			// Get list of all files in local repository
			File localRep = new File(dir);
			File[] localRepoFolderList = localRep.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return !"GetDependencies".equalsIgnoreCase(name);
				}
			});
			
			
			for(File file: localRepoFolderList) {				
				if(file.isDirectory()) {
					if(!checkIfExistsInJfrog(file.getAbsolutePath())) {
						logger.trace("MISSING DEPENDENCY:" + file.getAbsolutePath());
						diffLogger.trace(file.getAbsolutePath().replace(localRepo, "https://apiwebj2p2.iaglab.com/artifactory/libs-release-local/").replace("\\", "/"));
						
						FileUtils.copyDirectory(file, new File(file.getAbsolutePath().replace(localRepo, outputDir)), new FileFilter() {
							@Override
							public boolean accept(File file) {							
								return (file.isDirectory() || file.getName().endsWith(".jar") || file.getName().endsWith(".pom")) 
										&& !file.getName().endsWith("-sources.jar");
							}
						});
						
					} else {					
						getDependencies(file.getAbsolutePath());
					}
				}				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkIfExistsInJfrog(String srcDir) {
		boolean isExists = false;
		String src = srcDir.replace(localRepo, "").replace("\\", "/");
		
		try {
			String[] repos = bitbucketRepoList.split(";");
			
			for (String repo : repos) {
				String url = repo + src;

				HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
				if (con.getResponseCode() == HttpsURLConnection.HTTP_OK) {
					isExists = true;
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return isExists;
	}
	
}
