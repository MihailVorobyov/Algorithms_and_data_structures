package ru.vorobev.homework_02;

import java.util.Comparator;

public class NotebookComparator implements Comparator<Notebook> {
	
	@Override
	public int compare(Notebook o1, Notebook o2) {
		if (o1.price - o2.price == 0) {
			if (o1.memory - o2.memory == 0) {
				return o1.manufacturer.ordinal() - o2.manufacturer.ordinal();
			} else {
				return o1.memory - o2.memory;
			}
		} else {
			return o1.price - o2.price;
		}
	}
}
