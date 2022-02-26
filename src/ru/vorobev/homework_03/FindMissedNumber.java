package ru.vorobev.homework_03;

import java.util.Arrays;

public class FindMissedNumber {
	
	public static void main(String[] args) {
		int n = 1000; // Размер массива
		int[] arr = initArray(n);
//		int[] arr = new int[0]; // Проверка пустого массива (ожидается 1)
// 		int[] arr = new int[] {1, 2, 3, 4, 5};  // Проверка массива без пропусков (ожидается 6)
//		int[] arr = new int[] {1, 3, 4, 5, 6, 7, 8, 9, 10}; // Ожидается 2
		
		Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
		System.out.println("Пропущенное число: " + find(arr));
	}
	
	private static int[] initArray(int n) {
		int miss;
		
		do {
			miss = (int) (Math.random() * (n)) + 1;
		} while (miss == n + 1);
		
		System.out.println("miss = " + miss);
		int[] arr = new int[n];
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			if (i == miss - 1) {
				++count;
			}
			arr[i] = ++count;
		}
		
		return arr;
	}
	
	private static int find(int[] arr) {
		int miss = 0;
		
		if (arr.length == 0) {
			return 1;
		} else {
			if (arr[0] != 1) {
				return 1;
			} else if (arr[arr.length - 1] == arr.length) {
				return arr.length + 1;
			}
		}
		
		int start = 0;
		int end = arr.length - 1;
		int middle = end / 2;
		int iteration = 0;
		
		while ((end - start) != 1) {
			iteration++;
			if (arr[middle] == middle + 1) {
				start = middle;
			} else {
				end = middle;
			}
			middle = (start + end) / 2;
		}
		
		System.out.println("\nНайдено за " + iteration + " итераций");
		return arr[start] + 1;
	}
}
