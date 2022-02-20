package ru.vorobev.homework_02.sort;

import ru.vorobev.homework_02.Notebook;

public class NotebookInsertionSort {
    public static void sort(Notebook[] arr){
        for (int i = 0; i < arr.length; i++) {
            int j = i - 1;
            Notebook value = arr[i];

            while (j >= 0 && arr[j].compareTo(value) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = value;
        }
    }
}
