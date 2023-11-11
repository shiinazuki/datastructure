package com.iori.algorithm.leetcode;

import java.util.Arrays;

/**
 * <h3>字符串匹配</h3>
 * 暴力匹配 与 KMP
 */
public class StrStrLeetcode28 {


    public static void main(String[] args) {
        System.out.println(strStr("ababababcabacacababaca", "ababaca"));

        System.out.println(Arrays.toString(lps("ababaca".toCharArray())));
    }


    //KMP
    public static int strStr(String str1, String str2) {

        char[] origin = str1.toCharArray();   //原始
        char[] pattern = str2.toCharArray();  //模式
        int[] lps = lps(pattern);   //最长前后缀数组

        /*
            1.匹配成功:i++ j++,直到 j==模式字符串长度
            2.匹配失败
                j == 0 则 i++
                j != 0 跳过最长前后缀字符 继续匹配

         */

        int i = 0, j = 0;
        //如果模式字符串的长度 - j  小于等于 原始字符串的长度 - i
        //说明已经超出长度  没有必要在对比了
        while (pattern.length - j <= origin.length - i) {
            //匹配成功 i++ j++ 移动指针 去匹配下一对字符
            if (origin[i] == pattern[j]) {
                i++;
                j++;
            }
            //当j == 0时  i++ 去对比下一组字符
            else if (j == 0) {
                i++;
            } else {
                j = lps[j - 1];
            }
            //如果 j == 模式字符串的长度 说明找到解了
            if (j == pattern.length) {
                //i - j 就是模式字符串开始的索引位置
                return i - j;
            }
        }
        return -1;

    }

    /*
        最长前后缀数组：只跟模式字符串相关
        1. 索引：使用了模式字符串前 j 个字符串 - 1
        2. 值：最长前后缀的长度（恰好是j要跳转的位置）
     */
    public static int[] lps(char[] pattern) {

        //return new int[]{0, 0, 1, 2, 3, 0, 1};

        int[] lps = new int[pattern.length];
        int i = 1, j = 0;
        while (i < pattern.length) {
            /*
                遇到相同字符:
                    记录共用前后缀长度 长度即为 j + 1 长度记录至数组 i 索引处 然后 i++ j++
             */
            if (pattern[i] == pattern[j]) {
                //lps[i++] = ++j;
                lps[i] = j + 1;
                i++;
                j++;
            }
            /*
                遇到不同字符:
                    前面没有共同部分(j=0)
                    前面有共同部分 j向回找
                        无需对比的地方,可以跳过
                        无需对比的字符个数 之前已计算过
             */
            else if (j == 0) {
                i++;
            } else {
                j = lps[j - 1];
            }

        }
        return lps;
    }


    //暴力枚举
    public static int strStr0(String str1, String str2) {

        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int i = 0, j = 0;
        while (i <= char1.length - char2.length) {
            for (j = 0; j < char2.length; j++) {
                if (char2[j] != char1[j + i]) {
                    break;
                }
            }
            if (j == char2.length) {
                return i;
            }
            i++;
        }
        return -1;

    }

}
