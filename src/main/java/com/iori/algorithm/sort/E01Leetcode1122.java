package com.iori.algorithm.sort;


import java.util.Arrays;

/*
    根据另一个数组次序排序 前提
    1. 元素值均 >= 0  <=1000
    2. 两个数组长度 <= 1000
 */
public class E01Leetcode1122 {


    public static void main(String[] args) {
        int[] arr1 = {3, 2, 1, 2, 2, 1, 2, 5, 4};
        int[] arr2 = {2, 3, 1};

        E01Leetcode1122 leetcode = new E01Leetcode1122();
        int[] result = leetcode.relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(result));
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        return  relative(arr1,arr2);
    }

    public int[] relative(int[] arr1,int[] arr2) {
        //先用计数排序 统计arr1中元素出现次数
        int max = arr1[0];
        for (int i = 0; i < arr1.length;i++) {
            if (arr1[i] > max) {
                max = arr1[i];
            }
        }
        //创建数组
        int[] count = new int[max + 1];
        //开始计数
        for(int v : arr1) {
            count[v]++;
        }
        //开始遍历排序
        int k = 0;
        for (int i = 0;i < arr2.length;i++) {
            int temp = arr2[i];
            int num = count[temp];
            while (num > 0) {
                arr1[k++] = temp;
                num--;
            }
            count[temp] = 0;
        }

        for (int i = 0;i < count.length;i++) {
            int num = count[i];
            while(num > 0) {
                arr1[k++] = i;
                num--;
            }
        }
        return arr1;

    }
}
