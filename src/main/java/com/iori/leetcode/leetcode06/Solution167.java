package com.iori.leetcode.leetcode06;

/**
 * 167. 两数之和 II - 输入有序数组
 */
public class Solution167 {

    public static void main(String[] args) {

    }

    public static int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int num = numbers[i] + numbers[j];
            if (num < target) {
                i++;
            } else if (num > target) {
                j--;
            } else {
                return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

}
