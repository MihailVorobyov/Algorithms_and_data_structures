package ru.vorobev.homework_02;

import java.util.Arrays;

public class Second {
	
	public static void main(String[] args) {
		int n = 10;
		
		int miss;
		
		do {
			miss = (int) (Math.random() * (n + 1)) + 1;
		} while (miss == 1 || miss == n);
		
		System.out.println("miss = " + miss);
		int[] arr = new int[n];
		int count = 0;
		
		for (int i = 1; i <= n; i++) {
			if (i == miss) {
				continue;
			}
			arr[count++] = i;
		}
		
		System.out.println("miss is " + find(arr));
	}
	
	private static int find(int[] arr) {
		int miss = 0;
		
		if (arr.length == 0) {
			return 1;
		}
		
		Arrays.sort(arr);
		
		for (int i = 1; i < arr.length; i++) {
			
			if (arr[i] - arr[i - 1] > 1) {
				miss = arr[i] - 1;
				break;
			}
		}
		
		return miss;
	}
}
