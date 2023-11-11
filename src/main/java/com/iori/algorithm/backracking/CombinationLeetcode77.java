package com.iori.algorithm.backracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <h3>组合 回溯</h3>
 */
public class CombinationLeetcode77 {

    public static void main(String[] args) {
        List<List<Integer>> lists = combine(4, 3);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
        /*
            n   数字范围 1 ~ 4
            k   数字个数

            12
            13
            14
            23
            24
            34
         */
    }

    // 此 n 代表数字范围, 1~n
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(1, n, k, new LinkedList<>(), result);
        return result;
    }

    // start 起始处理数字
    public static void dfs(int start, int n, int k,
                           LinkedList<Integer> stack,
                           List<List<Integer>> result) {


        if (stack.size() == k) {
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i <= n; i++) {
            // k - stack.size() 还差几个能凑满
            // n - i + 1 还剩几个备用数字
            if (k - stack.size() > n - i + 1) {
                continue;
            }

            stack.push(i); // i = 1
            dfs(i + 1, n, k, stack, result);
            stack.pop(); // i = 1移除
        }

    }


}
