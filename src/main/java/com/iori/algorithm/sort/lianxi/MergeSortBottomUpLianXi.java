package com.iori.algorithm.sort.lianxi;


import java.util.Arrays;
import java.util.Map;

/**
 * <h3>归并排序 BottomUp</h3>
 */
public class MergeSortBottomUpLianXi {

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 6, 2, 8, 5, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));


    }

    /**
     * 归并排序 非递归
     *
     * @param array
     */
    private static void sort(int[] array) {
        int len = array.length;
        int[] array2 = new int[array.length];
        for (int width = 1; width < len; width <<= 1) {
            for (int left = 0; left < len; left += 2 * width) {
                int right = Math.min(len - 1, left + 2 * width - 1);
                int mid = Math.min(len - 1, left + width - 1);
                merge(array, left, mid, mid + 1, right, array2);
            }
            System.arraycopy(array2, 0, array, 0, len);
        }

    }


    /*
    a1 原始数组
    i~iEnd 第一个有序范围
    j~jEnd 第二个有序范围
    a2 临时数组
 */
    public static void merge(int[] array1, int i, int iend, int j, int jend, int[] array2) {
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
