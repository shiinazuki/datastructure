package com.iori.review.review03;

import com.iori.datastructure.priorityqueue.Priority;
import com.iori.datastructure.queue.Queue;

/**
 * 优先级队列 无序数组实现
 *    -入队保持顺序
 *    -出对前找到优先级最高的出队 相当于一次选择排序
 */
public class Review18<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public Review18(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        int max = selectMax();
        E e = (E) array[max];
        remove(max);
        return e;
    }

    private void remove(int index) {
        if (index < selectMax() - 1) {
            System.arraycopy(array, index + 1,
                    array, index, size - 1 - index);
        }
        array[--size] = null;
    }

    private int selectMax() {
        int max = 0;
        for (int i = array.length - 1; i >= 0; i--) {

            if (array[i].priority() > array[max].priority()) {
                max = i;
            }

        }
        return max;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        int max = selectMax();
        return (E) array[max];
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
