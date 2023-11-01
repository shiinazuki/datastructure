package com.iori.algorithm.sort;

import java.util.Arrays;

/**
 * <h3>计数排序（处理 >= 0 的元素）</h3>
 */
public class CountingSortPositive {

    /*
        要点
        1. 找到最大值，创建一个大小为 最大值+1 的 count 数组
        2. count 数组的索引对应原始数组的元素，用来统计该元素的出现次数
        3. 遍历 count 数组，根据 count 数组的索引（即原始数组的元素）以及出现次数，生成排序后内容
            count 数组的索引是：已排序好的

        前提：待排序元素 >=0 且最大值不能太大
     */
    public static void main(String[] args) {
        int[] a = {5, 1, 1, 3, 0};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /*
        {5, 1, 1, 3, 0}  原始数组 a
        [1   2   0   1   0   1] count
         0   1   2   3   4   5
     */
    public static void sort(int[] a) {
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        int[] count = new int[max + 1];
        for (int v : a) { //v 原始数组的元素
            count[v]++;
        }
        System.out.println(Arrays.toString(count));

        int size = 0;
        for (int i = 0; i < count.length; i++) {
            //i 代表原始数组的元素  元素出现的次数
            int num = count[i];
            while (num > 0) {
                a[size++] = i;
                num--;
            }
        }
    }


}
