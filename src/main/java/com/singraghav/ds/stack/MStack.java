package com.singraghav.ds.stack;

public interface MStack<E> {

    void push(E element);

    E pop();

    E top();

    int size();

    boolean isEmpty();
}
