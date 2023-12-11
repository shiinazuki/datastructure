package com.iori.dp;

/**
 * 背包问题
 */
public class dp04 {

    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 7};
        int[] values = {6, 5, 3, 19};
        int bag = 11;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(maxValue1(weights, values, bag));
    }

    /*-----------------------------------*/
    //动态规划 二维

    public static int maxValue1(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int n = w.length;
        // index 0 ~ N
        // rest 负 ~ bag
        int[][] dp = new int[n + 1][bag + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[i + 1][rest];
                int p2 = 0;
                int next = rest - w[i] < 0 ? -1 : dp[i + 1][rest - w[i]];
                if (next != -1) {
                    p2 = v[i] + next;
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }

        return dp[0][bag];

    }



    /*-----------------------------------*/
    //暴力递归


    //所有的货 重量和价值 都在w 和 v数组里
    //为了方便 其中没有负数
    //bag背包容量  不能超过这个载重
    //返回: 不超重的情况下 能够得到的最大价值
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        //尝试函数
        return process(w, v, 0, bag);
    }

    //当前考虑倒了index号货物, 从index开始  所有货物都可以自由选择
    //做的选择不能超过背包容量
    //返回最大价值
    public static int process(int[] w, int[] v, int index, int bag) {
        if (bag < 0) {
            return -1;
        }

        if (index == w.length) {
            return 0;
        }

        //index 没到最后  有货
        //bag有空间
        //不要当前的货
        int p1 = process(w, v, index + 1, bag);

        //要当前货
        int p2 = 0;
        int next = process(w, v, index + 1, bag - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }

        return Math.max(p1, p2);
    }


}
