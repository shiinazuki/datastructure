package com.iori.algorithm.leetcode;

/**
 * <h3>最长回文字串</h3>
 */
public class LongestPalindromeLeetcode5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("bccbcbabcbafa"));
    }


    private static int left; //i;
    private static int right; //j

    //跟着视频敲了一遍  代码运作流程看明白了 自己写就废了
    public static String longestPalindrome(String s) {
        left = 0;
        right = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            extend(chars, i, i); //一个字符作为中心点
            extend(chars, i, i + 1); //两个字符作为中心点
        }

        return new String(chars,left,right - left + 1);
    }

    public static void extend(char[] chars, int i, int j) {
        //如果是回文,就扩大回文范围
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
