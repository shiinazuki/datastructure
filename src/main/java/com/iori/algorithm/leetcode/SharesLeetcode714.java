package com.iori.algorithm.leetcode;

/**
 * 买卖股票的最佳时机含手续费
 */
public class SharesLeetcode714 {

    public static void main(String[] args) {
        // 两次交易的情况
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2)); // 8
        System.out.println(maxProfit(new int[]{1, 3, 7, 2, 18, 3}, 3)); // 16
        System.out.println(maxProfit(new int[]{2, 1, 4, 4, 2, 3, 2, 5, 1, 2}, 1)); // 4
        System.out.println(maxProfit(new int[]{9, 3, 12, 1, 2, 3}, 1)); // 9

        // 一次交易的情况
        System.out.println(maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3)); // 6
        System.out.println(maxProfit(new int[]{1, 3, 7, 5, 10, 11, 3}, 3)); // 7

    }


    /*
    fee：2
        0       1           2           3           4       5
        1       3           2           8           4       9
 买     -1     等-1 √       等-1 √       等-1 √      -1       等1 √
               买-3         买-2        买-8        买1 √     买-4
 卖     0      等0  √        等0  √      等0          等5 √    等5
               卖0          卖-1         卖5 √        卖1     卖8 √


     */

    // 这种改动虽然业务含义变化了，但对结果不影响, 根据这次 buy 计算 sell
    public static int maxProfit3(int[] prices, int fee) {
        int buy = Integer.MIN_VALUE;
        int sell = 0;
        for (int price : prices) {
            buy = Math.max(buy, sell - price);
            sell = Math.max(sell, buy + price - fee); // 根据这次 buy 计算 sell
        }
        return sell;
    }

    // 这种改动虽然业务含义变化了，但对结果不影响, 根据这次 buy 计算 sell
    public static int maxProfit2(int[] prices, int fee) {
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < prices.length; i++) {
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, buy + prices[i] - fee);
        }
        return sell;
    }


    // 根据上次 buy 计算 sell
    public static int maxProfit1(int[] prices, int fee) {
        int _buy = -prices[0];
        int _sell = 0;
        for (int i = 1; i < prices.length; i++) {
            int buy = Math.max(_buy, _sell - prices[i]);
            int sell = Math.max(_sell, _buy + prices[i] - fee);
            _buy = buy;
            _sell = sell;
        }
        return _sell;
    }

    //视频解法
    public static int maxProfit0(int[] prices, int fee) {
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];

        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }
        return sell[prices.length - 1];
    }


    //贪心  看的题解 自己想不通
    public static int maxProfit(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                ans += prices[i] - buy;
                buy = prices[i];
            }
        }
        return ans;
    }


}
