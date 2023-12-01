package com.iori.review.review03;

import com.iori.datastructure.deque.Deque;

/**
 * 环形数组 实现 双端队列
 * <ul>
 *     <li>容量是2的幂</li>
 *     <li>tail 停下来的位置不存储, 会浪费一个位置</li>
 * </ul>
 */
public class Review17<E> implements Deque<E> {


    private E[] array;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public Review17(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }
        array[++head & array.length - 1] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            return false;
        }
        array[tail++ & array.length - 1] = e;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        int idx = head++ & array.length - 1;
        E e = array[idx];
        array[idx] = null;
        return e;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        int idx = --tail & array.length - 1;
        E e = array[idx];
        array[idx] = null;
        return e;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return array[head & array.length - 1];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return array[tail - 1 & array.length - 1];
    }

    @Override
    public boolean isEmpty() {
        return tail - head == 0;
    }

    @Override
    public boolean isFull() {
        return tail - head == array.length - 1;
    }
}
