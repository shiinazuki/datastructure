package com.iori.algorithm.leetcode;

/**
 * <h3>买卖股票的最佳时机 II</h3>
 */
public class SharesIILeetcode122 {

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(maxProfit(prices));
    }




    //双指针 只要后一个数比前一个数大 就加起来
    public static int maxProfit(int[] prices) {
        int ans = 0;
        int i = 0, j = 1;

        while (j < prices.length) {
            if (prices[j] > prices[i]) {
                ans += prices[j] - prices[i];
            }
            i++;
            j++;
        }

        return ans;
    }

    //for循环
    public static int maxProfit0(int[] prices) {
        int ans = 0;

        int i = 0;
        for (int j = i + 1; j < prices.length; j++) {
            if (prices[j] > prices[i]) {
                ans += prices[j] - prices[i];
            }
            i++;
        }
        return ans;
}

}
