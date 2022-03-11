package ru.vorobev.homework_06;

public class Node <T extends Comparable<? super T>> {

    private T value;

    private Node<T> leftChild;
    private Node<T> rightChild;

    private int repeat;
    private boolean isRed;
    private boolean isRemoved;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isLeftChild(T value) {
        return getValue().compareTo(value) > 0;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public boolean hasOnlyOneChild() {
/*        return leftChild == null && rightChild != null
                || leftChild != null && rightChild == null;*/
        return rightChild != null ^ leftChild != null;
    }
}
