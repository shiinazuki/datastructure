package com.iori.datastructure.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 只出现一次的数字
 */
public class E05Leetcode136 {

     /*
        输入：nums = [2,2,1]
        输出：1        1

        输入：nums = [4,1,2,1,2]
        输出：4        4

        思路2
        1. 任何相同的数字异或，结果都是 0
        2. 任何数字与 0 异或，结果是数字本身
     */

    public int singleNumber(int[] nums) {
        //int num = nums[0];
        //for (int i = 1; i < nums.length; i++) {
        //    num = num ^ nums[i];
        //}
        //return num;
        int n = 0;
        for (int num : nums) {
            n ^= num;
        }
        return n;
    }


    /*
           思路1

           1. 准备一个 Set 集合，逐一放入数组元素
           2. 遇到重复的，则删除
           3. 最后留下来的，就是那个没有重复的数字

        */
    public int singleNumber3(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.remove(num);
            } else {
                map.put(num, num);
            }
        }
        Set<Integer> set = map.keySet();
        return set.toArray(new Integer[0])[0];
    }


    /**
     * 自己写写
     * 使用哈希表试试
     */
    public int singleNumber2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.remove(num);
            } else {
                map.put(num, num);
            }
        }
        Set<Integer> set = map.keySet();
        return set.toArray(new Integer[0])[0];
    }

    /**
     * 自己写写
     * 使用数组试试
     */
    public int singleNumber1(int[] nums) {
        int[] array = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    array[i] = 1;
                    array[j] = 1;
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                return nums[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        E05Leetcode136 e06 = new E05Leetcode136();
        System.out.println(e06.singleNumber(new int[]{2, 2, 1}));
        System.out.println(e06.singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

}
