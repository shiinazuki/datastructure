package com.iori.algorithm.dynamicprogramming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * <h3>零钱兑换 - 动态规划</h3>
 * <p>凑成总金额的凑法中，需要硬币最少个数是几？</p>
 */
public class ChangeMakingProblemLeetcode322 {

    /*
  面值    0        1        2        3        4        5
    1    80        1        11       111      1111     11111
    2    0        1        2        21       22       221
    5    0        1        2        21       22       5

  面值    0        1        2        3        4        5
   10    0        max      max      max      max      max

  总金额❤  - 类比为背包容量
  硬币面值  - 类比为物品重量
  硬币个数  - 类比为物品价值，固定为1 （求价值(个数)最小的）

  if(装得下) {
     min(上次价值(个数), 剩余容量能装下的最小价值(个数)+1)
     dp[i][j] = min(dp[i-1][j], dp[i][j-item.weight] + 1)
  } else {
     保留上次价值(个数)不变
     dp[i][j] = dp[i-1][j]
  }
  */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        Arrays.fill(dp,amount + 1);
        dp[0] = 0;
/*        for (int i = 1; i < amount + 1; i++) {
            if (i >= coins[0]) { //装得下
                dp[i] = dp[i - coins[0]] + 1;
            } else {//装不下
                dp[i] = amount + 1;//最大值 就是最大面值 + 1
            }
        }*/

        System.out.println(Arrays.toString(dp));

        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j >= coins[i]) {
                    dp[j] = Integer.min(dp[j],dp[j - coins[i]] + 1);
                }else {
                    dp[j] = dp[j];
                }
            }
        }
        System.out.println(Arrays.toString(dp));

        return dp[amount] <= amount  ? dp[amount] : -1;
    }


    public int coinChange2(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 1; i < amount + 1; i++) {
            if (i >= coins[0]) { //装得下
                dp[0][i] = dp[0][i - coins[0]] + 1;
            } else {//装不下
                dp[0][i] = amount + 1;//最大值 就是最大面值 + 1
            }
        }

        print(dp);

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = Integer.min(dp[i - 1][j],dp[i][j - coins[i]] + 1);
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        print(dp);
        return dp[coins.length - 1][amount] < amount + 1 ? dp[coins.length - 1][amount] : -1;
    }


    public static void main(String[] args) {
        ChangeMakingProblemLeetcode322 leetcode = new ChangeMakingProblemLeetcode322();
        int count = leetcode.coinChange(new int[]{1, 2, 5}, 5);
        //int count = leetcode.coinChange(new int[]{25, 10, 5, 1}, 41);
        //int count = leetcode.coinChange(new int[]{2}, 3);
//        int count = leetcode.coinChange(new int[]{15, 10, 1}, 21);
        System.out.println(count);
    }

    static void print(int[][] dp) {
        System.out.println("-".repeat(18));
        Object[] array = IntStream.range(0, dp[0].length + 1).boxed().toArray();
        System.out.printf(("%2d ".repeat(dp[0].length)) + "%n", array);
        for (int[] d : dp) {
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(("%2d ".repeat(d.length)) + "%n", array);
        }
    }

}
