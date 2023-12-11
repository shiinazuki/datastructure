package com.iori.dp;

public class dp13 {


    /*
        arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
        每个值都认为是一种面值，且认为张数是无限的。
        返回组成aim的方法数
        例如: arr={1.2}, aim= 4
        方法如下: 1+1+1+1. 1+1+2. 2+2
        共就3种方法，所以返回3

     */
    public static void main(String[] args) {
        int[] arr = {1, 2};
        int aim = 4;
        System.out.println(process(arr, 0, aim));
        System.out.println(dp(arr, aim));
        System.out.println(dp2(arr, aim));
    }

    //动态规划 两层for  优化
    private static int dp2(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];

        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];

                if (rest - arr[i] >= 0) {
                    dp[i][rest] += dp[i][rest - arr[i]];
                }
            }
        }

        return dp[0][aim];
    }


    //动态规划 三层for
    private static int dp(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];

        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int k = 0; k * arr[i] <= rest; k++) {
                    ways += dp[i + 1][rest - (k * arr[i])];
                }
                dp[i][rest] = ways;
            }
        }

        return dp[0][aim];
    }

    //暴力递归
    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int i = 0; i * arr[index] <= rest; i++) {
            ways += process(arr, index + 1, rest - (i * arr[index]));
        }
        return ways;
    }


}
