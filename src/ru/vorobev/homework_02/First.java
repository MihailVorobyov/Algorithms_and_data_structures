package ru.vorobev.homework_02;

import ru.vorobev.homework_02.sort.*;

import java.util.Arrays;

public class First {
	
	public static void main(String[] args) {
		int arrayLength = 50_000;

		Notebook[] notebooks = new Notebook[arrayLength];
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < notebooks.length; i++) {
			Manufacturer manufacturer = Manufacturer.values()[(int) (Math.random() * 5)];
			int memory = ((int) (Math.random() * 6) + 1) * 4;
			int price = ((int) (Math.random() * 40) + 1) * 50;
			notebooks[i] = new Notebook(manufacturer, memory, price);
		}
		long fillTime = System.currentTimeMillis() - start;
		System.out.println("Заполнение массива заняло " + fillTime + " мс");
		
		start = System.currentTimeMillis();
		
		//Лидер - Arrays.sort(T[] a, Comparator<? super T> c)
		Arrays.sort(notebooks, new NotebookComparator());   //10_000: 20 мс; 50_000: 48 мс
		
//		NotebookBubbleSort.sort(notebooks);     //10_000: 878 мс; 50_000: 45073 мс
//		NotebookCocktailSort.sort(notebooks);   //10_000: 1046 мс; 50_000: 42562 мс
//		NotebookCombSort.sort(notebooks);   //10_000: 40 мс; 50_000: 174 мс
//		NotebookInsertionSort.sort(notebooks);  //10_000: 145 мс; 50_000: 11312 мс
//		NotebookSelectionSort.sort(notebooks);  //10_000: 518 мс; 50_000: 20894 мс
		
		long sortTime = System.currentTimeMillis() - start;
		System.out.println("Сортировка заняла " + sortTime + " мс");
		
		// Визуальная проверка
//		for (int i = 0; i < 500; i++) {
//			System.out.println(notebooks[i]);
//		}
	}
}
