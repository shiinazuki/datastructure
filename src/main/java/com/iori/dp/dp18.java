package com.iori.dp;

public class dp18 {

    /*
        给定一个正数 n 求 n的裂开方法数。
        规定：后面的数不能比前面的数小。
        比如 4 的裂开方法有：① 1 + 1 + 1 + 1；② 1 + 1 + 2；③ 1 + 3；
        ④ 2 + 2；⑤ 4。一共 5 种，所以返回 5。

     */

    public static void main(String[] args) {
        int n = 13;
        System.out.println(ways1(n));
        System.out.println(dp1(n));
        System.out.println(dp2(n));
    }


    //动态规划 优化
    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }


        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                dp[i][j] = dp[i + 1][j];
                dp[i][j] += dp[i][j - i];
            }
        }
        return dp[1][n];
    }


    //动态规划
    public static int dp1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }


        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                int ways = 0;
                for (int k = i; k <= j; k++) {
                    ways += dp[k][j - k];
                }
                dp[i][j] = ways;
            }
        }
        return dp[1][n];
    }

    //暴力递归
    public static int ways1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process1(1, n);
    }

    //上一个拆出来的数是pre
    //还剩rest要去拆
    //返回拆解的方法数
    public static int process1(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        //pre < rest
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += process1(i, rest - i);
        }
        return ways;
    }


}
