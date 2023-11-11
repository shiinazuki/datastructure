package com.iori.algorithm.divideandconquer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <h3>快速选择算法 - 分而治之</h3>
 */
public class QuickSelect {


    public static void main(String[] args) {
        int[] array = {6, 5, 1, 2, 4};
        System.out.println(quick(array, 0, array.length - 1, 0)); // 1
        System.out.println(quick(array, 0, array.length - 1, 1)); // 2
        System.out.println(quick(array, 0, array.length - 1, 2)); // 4
        System.out.println(quick(array, 0, array.length - 1, 3)); // 5
        System.out.println(quick(array, 0, array.length - 1, 4)); // 6
    }

    /*
      求排在第 i 名的元素，i 从 0 开始，由小到大排
      6   5   1   2   4
   */
    public static int quick(int[] array, int left, int right, int i) {
        /*
            6   5   1   2   [4]

                    2
            1   2   4   6   5

            1   2   4   6   [5]
                        3
            1   2   4   5   6
         */

        int p = partition(array, left, right);
        if (p == i) {
            return array[p];
        }
        if (i < p) {
            return quick(array, left, p - 1, i);
        } else {
            return quick(array, p + 1, right, i);
        }

    }


    public static int partition(int[] a, int left, int right) {

        int index = ThreadLocalRandom.current().nextInt(right - left  + 1) + left;
        swap(a,index,left);
        int pv = a[left];
        int i = left + 1;
        int j = right;

        while (i <= j) {
            while (i <= j && a[i] < pv) {
                i++;
            }
            while (i <= j && a[j] > pv) {
                j--;
            }
            if (i <= j) {
                swap(a,i,j);
                i++;
                j--;
            }
        }
        swap(a, j, left);

        return j;
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
