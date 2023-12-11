package com.iori.dp;

public class dp20 {

    /*
        给定一个正数数组arr,请把arr中 所有的数分成两个集合
        如果arr长度为偶数，两个集合包含数的个数要一样多
        如果arr长度为奇数，两个集合包含数的个数必须只差一个
        请尽量让两个集合的累加和接近
        返回:
        最接近的情况下，较小集合的累加和

     */

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 5, 8, 6, 7, 9, 10};
        System.out.println(right(arr));
        System.out.println(dp(arr));

    }

    //动态规划
    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int len = arr.length;
        int k = (len + 1) / 2;
        sum /= 2;
        int[][][] dp = new int[len + 1][k + 1][sum + 1];

        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= k; j++) {
                for (int l = 0; l <= sum; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }

        for (int rest = 0; rest <= sum; rest++) {
            dp[len][0][rest] = 0;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= k; j++) {
                for (int rest = 0; rest <= sum; rest++) {
                    int p1 = dp[i + 1][j][rest];
                    int p2 = -1;
                    int next = -1;
                    if (j - 1 >= 0 && arr[i] <= rest) {
                        next = dp[i + 1][j - 1][rest - arr[i]];
                    }
                    if (next != -1) {
                        p2 = arr[i] + next;
                    }
                    dp[i][j][rest] = Math.max(p1, p2);

                }
            }
        }

        if ((arr.length & 1) == 0) {
            return dp[0][arr.length / 2][sum];
        } else {
            return Math.max(dp[0][arr.length / 2][sum], dp[0][arr.length / 2 + 1][sum]);
        }


    }


    //暴力递归
    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if ((arr.length & 1) == 0) {
            return process1(arr, 0, arr.length / 2, sum / 2);
        } else {
            return Math.max(process1(arr, 0, arr.length / 2, sum / 2),
                    process1(arr, 0, arr.length / 2 + 1, sum / 2));
        }
    }

    private static int process1(int[] arr, int i, int picks, int rest) {
        if (i == arr.length) {
            return picks == 0 ? 0 : -1;
        } else {
            int p1 = process1(arr, i + 1, picks, rest);
            int p2 = -1;
            int next = -1;
            if (arr[i] <= rest) {
                next = process1(arr, i + 1, picks - 1, rest - arr[i]);
            }
            if (next != -1) {
                p2 = arr[i] + next;
            }
            return Math.max(p1, p2);
        }
    }

}
