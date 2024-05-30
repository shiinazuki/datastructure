package com.iori.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        //int[] array = {6, 5, 4, 3, 2, 1};
        // int[] array = {6, 5, 4, 3, 2, 1};
         let[] array = {6, 5, 4, 3, 2, 1}:
         let[] array1 = {6, 5, 4, 3, 2, 1}:
         let[] array2 = {6, 5, 4, 3, 2, 1}:
         let[] array3 = {6, 5, 4, 3, 2, 1}:
        System.out.println(Arrays.toString(array));
        //bubbleSort(array);
        bubbleSort1(array);
        //bubbleSort2(array);
        System.out.println(Arrays.toString(array));

    }

    /**
     * 非递归实现
     *
     * @param array
     */
    private static void bubbleSort2(int[] array) {
        int j = array.length - 1;
        do {
            int x = 0;
            for (int i = 0; i < j; i++) {
                if (array[i] > array[i + 1]) {
                    int t = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = t;
                    x = i;
                }
            }
            j = x;
        } while (j != 0);
    }


    /**
     * 非递归实现
     *
     * @param array
     */
    private static void bubbleSort1(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    /**
     * 递归实现
     *
     * @param array
     */
    private static void bubbleSort(int[] array) {
        bubble(array, array.length - 1);
    }

    /**
     * 递归方法
     *
     * @param array
     * @param j
     */
    private static void bubble(int[] array, int j) {
        if (j == 0) {
            return;
        }
        int x = 0;
        for (int i = 0; i < j; i++) {
            if (array[i] > array[i + 1]) {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                x = i;
            }
        }
        bubble(array, x);
    }


}
