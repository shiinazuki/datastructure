package com.iori.algorithm.exhaustion;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h3>零钱兑换 - 穷举解法</h3>
 * <reversePrint>凑成总金额的凑法中，需要硬币最少个数是几？</reversePrint>
 */
public class ChangeMakingProblemLeetcode322LianXi {

    static int min = -1; // 需要的最少硬币数  2 3

    public static void main(String[] args) {

        ChangeMakingProblemLeetcode322LianXi leetcode = new ChangeMakingProblemLeetcode322LianXi();
        //int count = leetcode.coinChange(new int[]{5, 2, 1}, 5);
        //int count = leetcode.coinChange(new int[]{25, 10, 5, 1}, 41);
//        int count = leetcode.coinChange(new int[]{2}, 3);
        int count = leetcode.coinChange(new int[]{15, 10, 1}, 21);
        System.out.println(count);

    }

    public int coinChange(int[] coins, int amount) {
        rec(0, coins, amount, new AtomicInteger(-1), new LinkedList<>(), true);
        return min;
    }

    // count 代表某一组合 钱币的总数
    public void rec(int index, int[] coins, int remainder, AtomicInteger count, LinkedList<Integer> stack, boolean first) {
        if (!first) {
            stack.push(coins[index]);
        }
        count.incrementAndGet();
        //剩余金额 == 0
        if (remainder == 0) {
            System.out.println(stack);
            if (min == -1) {
                min = count.get();
            } else {
                min = Integer.min(min, count.get());
            }
        }
        //还有剩余金额 递归
        else if (remainder > 0) {
            for (int i = index; i < coins.length; i++) {
                rec(i, coins, remainder - coins[i], count, stack, false);
            }
        }
        if (!stack.isEmpty()) {
            stack.pop();
        }

        count.decrementAndGet();
    }

}