package com.hughes;

import org.springframework.stereotype.Component;

@Component
public class PalindromeLogic {

	public boolean check(String word) {
		StringBuilder builder = new StringBuilder(word);
		builder.reverse();
		String reversedWord = builder.toString();
		return word.equalsIgnoreCase(reversedWord);
	}

}
