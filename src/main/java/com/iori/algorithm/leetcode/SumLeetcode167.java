package com.iori.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class SumLeetcode167 {


    public static void main(String[] args) {
        int[] numbers = {2, 3, 4};
        int target = 6;
        System.out.println(Arrays.toString(twoSum0(numbers, target)));
    }

      /*
        思路
         - 两个指针 i 和 j 分别指向最左侧和最右侧的数字
         - 它俩指向的数字和与 target 相比
            - 小于 target i++ 向右找
            - 大于 target j-- 向左找
            - 等于 target 找到
     */

    //使用双指针
    public static int[] twoSum(int[] numbers, int target) {

        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                break;
            }
        }
        return new int[]{i + 1, j + 1};
    }

    //使用hash表试试
    public static int[] twoSum0(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (map.containsKey(target - number)) {
                return new int[]{map.get(target - number) + 1, i + 1};
            }
            map.put(number, i);
        }
        return new int[]{-1, -1};
    }


}
