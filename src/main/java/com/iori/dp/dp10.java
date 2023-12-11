package com.iori.dp;

public class dp10 {


    /*
        题目
        数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡9
        现在有n个人需要喝咖哇，只能用咖啡机来制造咖啡，
        认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
        每个人喝完之后咖峰杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
        洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
        四个参数: ar，n，a，b14 // 假没时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
     */

    public static void main(String[] args) {

    }

    /*------------------------------*/
    //动态规划
    public static int bestTimeDp(int[] drinks, int wash, int air) {
        int maxFree = 0;
        for (int drink : drinks) {
            maxFree = Math.max(maxFree, drink + wash);
        }
        int n = drinks.length;
        int[][] dp = new int[n + 1][n + 1];

        //dp[n][...] = 0
        for (int index = n - 1; index >= 0; index--) {
            for (int free = 0; free <= maxFree; free++) {
                int selfClean1 = Math.max(drinks[index], free);
                if (selfClean1 + wash > maxFree) {
                    continue;
                }

                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);

                int selfClean2 = drinks[index] + air;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);

                dp[index][free] =  Math.min(p1, p2);

            }
        }

        return dp[0][0];
    }


    /*------------------------------*/
    //暴力递归

    //drinks 所有杯子可以开始洗的时间
    //wash 单杯洗干净的时间 (串行)
    //air 挥发干净的时间   (并行)
    //free 洗的机器什么时候可用
    //drinks[index...]变干净 最少的时间点返回
    public static int bestTime(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }
        //index 杯子决定洗
        int selfClean1 = Math.max(drinks[index], free) + wash;
        int restClean1 = bestTime(drinks, wash, air, index + 1, selfClean1);
        int p1 = Math.max(selfClean1, restClean1);

        //index号杯子 决定挥发
        int selfClean2 = drinks[index] + air;
        int restClean2 = bestTime(drinks, wash, air, index + 1, free);
        int p2 = Math.max(selfClean2, restClean2);

        return Math.min(p1, p2);

    }




}
