package com.iori.algorithm.sort.lianxi;


import java.util.Arrays;

/**
 * <h3>归并排序 TopDown</h3>
 */
public class MergeSortToDownLianXi {

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 归并排序
     *
     * @param array
     */
    private static void sort(int[] array) {
        int[] array2 = new int[array.length];
        split(array, 0, array.length - 1, array2);
    }

    /**
     * 递归方法
     *
     * @param array
     * @param left
     * @param right
     */
    private static void split(int[] array, int left, int right, int[] array2) {



    }


    /**
     * 合并方法
     *
     * @param array1 原始数组
     * @param i      第一个有序范围开始
     * @param iend   第一个有序范围结尾
     * @param j      第二个有序范围开始
     * @param jend   第二个有序范围结尾
     * @param array2 临时数组
     */
    private static void merge(int[] array1, int i, int iend, int j, int jend, int[] array2) {
    }


}
