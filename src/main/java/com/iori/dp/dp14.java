package com.iori.dp;

import java.util.HashMap;
import java.util.Map;

public class dp14 {

    /*
    arr是货币数组，其中的值都是正数。再给定一个正数aim。
    每个值都认为是一种货币，
    认为值相同的货币没有任何不同
    返回组成aim的方法数
    例如: arr={1,2,1,1,2,1,2}, aim= 4
    方法如下: 1+1+1+1. 1+1+2. 2+2
    共就3种方法，所以返回3
 */
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 1, 2, 1, 2};
        int aim = 4;
        System.out.println(coinsWay(arr, aim));
        System.out.println(dp(arr, aim));
        System.out.println(dp2(arr, aim));

    }

    private static int dp2(int[] arr, int aim) {
        return 0;
    }

    //动态规划
    private static int dp(int[] arr, int aim) {
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int n = coins.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - coins[i] >= 0) {
                    dp[i][rest] += dp[i][rest - coins[i]];
                }
                if (rest - coins[i] * (zhangs[i] + 1) >= 0) {
                    dp[i][rest] =+ dp[i+ 1][rest - coins[i] * (zhangs[i] + 1)];
                }
            }
        }
        return dp[0][aim];
    }

    //暴力递归
    private static int coinsWay(int[] arr, int aim) {
        Info info = getInfo(arr);
        return process(info.coins, info.zhangs, 0, aim);
    }


    private static int process(int[] coins, int[] zhangs, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
            ways += process(coins, zhangs, index + 1, rest - (zhang * coins[index]));
        }
        return ways;
    }

    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] coins, int[] zhangs) {
            this.coins = coins;
            this.zhangs = zhangs;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int i : arr) {
            counts.compute(i, (key, value) -> value == null ? 1 : value + 1);
        }
        int n = counts.size();
        int[] coins = new int[n];
        int[] zhangs = new int[n];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }

}
