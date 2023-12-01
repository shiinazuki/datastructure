package com.iori.datastructure.hashtable;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 * <reversePrint>给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标</reversePrint>
 * <reversePrint>注意：<b>只会存在一个有效答案</b></reversePrint>
 */
public class E01Leetcode1 {

        /*

        [(2,0),(6,1)]
        输入：nums = [2,6,7,x,x,...], target = 9
        输出：[0,1]
        解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]

        输入：nums = [3,2,4], target = 6
        输出：[1,2]

        输入：nums = [3,3], target = 6
        输出：[0,1]

        思路：
        1. 循环遍历数组，拿到每个数字 x
        2. 以 target-x 作为 key 到 hash 表查找
            1）若没找到，将 x 作为 key，它的索引作为 value 放入 hash 表
            2）若找到了，返回 x 和它配对数的索引即可
     */

    public static void main(String[] args) {
        int[] array = {2, 5, 7, 9, 11};
        System.out.println(Arrays.toString(twoSum(array, 12)));
    }

    public static int[] twoSum(int[] nums, int target) {
        /*for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1,-1};*/

        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (result.containsKey(target - nums[i])) {
                return new int[]{result.get(target - nums[i]), i};
            } else {
                result.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

}


