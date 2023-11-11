package com.iori.algorithm.leetcode;

import java.util.Arrays;

/**
 * <h3>字符串匹配</h3>
 * 暴力匹配 与 KMP
 */
public class StrStrLeetcode28LianXi {


    public static void main(String[] args) {
        System.out.println(strStr("ababababcabacacababaca", "ababaca"));

        //System.out.println(Arrays.toString(lps("ababaca".toCharArray())));
    }




    public static int strStr(String str1, String str2) {
        char[] origin = str1.toCharArray();
        char[] pattern = str2.toCharArray();

        int i = 0, j = 0;
        while (i < origin.length) {
            for (j = 0; j < pattern.length; j++) {
                if (pattern[j] != origin[j + i]) {
                    break;
                }
            }
            if (j == pattern.length) {
                return i;
            }
            i++;
        }
        return -1;
    }


}
