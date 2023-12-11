package com.iori.dp;

public class dp11 {

    /*
        给定一个二维数组  一个人从左上角出发
        最后到达右下角 沿途只可以向下或者向右走
        沿途的数字都累加就是距离累加和
        返回最小距离累加和
     */

    public static void main(String[] args) {

    }



    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }

        int row = m.length;
        int col = m[0].length;
        int[] dp = new int[col];

        dp[0] = m[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[0] += m[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
            }
        }
        return dp[col - 1];
    }


}
