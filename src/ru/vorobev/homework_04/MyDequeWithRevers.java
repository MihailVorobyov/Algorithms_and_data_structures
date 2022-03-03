package ru.vorobev.homework_04;


public class MyDequeWithRevers<E> implements Deque<E> {
	
	private int size;
	private final int maxSize;
	private Node<E> first;
	private Node<E> last;
	
	
	public MyDequeWithRevers() {
		this.size = 0;
		this.maxSize = -1;
		this.first = null;
		this.last = null;
	}
	
	public MyDequeWithRevers(int size, int maxSize, Node<E> first, Node<E> last) {
		this.size = size;
		this.maxSize = maxSize;
		this.first = first;
		this.last = last;
	}
	
	@Override
	public boolean insert(E value) {
		if (isEmpty()) {
			first = new Node<>(value, null, null);
			last = first;
			size++;
		} else {
			insertRight(value);
		}
		return true;
	}
	
	@Override
	public E remove() {
		return null;
	}
	
	@Override
	public E peekFront() {
		return first.getItem();
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	@Override
	public boolean isFull() {
		if (maxSize == -1) {
			return false;
		} else {
			return size == maxSize;
		}
	}
	
	@Override
	public void display() {
	
	}
	
	@Override
	public boolean insertLeft(E value) {
		Node<E> newNode = new Node<>(value, null, first);
		first.setPrevious(newNode);
		first = newNode;
		size++;
		return true;
	}
	
	@Override
	public boolean insertRight(E value) {
		Node<E> newNode = new Node<>(value, last, null);
		last.setNext(newNode);
		last = newNode;
		size++;
		return true;
	}
	
	@Override
	public E removeLeft() {
		E toDelete = first.getItem();
		first = first.getNext();
		first.setPrevious(null);
		return toDelete;
	}
	
	@Override
	public E removeRight() {
		E toDelete = last.getItem();
		last = last.getPrevious();
		last.setNext(null);
		return toDelete;
	}
}
