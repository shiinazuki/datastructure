package com.iori.algorithm.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <h3>双边循环快排 - 处理相等元素 + 随机 + 插入排序</h3>
 * 优化后的
 */
public class QuickSortHandleDuplicate {

    public static void main(String[] args) {
//        int[] a = {4, 2, 1, 3, 2, 4}; // 最外层循环 = 要加
//        int[] a = {2, 1, 3, 2}; // 内层循环 = 要加
        int[] a = {2, 1, 3, 2}; // 内层if要加
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int[] a) {
        quick(a, 0, a.length - 1);
    }

    private static void quick(int[] a, int left, int right) {
        //如果 right - left <= 32个元素 使用插入排序
        if (left - right <= 32) {
            insertion(a);
            return;
        }
        int p = partition(a, left, right);
        quick(a, left, p - 1);
        quick(a, p + 1, right);
    }

    /*
        循环内
            i 从 left + 1 开始，从左向右找大的或相等的
            j 从 right 开始，从右向左找小的或相等的
            交换，i++ j--

        循环外 j 和 基准点交换，j 即为分区位置
     */
    private static int partition(int[] a, int left, int right) {
        int index = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(a, index, left);
        int pv = a[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {

            //找比基准点大的
            while ((i <= j && a[i] < pv)) {
                i++;
            }

            //找比基准点小的
            while (i <= j && a[j] > pv) {
                j--;
            }

            if (i <= j) {
                //交换
                swap(a, i, j);
                i++;
                j--;
            }
        }
        swap(a, j, left);
        return j;

    }

    public static void insertion(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            if (j + 1 != i) {
                array[j + 1] = temp;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


}
