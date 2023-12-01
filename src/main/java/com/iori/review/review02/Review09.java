package com.iori.review.review02;


import java.util.Arrays;

/**
 * 单路递归
 */
public class Review09 {


    public static void main(String[] args) {
        System.out.println(f(5));
        reversePrint("abcdefg", 0);
        System.out.println();
        int target = 3;
        int[] nums = {1, 2, 3, 4, 5, 8, 9};
        System.out.println(binarySearch(nums, target));
        int[] nums2 = {10, 2, 30, 4, 50, 8, 90};
        bubble(nums2);
        System.out.println(Arrays.toString(nums2));
        int[] nums3 = {10, 2, 30, 4, 50, 8, 90};
        insertion(nums3);
        System.out.println(Arrays.toString(nums3));

        System.out.println(josepFu(5, 2) + 1);
    }

    //求一个数的阶乘  递归实现
    public static int f(int x) {

        return 0;
    }

    //反向打印字符串
    public static void reversePrint(String s, int index) {

    }

    //二分查找（单路递归）
    public static int binarySearch(int[] nums, int target) {
        return -1;
    }

    //冒泡排序(单路递归)
    public static void bubble(int[] nums) {

    }

    //插入排序(单路递归)
    public static void insertion(int[] nums) {

    }

    //约瑟夫环
    public static int josepFu(int n, int m) {

        return 0;
    }


}
