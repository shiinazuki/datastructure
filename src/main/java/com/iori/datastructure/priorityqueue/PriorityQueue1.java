package com.iori.datastructure.priorityqueue;

import com.iori.datastructure.queue.Queue;

/**
 * 优先级队列 一端进 一端出  按优先级出队
 * 基于无序数组实现
 *
 * @param <E> 队列中元素类型, 必须实现 Priority 接口
 */
public class PriorityQueue1<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue1(int capacity) {
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
        E e = (E) array[selectMax()];
        remove(max);
        return e;
    }

    /**
     * 移除优先级最高的元素
     *
     * @param index
     */
    private void remove(int index) {
        if (index < size - 1) {
            //移动
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
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

    /**
     * 返回优先级最高的索引值
     *
     * @return
     */
    private int selectMax() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
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
