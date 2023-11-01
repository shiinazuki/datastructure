package com.iori.algorithm.exhaustion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * <h3>零钱兑换 II - 穷举解法</h3>
 * <p>凑成总金额有几种凑法?</p>
 */
public class ChangeMakingProblemLeetcode518LianXi {


    public int coinChange(int[] coins, int amount) {
        return rec(0, coins, amount, new LinkedList<>(), true);
    }

    /**
     * 求凑成剩余金额的解的个数
     *
     * @param index     -当前硬币索引
     * @param coins     -硬币面值数组
     * @param remainder -剩余金额
     * @param stack
     * @param first
     * @return
     */
    public int rec(int index, int[] coins, int remainder, LinkedList<Integer> stack, boolean first) {
        if (!first) {
            stack.push(coins[index]);
        }
        int count = 0;
        //情况1 剩余金额 < 0 无解
        if (remainder < 0) {
            print("无解:", stack);

        }
        //情况2 剩余金额 == 0 有解
        else if (remainder == 0) {
            print("有解:", stack);
            count = 1;
        }
        //剩余金额大于0 继续递归
        else {

            for (int i = index; i < coins.length; i++) {
                count += rec(i, coins, remainder - coins[i], stack, false);
            }

        }
        if (!stack.isEmpty()) {
            stack.pop();
        }
        return count;

    }

    private static void print(String prompt, LinkedList<Integer> stack) {
        ArrayList<Integer> print = new ArrayList<>();
        ListIterator<Integer> iterator = stack.listIterator(stack.size());
        while (iterator.hasPrevious()) {
            print.add(iterator.previous());
        }
        System.out.println(prompt + print);
    }


    public static void main(String[] args) {
        ChangeMakingProblemLeetcode518LianXi leetcode = new ChangeMakingProblemLeetcode518LianXi();
//        int count = leetcode.coinChange(new int[]{1, 5, 10, 25}, 41);
        int count = leetcode.coinChange(new int[]{5, 2, 1}, 5);
        //int count = leetcode.coinChange(new int[]{1, 2, 5}, 5);
        System.out.println(count);
    }


       /*
由小到大递归过程
rec(1,5)
    rec(1,4)
    | rec(1,3)
    | | rec(1,2)
    | | | rec(1,1)
    | | | | rec(1,0)
    | | | | rec(2,-1)
    | | | | rec(5,-4)
    | | | rec(2,0)
    | | | rec(5,-3)
    | | rec(2,1)
    | | | rec(2,-1)
    | | | rec(5,-4)
    | | rec(5,-2)
    | rec(2,2)
    | | rec(2,0)
    | | rec(5,-3)
    | rec(5,-1)
    rec(2,3)
    | rec(2,1)
    | | rec(2,-1)
    | | rec(5,-4)
    | rec(5,-2)
    rec(5,0)

由大到小递归过程
rec(5,5)
    rec(5,0)
    rec(2,3)
    | rec(2,1)
    | | rec(2,-1)
    | | rec(1,0)
    | rec(1,2)
    | | rec(1,1)
    | | | rec(1,0)
    rec(1,4)
    | rec(1,3)
    | | rec(1,2)
    | | | rec(1,1)
    | | | | rec(1,0)
     */

}
