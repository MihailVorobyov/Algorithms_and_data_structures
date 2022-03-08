package ru.vorobev.homework_05;

public class PowerApp {
	
	public static void main(String[] args) {
		long number = 2;
		int power = 10;
		
		long res = pow(number, power);
		System.out.println(res);
	}
	
	private static long pow(long number, int power) {
		long result = number;
		
		if (power > 1) {
			result = result * pow(number, power - 1);
		}
		
		return result;
	}
}
