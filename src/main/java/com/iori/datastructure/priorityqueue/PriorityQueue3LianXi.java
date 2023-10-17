package com.iori.datastructure.priorityqueue;

import com.iori.datastructure.queue.Queue;

import java.util.Arrays;

/**
 * 基于<b>大顶堆</b>实现
 *
 * @param <E> 队列中元素类型, 必须实现 Priority 接口
 */
@SuppressWarnings("all")
public class PriorityQueue3LianXi<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue3LianXi(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }


    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        int parent = child / 2 - 1;
        while (child > 0 && value.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = child / 2 - 1;
        }
        array[child] = value;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, --size);
        E e = (E) array[size];
        array[size] = null;
        down(0);
        return e;
    }

    private void down(int porent) {
        int left = porent * 2 + 1;
        int right = left + 1;
        int max = porent;

        if (left < size && array[left].priority() > array[max].priority()) {
            max = left;
        }

        if (right < size && array[right].priority() > array[max].priority()) {
            max = right;
        }

        if (max != porent) {
            swap(max, porent);
            down(max);
        }

    }

    private void swap(int i, int j) {
        Priority temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
