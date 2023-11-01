package com.iori.algorithm.sort;

import java.util.Arrays;

/**
 * <h3>堆排序</h3>
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 堆排序实现
     *
     * @param array
     */
    private static void sort(int[] array) {
        int size = array.length;
        //建堆
        heapify(array);
        while (size > 1) {
            swap(array, 0, size - 1);
            size--;
            down(array, 0, size);
        }
    }

    /**
     * 建堆方法
     * 罗伯特·弗洛伊德建堆法
     * @param array
     */
    private static void heapify(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            down(array, i, array.length - 1);
        }
    }


    /**
     * 下潜方法 非递归实现
     *
     * @param array
     * @param parent
     * @param size
     */
    private static void down(int[] array, int parent, int size) {
        while (true) {
            int left = parent * 2 + 1;
            int right = left + 1;
            int max = parent;

            if (left < size && array[left] > array[max]) {
                max = left;
            }

            if (right < size && array[right] > array[max]) {
                max = right;
            }
            //没找到更大的孩子
            if (max == parent) {
                break;
            }
            swap(array, max, parent);
            parent = max;
        }

    }


    /**
     * 下潜方法 递归实现
     *
     * @param array
     * @param parent
     * @param size
     */
    private static void down1(int[] array, int parent, int size) {
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
            swap(array, max, parent);
            down(array, max, size);
        }

    }

    /**
     * 上浮方法
     *
     * @param array
     * @param value
     */
    private static void up(int[] array, int value) {
        int child = array.length;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (value > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = value;

    }

    /**
     * 交换方法
     *
     * @param array
     * @param i
     * @param j
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
