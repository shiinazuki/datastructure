package com.iori.algorithm.leetcode;

import java.util.*;

/**
 * <h3>四数之和</h3>
 */
public class SumLeetcode18 {

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};

        System.out.println(threeSum(nums,0));
    }

    //双层for + 双指针
    public static List<List<Integer>> threeSum(int[] nums,int target) {
        //先对数组惊醒排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum <  target) {
                        left++;
                    } else if (sum >  target) {
                        right--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }

        return result;
    }


    //暴力四层for
    public static List<List<Integer>> threeSum0(int[] nums) {
        //用来去重
        HashSet<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {

                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == 0) {
                            List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            Collections.sort(list);
                            set.add(list);
                        }
                    }
                }

            }
        }

        return new ArrayList<>(set);
    }

}
