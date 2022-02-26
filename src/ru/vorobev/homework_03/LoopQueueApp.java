package ru.vorobev.homework_03;

public class LoopQueueApp {
	public static void main(String[] args) {
		LoopQueue lq = new LoopQueue(10);
		
		for (int i = 0; i < 15; i++) {
			if (lq.isFull()) {
				System.out.println("Очередь переполнена. Удалено число " + lq.remove());
			}
			lq.insert(i);
		}
	}
}
