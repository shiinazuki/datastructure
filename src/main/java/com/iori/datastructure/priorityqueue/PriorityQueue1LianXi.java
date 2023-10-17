package com.iori.datastructure.priorityqueue;

import com.iori.datastructure.queue.Queue;

/**
 * 优先级队列 一端进 一端出  按优先级出队
 * 基于无序数组实现
 *
 * @param <E> 队列中元素类型, 必须实现 Priority 接口
 */
public class PriorityQueue1LianXi<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue1LianXi(int capacity) {
        array = new Priority[capacity];
    }


    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return false;
    }

    /**
     * 返回优先级最高的索引值
     *
     * @return
     */
    private int selectMax() {
        int max = 0;
        for (int i = 1; i < size; i++) {
            if (array[i].priority() > array[max].priority())
                max = i;
        }
        return max;
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
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        array[--size] = null;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[selectMax()];
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
