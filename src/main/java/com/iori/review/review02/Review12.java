package com.iori.review.review02;

import com.iori.datastructure.queue.Queue;

/**
 * 环形数组 实现队列
 */
public class Review12<E> implements Queue<E> {

    private int head = 0;
    private int tail = 0;
    private final E[] array;
    private int capacity;
    private int size = 0;



    public Review12(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = array[head];
        head = (head + 1) % capacity;
        size--;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
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
