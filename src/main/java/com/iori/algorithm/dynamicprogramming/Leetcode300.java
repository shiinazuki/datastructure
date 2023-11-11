package com.iori.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * <h3>最长递增子序列</h3>
 */
public class Leetcode300 {

    public static void main(String[] args) {
        Leetcode300 code = new Leetcode300();
        //System.out.println(code.lengthOfLIS(new int[]{1, 3, 6, 4, 9}));
        System.out.println(code.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
//        System.out.println(code.lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
        //                                            1 3 6 7 9 10  = 6
        //                                            1 3 4 5 6     = 5
//        System.out.println(code.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
//        System.out.println(code.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }


    /*
                      1       2       3       4
              1       3       6       4       9
              1       13      16      14      19
                              136     134     139
                                              169
                                              1369
                                              149
                                              1349
             (1)    (2)      (3)     (3)      (4)
                                              4
       */
    public int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) { //满足了升序条件
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        return max;
    }


}
