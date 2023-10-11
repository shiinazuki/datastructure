package com.iori.datastructure.priorityqueue;

import com.iori.datastructure.queue.Queue;

/**
 * 优先级队列 一端进 一端出  按优先级出队
 * 基于有序数组实现
 *
 * @param <E> 队列中元素类型, 必须实现 Priority 接口
 */
public class PriorityQueue2<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue2(int capacity) {
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
        array[--size] = null;
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
