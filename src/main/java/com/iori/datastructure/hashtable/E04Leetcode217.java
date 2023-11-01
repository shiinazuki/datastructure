package com.iori.datastructure.hashtable;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 存在重复元素
 */
public class E04Leetcode217 {


    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(containsDuplicate1(nums));
        System.out.println(containsDuplicate2(nums));
    }

    /**
     * 使用哈希表
     */
    public  static boolean containsDuplicate2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(Integer key : nums) {
            if (set.contains(key)) {
                return true;
            }
            set.add(key);
        }
        return false;
    }

    /**
     * 自己写写  暴力破解
     * 超出时间限制
     */
    public static boolean containsDuplicate1(int[] nums) {
        for (int i = 0;i< nums.length;i++) {
            for (int j = i + 1;j < nums.length;j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

}
