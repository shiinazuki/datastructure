package com.iori.algorithm.binarysearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 二分查找算法
 */
public class BinarySearchArray {

    public static void main(String[] args) {

        //
        ////声明一个数组
        //int[] array = {15, 89, 32, 475, 54, 185, 874, 31, 685, 4775, 693, 147, 55};
        //
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("请输入要查找的数：");
        //int num = scanner.nextInt();
        ////对数组排序
        //Arrays.sort(array);
        //
        //System.out.println(binarySearchBasic(array, num));
    }


    /**
     * 二分查找基础班  找到返回索引  没找到返回-1
     *
     * @param array
     * @param target
     * @return
     */
    public static int binarySearchBasic(int[] array, int target) {
        //声明指针和设置初值
        int i = 0, j = array.length - 1;

        //范围内还有东西
        while (i <= j) {
            //取中间值  很重要
            //int index = (i + j) / 2; //这个写法有问题  如果加起来大于整数的最大值 会变成负数
            int index = (i + j) >>> 1; //无符号右移运算
            if (target < array[index]) {//目标在左边
                j = index - 1;
            } else if (array[index] < target) { //目标在右边
                i = index + 1;
            } else {
                //找到了 就返回
                return index;
            }
        }
        //没找到 返回-1
        return -1;
    }


    /**
     * 二分查找改动班  找到返回索引  没找到返回-1
     *
     * @param array
     * @param target
     * @return
     */
    public static int binarySearchAlternative(int[] array, int target) {
        //声明指针和设置初值
        int i = 0, j = array.length;

        //范围内还有东西
        while (i < j) {
            //取中间值  很重要
            int index = (i + j) >>> 1; //无符号右移运算
            if (target < array[index]) {//目标在左边
                j = index;
            } else if (target > array[index]) { //目标在右边
                i = index + 1;
            } else {
                //找到了 就返回
                return index;
            }
        }
        //没找到 返回-1
        return -1;
    }

    /**
     * 二分查找改动班  找到返回索引  没找到返回-1
     *
     * @param array
     * @param target
     * @return
     */
    public static int binarySearchBalance(int[] array, int target) {
        //声明指针和设置初值
        int i = 0, j = array.length;

        //范围内还有东西
        while (1 < j - i) {
            //取中间值  很重要
            int index = (i + j) >>> 1; //无符号右移运算
            if (target < array[index]) {//目标在左边
                j = index;
            } else { //目标在右边
                i = index;
            }
        }

        if (array[i] == target) {
            return i;
        } else {
            //没找到 返回-1
            return -1;
        }
    }

    /**
     * 二分查找  找到返回索引  没找到返回-1
     * 如果数组里有重复元素  找最左边的返回
     *
     * @param array
     * @param target
     * @return
     */
    public static int binarySearchLeftmost1(int[] array, int target) {
        //声明指针和设置初值
        int i = 0, j = array.length - 1;
        int candidate = -1;
        //范围内还有东西
        while (i <= j) {
            //取中间值  很重要
            //int index = (i + j) / 2; //这个写法有问题  如果加起来大于整数的最大值 会变成负数
            int index = (i + j) >>> 1; //无符号右移运算
            if (target < array[index]) {//目标在左边
                j = index - 1;
            } else if (array[index] < target) { //目标在右边
                i = index + 1;
            } else {
                //找到了 先不返回  先记录候选位置
                candidate = index;
                j = index - 1;
            }
        }
        //没找到 返回-1
        return candidate;
    }


    /**
     * 二分查找  找到返回索引  没找到返回-1
     * 如果数组里有重复元素  找最右边的返回
     *
     * @param array
     * @param target
     * @return
     */
    public static int binarySearchRightmost1(int[] array, int target) {
        //声明指针和设置初值
        int i = 0, j = array.length - 1;
        int candidate = -1;
        //范围内还有东西
        while (i <= j) {
            //取中间值  很重要
            //int index = (i + j) / 2; //这个写法有问题  如果加起来大于整数的最大值 会变成负数
            int index = (i + j) >>> 1; //无符号右移运算
            if (target < array[index]) {//目标在左边
                j = index - 1;
            } else if (array[index] < target) { //目标在右边
                i = index + 1;
            } else {
                //找到了 先不返回  先记录候选位置
                candidate = index;
                i = index + 1;
            }
        }

        return candidate;
    }

    /**
     * 二分查找  找到返回索引
     * 如果数组里有重复元素  找最左边的返回
     *
     * @param array
     * @param target
     * @return 大于等于目标的最靠左的索引
     */
    public static int binarySearchLeftmost2(int[] array, int target) {
        //声明指针和设置初值
        int i = 0, j = array.length - 1;
        //范围内还有东西
        while (i <= j) {
            //取中间值  很重要
            //int index = (i + j) / 2; //这个写法有问题  如果加起来大于整数的最大值 会变成负数
            int index = (i + j) >>> 1; //无符号右移运算
            if (target <= array[index]) {//目标在左边
                j = index - 1;
            } else { //目标在右边
                i = index + 1;
            }
        }

        return i;
    }


    /**
     * 二分查找  找到返回索引 re
     * 如果数组里有重复元素  找最右边的返回
     *
     * @param array
     * @param target
     * @return 小于等于目标的最靠右的索引
     */
    public static int binarySearchRightmost2(int[] array, int target) {
        //声明指针和设置初值
        int i = 0, j = array.length - 1;
        //范围内还有东西
        while (i <= j) {
            //取中间值  很重要
            //int index = (i + j) / 2; //这个写法有问题  如果加起来大于整数的最大值 会变成负数
            int index = (i + j) >>> 1; //无符号右移运算

            if (target < array[index]) {//目标在左边
                j = index - 1;
            } else {
                //找到了 先不返回  先记录候选位置
                i = index + 1;
            }
        }
        return i - 1;
    }








    public static int search(int[] array, int target) {

        //先定义两个指针
        int i = 0, j = array.length;
        //使用二分查找进行循环查找
        while (1 < j - i) {
            int index = (i + j) >>> 1;
            //如果目标值在左边
            if (target < array[index]) {
                j = index;
            } else { //目标值在右边
                i = index;
            }
        }
        if (array[i] == target) {
            return i;
        }else {
            return -1;
        }
    }


}

