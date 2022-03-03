package ru.vorobev.homework_04;


import ru.vorobev.lesson3.queue.Queue;

public interface Deque<E> extends Queue<E> {

    boolean insertLeft(E value);
    boolean insertRight(E value);

    E removeLeft();
    E removeRight();
    
    class Node<E> {
        E item;
        Node<E> previous;
        Node<E> next;
    
        public Node(E item, Node<E> previous, Node<E> next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    
        public E getItem() {
            return item;
        }
    
        public Node<E> getPrevious() {
            return previous;
        }
    
        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }
    
        public Node<E> getNext() {
            return next;
        }
    
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}