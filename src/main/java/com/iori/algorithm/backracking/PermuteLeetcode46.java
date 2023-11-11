package com.iori.algorithm.backracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <h3>全排列 - 回溯</h3>
 */
public class PermuteLeetcode46 {

    public static void main(String[] args) {
        List<List<Integer>> permute = permute1(new int[]{1, 2, 3});
        for (List<Integer> list : permute) {
            System.out.println(list);
        }
    }


    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, new boolean[nums.length], new LinkedList<>(), result);

        return result;

    }

    public static void dfs(int[] nums, boolean[] visited, LinkedList<Integer> stack, List<List<Integer>> result) {
        if (stack.size() == nums.length) {
            //System.out.println(stack);
            result.add(new ArrayList<>(stack));
            return;
        }
        //遍历 nums 数组 发现没有被使用的数组 则将其标记为使用 并加入 stack
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                stack.push(nums[i]);
                visited[i] = true;
                dfs(nums, visited, stack, result);
                visited[i] = false;
                stack.pop();
            }
        }
    }

    //暴力解法 三层for
    public static List<List<Integer>> permute1(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> newList = new ArrayList<>();
            for (List<Integer> value : result) {
                for (int j = 0; j <= value.size(); j++) {
                    List<Integer> list = new ArrayList<>(value);
                    list.add(j,nums[i]);
                    newList.add(list);
                }
            }
            result = newList;
        }
        return result;
    }


    public static List<List<Integer>> permute0(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> newResult = new ArrayList<>();
            for (List<Integer> perm : result) {
                for (int i = 0; i <= perm.size(); i++) {
                    List<Integer> newPerm = new ArrayList<>(perm);
                    newPerm.add(i, num);
                    newResult.add(newPerm);
                }
            }
            result = newResult;
        }
        return result;
    }

}
