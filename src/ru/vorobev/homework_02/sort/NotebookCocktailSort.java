package ru.vorobev.homework_02.sort;

import ru.vorobev.homework_02.Notebook;
import ru.vorobev.homework_02.NotebookComparator;

public class NotebookCocktailSort {
    public static void sort( Notebook[] a ){
        NotebookComparator c = new NotebookComparator();
        boolean swapped;
        do {
            swapped = false;
            for (int i =0; i<=  a.length  - 2;i++) {
                if (c.compare(a[ i ], a[ i + 1 ]) > 0) {
                    //test whether the two elements are in the wrong order
                    Notebook temp = a[i];
                    a[i] = a[i+1];
                    a[i+1]=temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                //we can exit the outer loop here if no swaps occurred.
                break;
            }
            swapped = false;
            for (int i= a.length - 2;i>=0;i--) {
                if (c.compare(a[ i ], a[ i + 1 ]) > 0) {
                    Notebook temp = a[i];
                    a[i] = a[i+1];
                    a[i+1]=temp;
                    swapped = true;
                }
            }
            //if no elements have been swapped, then the list is sorted
        } while (swapped);

    }
}
