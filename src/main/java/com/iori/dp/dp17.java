package com.iori.dp;

public class dp17 {

    /*
        arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
        每个值都认为是一种面值，且认为张数是无限的。
        返回组成aim的最少货币数

     */

    public static void main(String[] args) {
        int[] arr = {1, 5, 10};
        int aim = 1000;
        System.out.println(minCoins(arr, aim));
        System.out.println(dp(arr, aim));
        System.out.println(dp1(arr, aim));
    }

    //动态规划 优化
    public static int dp1(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != Integer.MAX_VALUE) {
                    dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - arr[i]] + 1);
                }
            }
        }
        return dp[0][aim];
    }


    //动态规划
    public static int dp(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int z = 0; z * arr[i] <= rest; z++) {
                    int next = dp[i + 1][rest - z * arr[i]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, z + next);
                    }
                }
                dp[i][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    //暴力递归
    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i * arr[index] <= rest; i++) {
                int next = process(arr, index + 1, rest - i * arr[index]);
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, next + i);
                }
            }
            return ans;
        }
    }

}
