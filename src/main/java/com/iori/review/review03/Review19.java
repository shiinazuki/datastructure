package com.iori.review.review03;

import com.iori.datastructure.priorityqueue.Priority;
import com.iori.datastructure.queue.Queue;

/**
 * 优先级队列 有序数组实现
 *    -入队后排好序，优先级最高的排列在尾部
 *    -出队只需删除尾部元素即可
 */
public class Review19<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public Review19(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        insert(value);
        size++;
        return true;
    }

    private void insert(E e) {
        int i = size - 1;
        while (i >= 0 && array[i].priority() > e.priority()) {
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = e;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        E e = (E) array[size - 1];
        array[--size] = null; //help GC
        return e;
    }



    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return (E) array[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
