package com.iori.dp;

public class dp07 {

    /*
        最长公共子序列
        给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。
        如果不存在 公共子序列 ，返回 0 。

        例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
        两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     */


    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
        System.out.println(longestCommonSubsequence(text1, text2));
        System.out.println(longestCommonSubsequence1(text1, text2));
    }


    public static int longestCommonSubsequence1(String text1, String text2) {
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int n = chars1.length;
        int m = chars2.length;
        int[][] dp = new int[n][m];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[0][i] = chars1[0] == chars2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = chars1[i] == chars2[j] ? 1 + dp[i - 1][j - 1] : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[n - 1][m - 1];
    }


    /*----------------------*/
    //暴力递归
    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null
                || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        return process1(chars1, chars2, chars1.length - 1, chars2.length - 1);
    }

    public static int process1(char[] chars1, char[] chars2, int i, int j) {
        if (i == 0 && j == 0) {
            return chars1[i] == chars2[j] ? 1 : 0;
        } else if (i == 0) {
            if (chars1[i] == chars2[j]) {
                return 1;
            } else {
                return process1(chars1, chars2, i, j - 1);
            }
        } else if (j == 0) {
            if (chars1[i] == chars2[j]) {
                return 1;
            } else {
                return process1(chars1, chars2, i - 1, j);
            }
        } else { //都不等于0
            int p1 = process1(chars1, chars2, i - 1, j);
            int p2 = process1(chars1, chars2, i, j - 1);
            int p3 = chars1[i] == chars2[j] ?
                    (1 + process1(chars1, chars2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }


    }

}
