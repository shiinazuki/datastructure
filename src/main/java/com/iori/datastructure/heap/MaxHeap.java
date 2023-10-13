package com.iori.datastructure.heap;

import java.util.Arrays;

/**
 * 大顶堆
 */
public class MaxHeap {
    int[] array;
    int size;

    public MaxHeap(int capacity) {
        this.array = new int[capacity];
    }

    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    /**
     * 建堆
     */
    private void heapify() {
        //如何找到最后这个非叶子节点 size  / 2 - 1
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    /**
     * 下潜方法
     *
     * @param parent
     */
    public void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }
        if (max != parent) { //找到更大的孩子
            //替换
            swap(max, parent);
            down(max);
        }
    }

    /**
     * 替换方法
     *
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 获取堆顶元素
     *
     * @return 堆顶元素
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("堆为空");
        }
        return array[0];
    }

    /**
     * 删除堆顶元素
     *
     * @return 堆顶元素
     */
    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("堆为空，无法移除元素");
        }
        int top = array[0];
        swap(0, size - 1);
        size--;
        //下潜
        down(0);
        return top;
    }

    /**
     * 删除指定索引处元素
     *
     * @param index 索引
     * @return 被删除元素
     */
    public int poll(int index) {
        if (isEmpty()) {
            throw new RuntimeException("堆为空，无法移除元素");
        }
        if (index > array.length) {
            throw new IndexOutOfBoundsException();
        }
        //先记录指定位置的数据
        int deleted = array[index];
        swap(index, size - 1);
        size--;
        //下潜
        down(index);
        return deleted;
    }

    /**
     * 替换堆顶元素
     *
     * @param replaced 新元素
     */
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    /**
     * 堆的尾部添加元素
     *
     * @param offered 新元素
     * @return 是否添加成功
     */
    public boolean offer(int offered) {
        if (isFull()) {
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    // 将 offered 元素上浮: 直至 offered 小于父元素或到堆顶
    private void up(int offered) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (offered > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;

    }


    /**
     * 判断空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断满
     *
     * @return
     */
    public boolean isFull() {
        return size == array.length;
    }


    public static void main(String[] args) {

        int[] array = {2, 3, 1, 7, 6, 5, 4};
        MaxHeap heap = new MaxHeap(array);
        System.out.println(Arrays.toString(heap.array));
        //循环堆大小 - 1 次
        while (heap.size > 1) {
            //先交换堆顶 和 堆尾的元素
            heap.swap(0, heap.size - 1);
            //堆的大小减1
            heap.size--;
            //交换完后下潜
            heap.down(0);
        }


        System.out.println(Arrays.toString(heap.array));
    }
}
