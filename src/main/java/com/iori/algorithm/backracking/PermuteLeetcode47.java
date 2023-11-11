package com.iori.algorithm.backracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <h3>全排列 II - 回溯</h3>
 */
public class PermuteLeetcode47 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 3};
        Arrays.sort(nums);
        List<List<Integer>> permute = permuteUnique(nums);
        for (List<Integer> list : permute) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, new boolean[nums.length], new LinkedList<>(), result);
        return result;
    }

    private static void dfs(int[] nums, boolean[] visited, LinkedList<Integer> stack, List<List<Integer>> result) {
        if (stack.size() == nums.length) {
            //System.out.println(stack);
            result.add(new ArrayList<>(stack));
            return;
        }
        //遍历 nums 数组 发现没有被使用的数组 则将其标记为使用 并加入 stack
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) { //找出重复数字
                continue;
            }
            if (!visited[i]) {
                stack.push(nums[i]);
                visited[i] = true;
                dfs(nums, visited, stack, result);
                visited[i] = false;
                stack.pop();
            }
        }
    }


}
