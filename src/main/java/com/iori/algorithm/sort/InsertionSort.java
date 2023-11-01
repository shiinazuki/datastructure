package com.iori.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSort {


    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        //sort1(a);
        sort2(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 插入排序
     *
     * @param array
     */
    public static void sort2(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            //找到位置了
            if (j + 1 != i) {
                array[j + 1] = temp;
            }
        }
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

        if (low == array.length) {
            return;
        }
        int temp = array[low];
        int i = low - 1; //已排序区域指针

        //没找到插入位置
        while (i >= 0 && array[i] > temp) {
            array[i + 1] = array[i]; //空出插入位置
            i--;
        }
        //找到位置了
        if (i + 1 != low) {
            array[i + 1] = temp;
        }
        insertionSort(array, low + 1);
    }

}
