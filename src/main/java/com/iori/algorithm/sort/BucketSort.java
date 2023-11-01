package com.iori.algorithm.sort;

import com.iori.datastructure.array.DynamicArray;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <h3>桶排序</h3>
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] ages = {20, 18, 28, 66, 25, 31, 67, 30}; // 假设人类年龄 1~99
        System.out.println(Arrays.toString(ages));
        sort1(ages);
        System.out.println(Arrays.toString(ages));
    }

    /*
        0
        1   18
        2   20 25 28
        3   31
        4
        5
        6   66 67
        7
        8
        9
     */
    public static void sort(int[] ages) {
        //准备桶
        DynamicArray[] buckets = new DynamicArray[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new DynamicArray();
        }
        //放入年龄数据
        for (int age : ages) {
            buckets[age / 10].addLast(age);
        }
        int k = 0;
        for (DynamicArray bucket : buckets) {
            //排序桶内元素
            int[] array = bucket.array();
            InsertionSort.sort2(array);
            for (int v : array) {
                ages[k++] = v;
            }
        }
    }

    public static void sort1(int[] ages) {
        //准备桶
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        //放入年龄数据
        for (int age : ages) {
            buckets[age / 10].add(age);
        }
        int k = 0;
        for (ArrayList<Integer> bucket : buckets) {
            //排序桶内元素
            bucket.sort(null);

            for (Integer v : bucket) {
                ages[k++] = v;
            }
        }
    }
}
