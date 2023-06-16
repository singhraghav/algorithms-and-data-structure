package com.singraghav.ds.linkedlist.single;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LNode<T> {

    private T data;
    private LNode<T> next;

    public LNode(T data) {
        this.data = data;
        this.next = null;
    }

}
