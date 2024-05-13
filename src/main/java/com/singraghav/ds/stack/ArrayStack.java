package com.singraghav.ds.stack;

public class ArrayStack<E> implements MStack<E> {

    public static final int CAPACITY = 100;

    private E[] data;

    private int t = -1;

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(int capacity) {
        data = (E[]) new Object [capacity];
    }

    @Override
    public void push(E element) {
        if (size() == data.length) throw new IllegalStateException("Stack full");
        data[++t] = element;
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        E top = data[t];
        data[t] = null;
        t -= 1;
        return top;
    }

    @Override
    public E top() {
        if (isEmpty()) return null;
        return data[t];
    }

    @Override
    public int size() {
        return t + 1;
    }

    @Override
    public boolean isEmpty() {
        return t == -1;
    }
}
