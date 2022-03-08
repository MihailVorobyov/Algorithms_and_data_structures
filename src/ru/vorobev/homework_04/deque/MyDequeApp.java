package ru.vorobev.homework_04.deque;

import java.util.Iterator;

public class MyDequeApp {
	
	public static void main(String[] args) {
		MyDequeWithRevers<String> myDeque = new MyDequeWithRevers<>(11);
		
		for (int i = 0; i < 11; i++) {
			myDeque.insert(Integer.toString(i));
		}
		myDeque.display();
		
		Iterator<String> iterator = myDeque.iterator();
		
		for (int i = 0; i < 6; i++) {
			if (iterator.hasNext()) {
				System.out.println("next is " + iterator.next());
			}
		}
		
		iterator.remove();
		myDeque.display();
		System.out.println("Удалён элемент: " + myDeque.remove());
		myDeque.display();
		
		for (String s : myDeque) {
			System.out.print(s + " ");
		}

	}
}
