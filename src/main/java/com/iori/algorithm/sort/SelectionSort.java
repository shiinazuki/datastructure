package com.iori.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 选择排序
     *
     * @param array
     */
    public static void sort(int[] array) {
        // 1. 选择轮数 a.length - 1
        // 2. 交换的索引位置(right) 初始 a.length - 1, 每次递减
        for (int i = array.length - 1; i > 0; i--) {
            int max = i;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[max]) {
                    max = j;
                }
            }
            if (max != i) {
                swap(array, i, max);
            }
        }
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
