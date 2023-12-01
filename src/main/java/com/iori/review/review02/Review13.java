package com.iori.review.review02;

import com.iori.datastructure.queue.Queue;

/**
 * 环形数组 实现队列
 */
public class Review13<E> implements Queue<E> {

    private int head = 0;
    private int tail = 0;
    private final E[] array;
    private int capacity;

    public Review13(int capacity) {
        if ((capacity & (capacity - 1)) != 0) {
            throw new IllegalArgumentException("参数必须是2的幂");
        }
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail & capacity - 1] = value;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = array[head & capacity - 1];
        head++;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head & capacity - 1];
    }

    @Override
    public boolean isEmpty() {
        return tail - head == 0;
    }

    @Override
    public boolean isFull() {
        return tail - head == capacity;
    }
}
