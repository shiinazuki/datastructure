package com.iori.datastructure.stack;

import java.util.Iterator;
import java.util.StringJoiner;

public class LinkedListStack<E> implements Stack<E>, Iterable<E> {
    //容量
    private int capacity = Integer.MAX_VALUE;
    //元素个数
    private int size;
    private Node<E> head = new Node<>(null, null);

    public LinkedListStack() {
    }

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }


    static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }


    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        head.next = new Node<>(value, head.next);
        size++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        Node<E> first = head.next;
        head.next = first.next;
        size--;
        return first.value;
    }

    @Override
    public E peek() {
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return capacity == size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",");
        for (E e : this) {
            sj.add(e.toString());
        }
        return sj.toString();
    }

}
