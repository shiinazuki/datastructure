package com.iori.review.review03;

import com.iori.datastructure.deque.Deque;

/**
 * 双端队列  环形链表实现
 */
public class Review16<E> implements Deque<E> {

    //节点类
    public static class Node<E> {
        Node<E> prev;
        E value;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private final Node<E> sentinel = new Node<>(null, null, null);
    private int capacity;
    private int size;

    public Review16(int capacity) {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        this.capacity = capacity;
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }
        Node<E> a = sentinel;
        Node<E> b = sentinel.next;
        Node<E> added = new Node<>(a, e, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            return false;
        }
        Node<E> prev = sentinel.prev;
        Node<E> b = sentinel;
        Node<E> added = new Node<>(prev, e, b);
        prev.next = added;
        b.prev = added;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> a = sentinel;
        Node<E> deleted = sentinel.next;
        Node<E> b = deleted.next;
        a.next = b;
        b.prev = a;
        size--;
        return deleted.value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        Node<E> prev = sentinel.prev;
        Node<E> a = prev.prev;
        Node<E> b = sentinel;

        a.next = b;
        b.prev = a;
        size--;
        return prev.value;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }
}