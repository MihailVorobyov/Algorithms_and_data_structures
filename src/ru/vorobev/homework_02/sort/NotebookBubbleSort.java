package ru.vorobev.homework_02.sort;


import ru.vorobev.homework_02.Notebook;
import ru.vorobev.homework_02.NotebookComparator;

import java.util.Arrays;

public class NotebookBubbleSort {
    public static void sort(Notebook[] arr) {
        NotebookComparator c = new NotebookComparator();
        boolean isChange;
        int limit = arr.length;
        do {
            limit--;
            isChange = false;
            for (int i = 0; i < limit; i++) {
                
                if (c.compare(arr[i], arr[i + 1]) > 0) {
                    Notebook temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;

                    isChange = true;
                }
            }
//            System.out.println(limit + " " + Arrays.toString(arr));
        } while (isChange);
    }
}
