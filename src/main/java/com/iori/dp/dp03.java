package com.iori.dp;

import java.util.Arrays;


public class dp03 {

    /*
        给定一个整型数组arr，代表数值不同的纸牌排成一条线
        玩家A和玩家B依次拿走每张纸牌
        规定玩家A先拿，玩家B后拿
        但是每个玩家每次只能拿走最左或最右的纸牌
        玩家A和玩家B都绝顶聪明
        请返回最后获胜者的分数
     */
    public static void main(String[] args) {
        int[] array = {50, 100, 70, 10};
        System.out.println(win1(array));
        System.out.println(win2(array));
        System.out.println(win3(array));
    }

    /*----------------------------------------------------------------*/
    //动态规划
    public static int win3(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int n = array.length;
        int[][] fdp = new int[n][n];
        int[][] gdp = new int[n][n];
        for (int i = 0; i < n; i++) {
            fdp[i][i] = array[i];
        }
        //按照对角线推
        for (int startCol = 1; startCol < n; startCol++) {
            int left = 0;
            int right = startCol;
            //列越界 循环结束
            while (right < n) {
                fdp[left][right] = Math.max(array[left] + gdp[left + 1][right],
                        array[right] + gdp[left][right - 1]);

                gdp[left][right] = Math.min(fdp[left + 1][right], fdp[left][right] - 1);

                left++;
                right++;
            }
        }
        return Math.max(fdp[0][n - 1], gdp[0][n - 1]);
    }


    /*----------------------------------------------------------------*/
    //加缓存  记忆法优化
    public static int win2(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int n = array.length;
        int[][] fdp = new int[n][n];
        int[][] gdp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(fdp[i], -1);
            Arrays.fill(gdp[i], -1);
        }

        int first = f2(array, 0, array.length - 1, fdp, gdp);
        int second = g2(array, 0, array.length - 1, fdp, gdp);
        return Math.max(first, second);
    }


    private static int f2(int[] array, int left, int right, int[][] fdp, int[][] gdp) {

        if (fdp[left][right] != -1) {
            return fdp[left][right];
        }

        int ans = 0;
        if (left == right) {
            ans = array[left];
        } else {
            int p1 = array[left] + g2(array, left + 1, right, fdp, gdp);
            int p2 = array[right] + g2(array, left, right - 1, fdp, gdp);
            ans = Math.max(p1, p2);
        }
        fdp[left][right] = ans;
        return ans;
    }


    private static int g2(int[] array, int left, int right, int[][] fdp, int[][] gdp) {
        if (gdp[left][right] != -1) {
            return gdp[left][right];
        }
        int ans = 0;
        if (left == right) {
            ans = 0;
        } else {
            int p1 = f2(array, left + 1, right, fdp, gdp);
            int p2 = f2(array, left, right - 1, fdp, gdp);
            ans = Math.min(p1, p2);
        }
        gdp[left][right] = ans;
        return ans;
    }


    /*----------------------------------------------------------------*/
    //暴力递归
    //根据规则 返回获胜者分数
    public static int win1(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int first = f1(array, 0, array.length - 1);
        int second = g1(array, 0, array.length - 1);
        System.out.printf("先手%s  后手%s \n", first, second);
        return Math.max(first, second);
    }

    //在 array[left,right] 先手获得的最好分数 返回
    public static int f1(int[] array, int left, int right) {
        if (left == right) {
            return array[left];
        }

        //对手拿走了L位置的数
        int p1 = array[left] + g1(array, left + 1, right);
        //对手拿走了R位置的数
        int p2 = array[right] + g1(array, left, right - 1);
        return Math.max(p1, p2);

    }

    public static int g1(int[] array, int left, int right) {
        if (left == right) {
            return 0;
        }
        int p1 = f1(array, left + 1, right);
        int p2 = f1(array, left, right - 1);
        return Math.min(p1, p2);
    }

}
