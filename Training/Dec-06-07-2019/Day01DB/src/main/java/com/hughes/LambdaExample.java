package com.hughes;

interface Greetings {
	default void doSomething() {
		System.out.println("Wth is going on");
	}
	static void confuse() {
		System.out.println("Totally confused"); 
	}
	void greet();
}

class GreetingsImpl implements Greetings {
	public void greet() {
		System.out.println("Namaste");
	}
}
interface Calculator {
	int add(int a, int b);
}

public class LambdaExample {

	public static void main(String[] args) {
		Calculator calc = (a, b) -> {
			return a + b;
		};
		
		Calculator sum = (a, b) -> a + b;
		
		Greetings greetingsOld = new GreetingsImpl();
		greetingsOld.greet();
		
		Greetings greetingsNew = new Greetings() {
			public void greet() {
				System.out.println("Hello");
			}
		};
		greetingsNew.greet();
		
		Greetings greetingsModern = () -> System.out.println("Wassup");
		greetingsModern.greet();
		System.out.println(greetingsModern.getClass().getName());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
