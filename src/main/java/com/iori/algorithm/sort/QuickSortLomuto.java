package com.iori.algorithm.sort;

import java.util.Arrays;

/**
 * <h3>单边循环快排（lomuto 洛穆托分区方案）</h3>
 * <reversePrint>核心思想：每轮找到一个基准点元素，把比它小的放到它左边，比它大的放到它右边，这称为分区</reversePrint>
 * <ol>
 * <li>选择最右元素作为基准点元素</li>
 * <li>j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换</li>
 * <ul>
 * <li>交换时机：j 找到小的，且与 i 不相等</li>
 * <li>i 找到 >= 基准点元素后，不应自增</li>
 * </ul>
 * <li>最后基准点与 i 交换，i 即为基准点最终索引</li>
 * </ol>
 */
public class QuickSortLomuto {

    public static void main(String[] args) {
        int[] a = {5, 3, 7, 2, 9, 8, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    private static void sort(int[] array) {
        quick(array, 0, array.length - 1);
    }

    /**
     * 递归方法
     *
     * @param array
     * @param left
     * @param right
     */
    private static void quick(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        //p代表基准点元素索引
        int p = partition(array, left, right);
        quick(array, left, p - 1);
        quick(array, p + 1, right);
    }

    private static int partition(int[] array, int left, int right) {
        //基准点元素值
        int pv = array[right];
        int i = left;
        int j = left;
        while (j < right) {
            //j找到比基准点小的了
            if (array[j] < pv) {
                //并且i 与 j不相等
                if (i != j) {
                    swap(array, i, j);
                }
                i++;
            }
            j++;
        }
        swap(array,i,right);
        return i;
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
