package com.iori.algorithm.sort.lianxi;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSortLianXi {

    public static void main(String[] args) {
        //int[] array = {6, 5, 4, 3, 2, 1};
        int[] array = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(array));
        bubbleSort1(array);
        System.out.println(Arrays.toString(array));

    }

    /**
     * 非递归实现
     *
     * @param array
     */
    private static void bubbleSort2(int[] array) {

    }


    /**
     * 非递归实现
     *
     * @param array
     */
    private static void bubbleSort1(int[] array) {

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


    }


}
