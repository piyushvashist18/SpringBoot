package com.hughes;

import org.springframework.stereotype.Component;

@Component
public class PalindromeLogic {

	public boolean check(String word) {
		StringBuilder sb = new StringBuilder(word);
		return word.equalsIgnoreCase(sb.reverse().toString());
	}

}
