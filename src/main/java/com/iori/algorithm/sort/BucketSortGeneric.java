package com.iori.algorithm.sort;

import com.iori.datastructure.array.DynamicArray;

import java.util.Arrays;

/**
 * <h3>桶排序(改进)</h3>
 */
public class BucketSortGeneric {
    public static void main(String[] args) {
        int[] ages = {9, 0, 5, 1, 3, 2, 4, 6, 8, 7};
        System.out.println(Arrays.toString(ages));
        sort(ages, 3);
        System.out.println(Arrays.toString(ages));
    }

    /*
        0   0,1,2
        1   3,4,5
        2   6,7,8
        3   9
     */
    public static void sort(int[] a, int range) {
        //找到最大值与最小值
        int max = a[0];
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        //1.准备桶
        DynamicArray[] buckets = new DynamicArray[(max - min )/ range + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new DynamicArray();
        }
        //2.放入数据
        for (int v : a) {
            buckets[(v - min) / range].addLast(v);
        }

        int k = 0;
        for (DynamicArray bucket : buckets) {
            //排序桶内元素
            int[] array = bucket.array();
            InsertionSort.sort2(array);
            for (int v : array) {
                a[k++] = v;
            }
        }

    }
}
