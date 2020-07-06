package com.hughes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MovieResultReader {

	@Autowired
	private List<String> movieResultDataStore;
	
	@JmsListener(destination = "${movie.output}")
	public void readMovieResult(String movieResult) {
		movieResultDataStore.add(movieResult);
	}
}
