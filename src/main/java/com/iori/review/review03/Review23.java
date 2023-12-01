package com.iori.review.review03;

/**
 * 大顶堆
 */
public class Review23 {

    private int[] array;
    private int size;

    public Review23(int capacity) {
        array = new int[capacity];
    }

    public int peek() {
        return array[0];
    }

    public int poll() {
        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }

    public int poll(int index) {
        int deleted = array[index];
        up(Integer.MAX_VALUE, index);
        poll();
        return deleted;
    }

    //替换堆顶元素
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    public boolean offer(int offered) {
        if (size == array.length) {
            return false;
        }
        up(offered, size);
        size++;
        return true;
    }

    private void up(int offered, int index) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) >>> 1;
            if (array[parent] < offered) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }

    //建堆
    public void heapify() {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }

        if (max != parent) {
            swap(max, parent);
            down(max);
        }

    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
