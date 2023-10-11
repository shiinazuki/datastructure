package com.iori.algorithm.recursion_single;

import java.util.Arrays;

/**
 * 递归冒泡排序
 */
public class E04BubbleSort {

    public static void sort(int[] array) {
        //int[] array = {100, 50, 6, 49, 974, 845, 21, 4, 5, 1};
        //sort(array);
        bubble(array, array.length - 1);
        System.out.println(Arrays.toString(array));
    }


    /**
     * 递归冒泡排序
     *
     * @param array
     * @param j     未排序区域的右边界
     */
    private static void bubble(int[] array, int j) {
        if (j == 0) {
            return;
        }
        //用来记录最后一次交换的索引 防止不必要的递归
        int x = 0;
        int temp;
        for (int i = 0; i < j; i++) {
            if (array[i] > array[i + 1]) {
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                x = i;
            }
        }
        bubble(array, x);

    }


    public static void bubbleLianXi(int[] array) {

        //使用双层for循环
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = array[j];
                array[j + 1] = array[j];
                array[j] = temp;
            }
        }


    }

    public static void bubbleLianXi(int[] array, int j) {

        //用来记录最后一次交换的索引,防止不必要的递归
        int x = 0;
        int temp;
        for (int i = 0; i < j; i++) {
            if (array[i] > array[i + 1]) {
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                x = i;
            }
        }
        bubbleLianXi(array, x);


    }

    //简单冒泡排序
    private static void simpleBubble(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

    }

}
