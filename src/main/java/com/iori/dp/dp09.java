package com.iori.dp;

public class dp09 {

    /*
        象棋棋盘
        从0,0点开始 马走日 走到目标点
        k步走到目标点的方法总共有多少
     */

    public static void main(String[] args) {

        System.out.println(jump1(7, 7, 6));
        System.out.println(jump2(7, 7, 6));

    }

    /*--------------------------------*/
    //动态规划
    public static int jump2(int a, int b, int k) {

        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    dp[x][y][rest] = ways;
                }
            }
        }


        return dp[0][0][k];

    }


    //拿到 如果越界返回0 不越界返回对应位置的数
    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }



    /*-----------------------------*/
    //暴力递归

    public static int jump1(int a, int b, int k) {
        return process1(0, 0, k, a, b);
    }

    //当前来到的位置是(x,y)
    //还剩下rest步需要跳
    //跳完rest步  正好跳到a,b的方法数是多少
    // 10 * 9
    public static int process1(int x, int y, int rest, int a, int b) {
        //越界
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        //还剩0步
        if (rest == 0) {
            return (x == a && y == b) ? 1 : 0;
        }

        int ways = process1(x + 2, y + 1, rest - 1, a, b);
        ways += process1(x + 1, y + 2, rest - 1, a, b);
        ways += process1(x - 1, y + 2, rest - 1, a, b);
        ways += process1(x - 2, y + 1, rest - 1, a, b);
        ways += process1(x - 2, y - 1, rest - 1, a, b);
        ways += process1(x - 1, y - 2, rest - 1, a, b);
        ways += process1(x + 1, y - 2, rest - 1, a, b);
        ways += process1(x + 2, y - 1, rest - 1, a, b);

        return ways;
    }

}
