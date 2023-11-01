package com.iori.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 希尔排序实现
     *
     * @param array
     */
    private static void sort(int[] array) {
        for (int gap = array.length >> 1; gap >= 1;  gap >>= 1) {
            for (int low = gap; low < array.length; low++) {
                int temp = array[low];
                int j = low - gap;
                // 自右向左找插入位置，如果比待插入元素大，则不断右移，空出插入位置
                while (j >= 0 && array[j] > temp) {
                    array[j + gap] = array[j];
                    j -= gap;
                }
                //找到插入位置
                if (j != low - gap) {
                    array[j + gap] = temp;
                }
            }
        }

    }

}
