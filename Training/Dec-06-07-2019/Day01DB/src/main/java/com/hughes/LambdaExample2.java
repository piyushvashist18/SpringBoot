package com.hughes;

class MyCalculator {
	public int num1, num2;
	
	public int compute(Operation operation) {
		return operation.evaluate(num1, num2);
	}
}
interface Operation {
	int evaluate(int a, int b);
}

public class LambdaExample2 {

	public static void main(String[] args) {
		MyCalculator myCalc = new MyCalculator();
		myCalc.num1 = 12;
		myCalc.num2 = 15;
		myCalc.compute((a, b) -> a + b);
		myCalc.compute((a, b) -> a - b);
		myCalc.compute((a, b) -> a * b);
		myCalc.compute((a, b) -> a / b);
		myCalc.compute((a, b) -> a*a + b*b + 2*a*b);
		
		
		
		
	}

}
