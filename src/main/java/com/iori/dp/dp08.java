package com.iori.dp;

import java.util.Objects;

public class dp08 {


    /*
        最长回文子序列
        可以将字符串反转 和原来的字符串求最长公共子序列 解法一
        这里使用了另一种解法
     */

    public static void main(String[] args) {
        String str = "a1b2c3d4e4d3c2b1a";
        System.out.println(lpsl(str));
        System.out.println(lpsl1(str));
    }


    /*-------------------------------------*/
    //动态规划 二维数组
    public static int lpsl1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int n = chars.length;

        int[][] dp = new int[n][n];
        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = chars[i] == chars[i + 1] ? 2 : 1;
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {

                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (chars[i] == chars[j]) {
                    dp[i][j] = Math.max(dp[i][j] ,2 + dp[i + 1][j - 1]);
                }

                //下面代码可以优化成 上面代码

          /*      int p1 = dp[i + 1][j - 1];
                int p2 = dp[i][j - 1];
                int p3 = dp[i + 1][j];
                int p4 = chars[i] == chars[j] ? 2 + dp[i + 1][j - 1] : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));*/
            }
        }
        return dp[0][n - 1];

    }



    /*-----------------------------------*/
    //暴力递归

    public static int lpsl(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        return f(chars, 0, chars.length - 1);
    }

    //str[left...right] 最长回文子序列长度返回
    public static int f(char[] chars, int left, int right) {
        if (left == right) {
            return 1;
        }
        if (left == right - 1) {
            return chars[left] == chars[right] ? 2 : 1;
        }

        int p1 = f(chars, left + 1, right - 1);
        int p2 = f(chars, left, right - 1);
        int p3 = f(chars, left + 1, right);
        int p4 = chars[left] == chars[right] ? 2 + f(chars, left + 1, right - 1) : 0;

        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


}
