package com.iori.dp;

public class dp16 {

    /*
        给定3个参数 n m k
        怪兽有n滴血 等着英雄来砍
        英雄每一次打击 都会让怪兽流失[0 ~ M]的血量
        到底流失多少 每一次在[0~m]上等概率的获得一个值
        求k次打击后 英雄把怪兽砍死的概率
     */
    public static void main(String[] args) {
        System.out.println(right1(10, 4, 6));
        System.out.println(dp1(10, 4, 6));
        System.out.println(dp2(10, 4, 6));
    }

    //动态规划 优化
    public static double dp2(int n, int m, int k) {
        long all = (long) Math.pow(m + 1, k);

        long[][] dp = new long[k + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            dp[i][0] = (long) Math.pow(m + 1, i);
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                if (j - 1 - m >= 0) {
                    dp[i][j] -= dp[i - 1][j - 1 - m];
                }else {
                    dp[i][j] -=(long) Math.pow(m + 1, i - 1);
                }

            }
        }
        long kill = dp[k][n];
        return (double) kill / (double) (all);
    }

    //动态规划
    public static double dp1(int n, int m, int k) {
        long all = (long) Math.pow(m + 1, k);

        long[][] dp = new long[k + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            dp[i][0] = (long) Math.pow(m + 1, i);
            for (int j = 1; j <= n; j++) {
                long ways = 0;
                for (int z = 0; z <= m; z++) {
                    if (j - z >= 0) {
                        ways += dp[i - 1][j - z];
                    } else {
                        ways += (long) Math.pow(m + 1, i - 1);
                    }
                }
                dp[i][j] = ways;
            }
        }
        long kill = dp[k][n];
        return (double) kill / (double) (all);
    }


    //暴力递归
    public static double right1(int times, int m, int hp) {
        long all = (long) Math.pow(m + 1, hp);
        long kill = process1(times, m, hp);
        return (double) kill / (double) (all);
    }

    public static long process1(int times, int m, int hp) {
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            return (long) Math.pow(m + 1, hp);
        }

        long ways = 0;
        for (int i = 0; i <= m; i++) {
            ways += process1(times - i, m, hp - 1);
        }
        return ways;
    }

}
