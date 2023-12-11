package com.iori.dp;

public class dp19 {

    /*
        给定一个整数数组
        请把数组中所有的分数分成两个集合  计量让两个集合的累加和接近
        返回:  最接近的情况下 较小集合的累加和
     */

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 5, 8, 6, 7, 9};
        System.out.println(right(arr));
        System.out.println(dp(arr));

    }

    //动态规划
    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int len = arr.length;
        sum /= 2;
        int[][] dp = new int[len + 1][sum + 1];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= sum; j++) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                if (arr[i] <= j) {
                    p2 = arr[i] + dp[i + 1][j - arr[i]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }

        return dp[0][sum];
    }


    //暴力递归
    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return process1(arr, 0, sum / 2);
    }

    private static int process1(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return 0;
        } else {
            int p1 = process1(arr, i + 1, rest);
            int p2 = 0;
            if (arr[i] <= rest) {
                p2 = arr[i] + process1(arr, i + 1, rest - arr[i]);
            }
            return Math.max(p1, p2);
        }
    }

}
