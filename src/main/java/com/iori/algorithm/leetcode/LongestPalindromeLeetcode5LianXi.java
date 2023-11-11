package com.iori.algorithm.leetcode;

/**
 * <h3>最长回文字串</h3>
 */
public class LongestPalindromeLeetcode5LianXi {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("bccbcbabcbafa"));
    }

    private static int left;
    private static int right;

    public static String longestPalindrome(String s) {
        left = 0;
        right = 0;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            extend(chars, i, i);  //一个字符做中心点
            extend(chars, i, i + 1);  //两个字符做中心点
        }
        return new String(chars, left, right - left + 1);

    }

    public static void extend(char[] chars, int i, int j) {
        //是回文 i-- j++
        while (i >= 0 && j < chars.length && chars[i] == chars[j]) {
            i--;
            j++;
        }

        //不是回文
        i++;
        j--;
        if (j - i > right - left) {
            left = i;
            right = j;
        }

    }


}
