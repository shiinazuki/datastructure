package com.iori.algorithm.sort.lianxi;

import java.util.Arrays;

/**
 * <h3>堆排序</h3>
 */
public class HeapSortLianXi {

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

    }

    /**
     * 建堆方法
     * 罗伯特·弗洛伊德建堆法
     *
     * @param array
     */
    private static void heapify(int[] array) {

    }


    /**
     * 下潜方法 递归实现
     *
     * @param array
     * @param parent
     * @param size
     */
    private static void down(int[] array, int parent, int size) {

    }


    /**
     * 下潜方法 非递归实现
     *
     * @param array
     * @param parent
     * @param size
     */
    private static void down1(int[] array, int parent, int size) {


    }

    /**
     * 上浮方法
     *
     * @param array
     * @param value
     */
    private static void up(int[] array, int value) {
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
