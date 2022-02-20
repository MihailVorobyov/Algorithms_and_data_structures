package ru.vorobev.homework_02.sort;

import ru.vorobev.homework_02.Notebook;

import java.util.Arrays;

public class NotebookSelectionSort {
    public static void sort(Notebook[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[min]) < 0) {
                    min = j;
                }
            }

            Notebook temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;

//            System.out.println("i: " + i + Arrays.toString(arr));
        }
    }
}
