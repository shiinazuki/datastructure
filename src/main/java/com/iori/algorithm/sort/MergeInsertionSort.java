package com.iori.algorithm.sort;

import java.util.Arrays;

/**
 * <h3>归并排序 + 插入排序</h3>
 */
public class MergeInsertionSort {

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 归并排序 + 插入排序
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
        //System.out.println(Arrays.toString(Arrays.copyOfRange(array, left, right + 1)));
        //2. 治
        if (right - left <= 32) {
            //插入排序
            insertion(array, left, right);
            return;
        }
        //1. 分
        int mid = (left + right) >>> 1;
        split(array, left, mid, array2);
        split(array, mid + 1, right, array2);
        //3. 合
        merge(array, left, mid, mid + 1, right, array2);
        System.arraycopy(array2, left, array, left, right - left + 1);

    }

    /**
     * 插入排序
     *
     * @param array
     */
    public static void insertion(int[] array, int left, int right) {
        for (int low = left + 1; low <= right; low++) {
            int temp = array[low];
            int j = low - 1;
            while (j >= left && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            if (j != low - 1) {
                array[j + 1] = temp;
            }
        }
    }

    /**
     * 合并方法
     *
     * @param array1 原始数组
     * @param i      第一个有序范围开始
     * @param iend   第一个有序范围结尾
     * @param j      临时数组
     * @param jend   第二个有序范围开始
     * @param array2 第二个有序范围结尾
     */
    private static void merge(int[] array1, int i, int iend, int j, int jend, int[] array2) {
        int k = i;
        while (i <= iend && j <= jend) {
            if (array1[i] < array1[j]) {
                array2[k] = array1[i];
                i++;
            } else {
                array2[k] = array1[j];
                j++;
            }
            k++;
        }
        if (i > iend) {
            System.arraycopy(array1, j, array2, k, jend - j + 1);
        }
        if (j > jend) {
            System.arraycopy(array1, i, array2, k, iend - i + 1);
        }

    }


}
