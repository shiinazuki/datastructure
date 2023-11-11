package com.iori.algorithm.leetcode;

import java.util.Map;

/**
 * <h3>买卖股票的最佳时机</h3>
 */
public class SharesILeetcode121 {

    public static void main(String[] args) {
        //int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices = {2, 1, 2, 1, 0, 1, 2};
        System.out.println(maxProfit(prices));

    }

    //视频的解法
    public static int maxProfit(int[] prices) {
        int ans = 0;
        int i = 0, j = 1;
        while (j < prices.length) {
            if (prices[j] - prices[i] > 0) {
                ans = Math.max(ans, prices[j] - prices[i]);
                j++;
            } else {
                i = j;
                j++;
            }
        }
        return ans;

    }

    //双指针试试
    public static int maxProfit1(int[] prices) {
        int ans = 0;
        int i = 0, j = 1;
        while (j < prices.length) {
            if (prices[i] > prices[j]) {
                i++;
            } else {
                int t = prices[j] - prices[i];
                if (t > ans) {
                    ans = t;
                }
                j++;
            }
        }

        return ans;
    }


    //双层for
    public static int maxProfit0(int[] prices) {

        int ans = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int t = prices[j] - prices[i];
                if (t > ans) {
                    ans = t;
                }
            }
        }
        return ans;

    }


}
