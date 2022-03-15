package ru.vorobev.homework_06;

import java.util.Deque;
import java.util.LinkedList;

public class My4LevelTree<E extends Comparable<? super E>> implements Tree<E> {
	public static final int DEEP = 4;
	
	private Node<E> root;
	private int size;
	private int level;
	
	public My4LevelTree() {
		this.root = null;
		this.size = 0;
		this.level = 0;
	}
	
	private class NodeAndParent {
		Node<E> parent;
		Node<E> current;
		
		public NodeAndParent(Node<E> parent, Node<E> current) {
			this.parent = parent;
			this.current = current;
		}
	}
	
	public Node<E> getRoot() {
		return root;
	}
	
	@Override
	public boolean contains(E value) {
		if (value == null) {
			throw new NullPointerException("value is null");
		}
		NodeAndParent nodeAndParent = doFind(value);
		return nodeAndParent.current != null;
	}
	
	private NodeAndParent doFind(E value) {
		if (value == null) {
			throw new NullPointerException("value is null");
		}
		
		Node<E> parent = null;
		Node<E> current = root;
		
		level = root == null ? 0 : 1;
		
		
		while (current != null) {
			level++;
			if (value.equals(current.getValue())) {
				return new NodeAndParent(parent, current);
			}
			
			parent = current;
			current = value.compareTo(current.getValue()) < 0
				? current.getLeftChild()
				: current.getRightChild();
			
		}
		
		return new NodeAndParent(parent,current);
	}
	
	@Override
	public boolean add(E value) {
		if (value == null) {
			throw new NullPointerException("value is null");
		}
		
		if(isEmpty()) {
			root = new Node<>(value);
		} else {
			NodeAndParent nodeAndParent = doFind(value);
			
			if (nodeAndParent.current != null) {
				nodeAndParent.current.addOne();
			} else {
				if (level <= DEEP) {
					if (value.compareTo(nodeAndParent.parent.getValue()) < 0) {
						nodeAndParent.parent.setLeftChild(new Node<>(value));
					} else {
						nodeAndParent.parent.setRightChild(new Node<>(value));
					}
				}
			}
		}
		
		size++;

		return true;
	}
	
	@Override
	public boolean remove(E value) {
		NodeAndParent currentAndParent = doFind(value);
		
		Node<E> parent = currentAndParent.parent;
		Node<E> removed = currentAndParent.current;
		
		if (removed == null) {
			return false;
		}
		
		if (removed.isLeaf()) {
			removeLeafNode(removed, parent);
		} else if (removed.hasOnlyOneChild()) {
			removeNodeWithOneChild(removed, parent);
		} else {
			//[1 2 3 4 5] 6 [7 8 9 10 11]
			removedNodeWithAllChildren(removed, parent);
		}
		
		size--;
		return true;
	}
	
	private void removeLeafNode(Node<E> removed, Node<E> parent) {
		if (removed == root) {
			root = null;
		} else if (parent.isLeftChild(removed.getValue())) {
			parent.setLeftChild(null);
		} else {
			parent.setRightChild(null);
		}
	}
	
	private void removeNodeWithOneChild(Node<E> removed, Node<E> parent) {
		Node<E> child = removed.getLeftChild() != null
			? removed.getLeftChild()
			: removed.getRightChild();
		
		if (removed.isLeftChild(child.getValue())) {
			removed.setLeftChild(null);
		} else {
			removed.setRightChild(null);
		}
		
		if (removed == root) {
			root = child;
		} else if (parent.isLeftChild(removed.getValue())) {
			parent.setLeftChild(child);
		} else {
			parent.setRightChild(child);
		}
	}
	
	private void removedNodeWithAllChildren(Node<E> removed, Node<E> parent) {
		Node<E> successor = getSuccessor(removed);
		
		if (removed == root) {
			root = successor;
		} else if (parent.isLeftChild(removed.getValue())) {
			parent.setLeftChild(successor);
		} else {
			parent.setRightChild(successor);
		}
	}
	
	private Node<E> getSuccessor(Node<E> removed) {
		Node<E> successor = removed;
		Node<E> parent = null;
		Node<E> current = removed.getRightChild();
		
		while (current != null) {
			parent = successor;
			successor = current;
			current = current.getLeftChild();
		}
		
		if (successor != removed.getRightChild() && parent != null) {
			parent.setLeftChild(successor.getRightChild());
			successor.setRightChild(removed.getRightChild());
		}
		successor.setLeftChild(removed.getLeftChild());
		
		return successor;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void display() {
		if (isEmpty()) {
			System.out.println("Tree is empty");
			return;
		}
		
		Deque<Node<E>> queue = new LinkedList<>();
		queue.add(root);
		int levelSize = 1;
		int nextLevelSize = 0;
		Node<E> current;
		int currentLevel = 1;
		
		int maxSymbols = (2 << (DEEP - 1)) * 4;
		
		while (!queue.isEmpty()) {
			int elements = 2 << (currentLevel - 1);
			String indent = indent((maxSymbols - elements * 2) / (elements * 2));
			
			// Добавляем в очередь дочерние элементы всех родительских элементов текущего уровня
			// и параллельно печатаем элементы текущего уровня
			for (int i = 0; i < levelSize; i++) {
				
				System.out.print(indent);
				
				if ((current = queue.poll()) == null) {
					System.out.print("  ");
					System.out.print(indent);
					if (currentLevel < DEEP) {
						queue.offer(null);
						queue.offer(null);
						nextLevelSize += 2;
					}
					continue;
				}

				System.out.print(current.getValue());
					System.out.print(indent);
				
				queue.offer(current.getLeftChild());
				nextLevelSize++;
				
				queue.offer(current.getRightChild());
				nextLevelSize++;
			}
			
			levelSize = nextLevelSize;
			nextLevelSize = 0;
			
			System.out.println("");
			currentLevel++;
		}
	}
	
	private String indent(int indent) {
		return " ".repeat(indent);
	}
	
	public boolean isBalanced(Node<E> root) {
		return (root == null) ||
			isBalanced(root.getLeftChild()) &&
				isBalanced(root.getRightChild()) &&
				Math.abs(height(root.getLeftChild()) - height(root.getRightChild())) <= 1;
	}
	
	private int height(Node<E> node) {
		return node == null ? 0 : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
	}
	
	@Override
	public void traverse(TraversMode mode) {
	
	}
}
