package com.iori.algorithm.backracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <h3>组合总和 回溯</h3>
 */
public class CombinationLeetcode39 {


    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }

//        System.out.println(combinationSum(new int[]{1, 2, 5}, 5).size()); //4
//        System.out.println(combinationSum(new int[]{15, 10, 1}, 21).size()); //4
//        System.out.println(combinationSum(new int[]{25, 10, 5, 1}, 41).size()); //31
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, candidates, target, new LinkedList<>(), result);
        return result;
    }


    public static void dfs(int start, int[] candidates, int target, LinkedList<Integer> stack, List<List<Integer>> result) {

    //加了剪枝 可以去掉
  /*      if (target < 0) {
            return;
        }*/
        if (target == 0) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];

            //剪枝
            if (target < candidate) {
                continue;
            }

            stack.push(candidate);
            dfs(i, candidates, target - candidate, stack, result);
            stack.pop();
        }
    }

}
