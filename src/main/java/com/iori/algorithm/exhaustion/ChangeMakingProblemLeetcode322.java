package com.iori.algorithm.exhaustion;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h3>零钱兑换 - 穷举解法</h3>
 * <p>凑成总金额的凑法中，需要硬币最少个数是几？</p>
 */
public class ChangeMakingProblemLeetcode322 {

    static int min = -1; // 需要的最少硬币数  2 3

    public static void main(String[] args) {

        ChangeMakingProblemLeetcode322 leetcode = new ChangeMakingProblemLeetcode322();
        int count = leetcode.coinChange(new int[]{5, 2, 1}, 5);
//        int count = leetcode.coinChange(new int[]{25, 10, 5, 1}, 41);
//        int count = leetcode.coinChange(new int[]{2}, 3);
//        int count = leetcode.coinChange(new int[]{15, 10, 1}, 21);
        System.out.println(count);

    }

    public int coinChange(int[] coins, int amount) {
       rec(0,coins,amount,new AtomicInteger(-1),new LinkedList<>(),true);
       return min;
    }

    // count 代表某一组合 钱币的总数
    public void rec(int index, int[] coins, int remainder, AtomicInteger count, LinkedList<Integer> stack,boolean first) {
        if (!first) {
            stack.push(coins[index]);
        }
        count.incrementAndGet();
        //情况1:剩余金额 == 0 有一个解
         if (remainder == 0) {
             System.out.println(stack);
          if (min == -1) {
              min = count.get();
          }else {
              min = Integer.min(min,count.get());
          }
        }
        //情况2: 剩余金额 > 0 继续递归
        else if (remainder > 0){
            for (int i = index; i < coins.length; i++) {
             rec(i,coins,remainder - coins[i],count,stack,false);
            }
        }
        count.decrementAndGet();
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

}