package com.iori.leetcode.leetcode08;

/**
 * 583. 两个字符串的删除操作
 */
public class Solution583 {

    public static void main(String[] args) {
        String word1 = "sea", word2 = "eat";
        System.out.println(minDistance(word1, word2));
    }

    public static int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i < m + 1; i++) {
            char c = char1[i - 1];
            for (int j = 1; j < n + 1; j++) {
                char y = char2[j - 1];
                if (c == y) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m + n - dp[m][n] - dp[m][n];
    }


}
