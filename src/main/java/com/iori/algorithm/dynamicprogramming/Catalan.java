package com.iori.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 卡特兰数
 */
public class Catalan {

    public static void main(String[] args) {
        System.out.println(catalan(3));
    }

    private static int catalan(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int j = 2; j < n + 1; j++) {
            for (int i = 0; i < j; i++) { //第 j 个卡特兰数 的拆分
                //System.out.printf("(%d,%d)\t", i, j - 1 - i);
                dp[j] += dp[i] * dp[j - 1 - i];
            }
            //System.out.println(Arrays.toString(dp));
        }

        return dp[n];
    }

}
