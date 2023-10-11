package com.iori.datastructure.deque;


import java.util.Iterator;

/**
 * 基于双向环形链表实现
 *
 * @param <E>
 */
public class LinkedListDeque<E> implements Deque<E>, Iterable<E> {

    int capacity;
    int size;
    Node<E> sentinel = new Node<>(null, null, null);

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        //头指向尾 尾指向头
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    static class Node<E> {
        Node<E> prev;
        E value;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }


    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }
        Node<E> prev = sentinel;
        Node<E> next = sentinel.next;
        Node<E> added = new Node<>(prev, e, next);
        prev.next = added;
        next.prev = added;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {

        if (isFull()) {
            return false;
        }
        Node<E> prev = sentinel.prev;
        Node<E> next = sentinel;
        Node<E> added = new Node<>(prev, e, next);
        prev.next = added;
        next.prev = added;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size--;
        return first.value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        Node<E> last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        size--;
        return last.value;
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

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }
}
