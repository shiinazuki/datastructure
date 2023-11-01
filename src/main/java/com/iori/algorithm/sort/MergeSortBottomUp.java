package com.iori.algorithm.sort;


import java.util.Arrays;

/**
 * <h3>归并排序 BottomUp</h3>
 */
public class MergeSortBottomUp {

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
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
        int n = array.length;
        int[] array2 = new int[n];

        //width代表有序区间的宽度,取值依次为 1, 2, 4 ...
        for (int width = 1; width < n; width <<= 1) {
            //[left,right] 分别代表带合并区间的左右边界
            for (int left = 0; left < n; left += 2 * width) {
                //在数组长度- 1 和最后一个左边界减1的位置取一个最小值
                int right = Math.min(left + 2 * width - 1, n - 1);
                //System.out.printf("width %d [%d,%d]%n", width, left, right);
                int mid = Math.min(left + width - 1, n - 1);
                merge(array, left, mid, mid + 1, right, array2);
            }
            System.arraycopy(array2, 0, array, 0, n);
        }

    }


    /*
    a1 原始数组
    i~iEnd 第一个有序范围
    j~jEnd 第二个有序范围
    a2 临时数组
 */
    public static void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = i;
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }
    }


}
