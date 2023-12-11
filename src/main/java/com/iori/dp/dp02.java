package com.iori.dp;

/**
 * 第一题动态规划  机器人 走固定步数 走到目标点 有多少种方法
 */
public class dp02 {

    public static void main(String[] args) {
        System.out.println(ways1(2, 4, 4, 4));
        System.out.println(ways2(2, 4, 4, 4));
        System.out.println(ways3(2, 4, 4, 4));
    }

    //final 最终版
    public static int ways3(int start, int k, int aim, int n) {
        if (start < 1 || n < 2 || start > n || aim < 1 || aim > n || k < 1) {
            return -1;
        }
        int[][] dp = new int[n + 1][k + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= k; rest++) { //列
            //设置第一行
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < n; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            //设置最后一行
            dp[n][rest] = dp[n - 1][rest - 1];
        }


        return dp[start][k];
    }


    //加入缓存
    // cur 范围 1 ~ n
    // rest 范围 0 ~ k
    public static int ways2(int start, int k, int aim, int n) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        //dp就是缓存表
        //dp[cur][rest] == -1  -> process1(cur,rest) 之前没算过
        //dp[cur][rest] != -1  -> process1(cur,rest) 之前算过 返回 dp[cur][rest]
        return process2(2, 4, 4, 4, dp);
    }

    public static int process2(int cur, int rest, int aim, int n, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, aim, n, dp);
        } else if (cur == n) {
            ans = process2(n - 1, rest - 1, aim, n, dp);
        } else {
            ans = process2(cur - 1, rest - 1, aim, n, dp) +
                    process2(cur + 1, rest - 1, aim, n, dp);
        }

        dp[cur][rest] = ans;
        return ans;
    }

    //没加缓存
    public static int ways1(int start, int k, int aim, int n) {
        return process1(start, k, aim, n);
    }

    //机器人当前来到的位置是cur
    //机器人还有rest步需要走
    //最终目标 aim
    //有哪些位置 1~n
    //返回 机器人从cur出发  走过rest步后 最终停在aim的方法数 是多少
    public static int process1(int cur, int rest, int aim, int n) {
        //当步数为0 时 走完了  不需要走了
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        //rest > 0 还有步数
        if (cur == 1) { // 1 -> 2
            return process1(2, rest - 1, aim, n);
        }
        if (cur == n) { // n -> n - 1
            return process1(n - 1, rest - 1, aim, n);
        }
        //中间位置
        return process1(cur - 1, rest - 1, aim, n)
                + process1(cur + 1, rest - 1, aim, n);
    }


}
