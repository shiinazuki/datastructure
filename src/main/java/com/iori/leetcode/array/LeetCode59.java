package com.iori.leetcode.array;

/**
 * 螺旋矩阵II
 * 不用复习
 */
public class LeetCode59 {

    public static void main(String[] args) {

        int[][] nums = generateMatrix0(9);

    }


    public static int[][] generateMatrix1(int n) {

        return new int[n][n];
    }


    //自己看了解题思路写的  奇数的话最后一个没有填充
    public static int[][] generateMatrix0(int n) {
        int[][] nums = new int[n][n];
        int i = 0;
        int j = n - 1;
        int sum = 1;
        while (i < j) {
            //从左到右
            for (int lr = i; lr < j; lr++) {
                nums[i][lr] = sum++;
            }
            //从上到下
            for (int ud = i; ud < j; ud++) {
                nums[ud][j] = sum++;
            }
            //从右到左
            for (int rl = j; rl > i; rl--) {
                nums[j][rl] = sum++;
            }
            //从下到上
            for (int du = j; du > i; du--) {
                nums[du][i] = sum++;
            }

            i++;
            j--;
        }

        //1 0 0 ,3 1 1, 5 2 2, 7 3 3, 9 4 4
        if (n % 2 != 0) {
            nums[i][j] = sum;
        }
        return new int[1][1];
    }

}
