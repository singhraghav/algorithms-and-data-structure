package com.singraghav.ds.linkedlist.single;

public class SLinkedList<T> {

    private int size;
    private LNode<T> head;
    private LNode<T> tail;

    public SLinkedList(T element) {
        initialize(element);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return this.size;
    }

    public void append(T element) {
        if(this.isEmpty())
            initialize(element);
        else
            appendAtLast(element);
    }

    private void initialize(T element) {
        this.head = new LNode<>(element, null);
        this.tail = head;
        this.size = 1;
    }

    private void appendAtLast(T element) {
        LNode<T> temp = new LNode<>(element);
        tail.setNext(temp);
        tail = temp;
        this.size += 1;
    }
}
