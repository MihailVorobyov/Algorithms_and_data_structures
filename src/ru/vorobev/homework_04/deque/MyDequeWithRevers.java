package ru.vorobev.homework_04.deque;


import java.util.Iterator;

public class MyDequeWithRevers<E> implements Deque<E>, Iterable<E> {
	
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
	
	public MyDequeWithRevers(int maxSize) {
		this.size = 0;
		this.maxSize = maxSize;
		this.first = null;
		this.last = null;
	}
	
	@Override
	public boolean insert(E value) {
		if (size == maxSize) {
			throw new IndexOutOfBoundsException("Deque is full");
		}
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
		E item = last.item;
		last = last.previous;
		last.next = null;
		last.previous = null;
		size--;
		return item;
	}
	
	private void remove(Node<E> node) {
		if (node == null) {
			throw new NullPointerException("Trying to delete node that is null");
		}
		
		if (node.next != null && node.previous != null) {
			node.previous.next = node.next;
			node.next.previous = node.previous;
		} else if (node.previous != null) {
			node.previous.next = null;
		} else if (node.next != null) {
			node.next.previous = null;
		}
		
		size--;
	}
	
	@Override
	public E peekFront() {
		return first.item;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
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
		System.out.println(this);
	}
	
	@Override
	public boolean insertLeft(E value) {
		Node<E> newNode = new Node<>(value, null, first);
		first.previous = newNode;
		first = newNode;
		size++;
		return true;
	}
	
	@Override
	public boolean insertRight(E value) {
		Node<E> newNode = new Node<>(value, last, null);
		last.next = newNode;
		last = newNode;
		size++;
		return true;
	}
	
	@Override
	public E removeLeft() {
		E toDelete = first.item;
		first = first.next;
		first.previous = null;
		return toDelete;
	}
	
	@Override
	public E removeRight() {
		E toDelete = last.item;
		last = last.previous;
		last.next = null;
		return toDelete;
	}
	
	@Override
	public String toString() {
		return "MyDequeWithRevers{" +
			"size=" + size +
			", maxSize=" + maxSize +
			", first=" + first +
			", last=" + last +
			'}';
	}
	
	@Override
	public Iterator<E> iterator() {
		return new MyDequeIterator<E>(this);
	}
	
	static class Node<E> {
		E item;
		Node<E> previous;
		Node<E> next;
		
		private Node(E item, Node<E> previous, Node<E> next) {
			this.item = item;
			this.previous = previous;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return "item = " + item;
		}
	}
	
	static class MyDequeIterator<E> implements Iterator<E> {
		
		private final MyDequeWithRevers myDeque;
		private Node<E> current;
		
		private MyDequeIterator(MyDequeWithRevers<E> deque ) {
			current = null;
			myDeque = deque;
		}
		
		@Override
		public boolean hasNext() {
			if (myDeque.size == 0) {
				return false;
			}
			if (current != null) {
				return current.next != null;
			}
			return true;
		}
		
		public boolean hasPrevious() {
			if (myDeque.size == 0) {
				return false;
			}
			if (current != null) {
				return current.previous != null;
			}
			return false;
		}
		
		@Override
		public E next() {
			if (current == null) {
				current = myDeque.first;
			} else {
				current = current.next;
			}
			return current.item;
			
		}
		
		public E previous() {
			if (current == null) {
				current = null;
				return null;
			} else {
				current = current.previous;
				return current.item;
			}
		}
		
		@Override
		public void remove() {
			Node<E> temp = current.next;
			myDeque.remove(current);
			current = temp;
		}
	}
}
