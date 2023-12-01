package com.iori.review.review03;

import com.iori.datastructure.linkedlist.ListNode;
import com.iori.datastructure.priorityqueue.Priority;
import com.iori.datastructure.queue.Queue;

/**
 * 优先级队列 堆实现
 *
 */
public class Review20<E extends Priority> implements Queue<E> {

    private Priority[] array;
    private int size;

    public Review20(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        while (child > 0) {
            int parent = (child - 1) >>> 1;
            if (array[parent].priority() < value.priority()) {
                array[child] = array[parent];
                child = parent;
            }
        }
        array[child] = value;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, size - 1);
        size--;
        Priority priority = array[size];
        array[size] = null;
        down(0);
        return (E) priority;
    }

    //下潜方法
    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if (left < array.length && array[left].priority() > array[max].priority()) {
            max = left;
        }
        if (right < array.length && array[right].priority() > array[max].priority()) {
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    //交换方法
    private void swap(int i, int j) {
        Priority priority = array[i];
        array[i] = array[j];
        array[j] = priority;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[0];
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



