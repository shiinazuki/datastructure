package com.iori.algorithm.sort.lianxi;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSortLianXi {


    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        sort1(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 插入排序
     *
     * @param array
     */
    private static void sort2(int[] array) {

    }

    private static void sort1(int[] array) {
       insertionSort(array,0);
    }

    /**
     * 递归插入排序
     *
     * @param array
     * @param low
     */
    private static void insertionSort(int[] array, int low) {

    }

}
