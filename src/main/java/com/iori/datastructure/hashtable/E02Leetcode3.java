package com.iori.datastructure.hashtable;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 无重复字符的最长子串
 * <p>1. 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。</p>
 * <p>2. s 由英文字母、数字、符号和空格组成</p>
 */
public class E02Leetcode3 {

    public static void main(String[] args) {

 /*
    [(a,3) (b,1) (c,2)]

     b
       e
    abcabcbb
    要点：
        1.用 begin 和 end 表示子串开始和结束位置
        2.用 hash 表检查重复字符
        3.从左向右查看每个字符, 如果:
         - 没遇到重复字符，调整 end
         - 遇到重复的字符，调整 begin
         - 将当前字符放入 hash 表
        4.end - begin + 1 是当前子串长度
     */

        String s = "abba";
        System.out.println(lengthOfLongestSubstring(s));

    }

    public static int lengthOfLongestSubstring(String s) {

        HashMap<Character, Integer> map = new HashMap<>();
        int begin = 0;
        int maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                begin = Math.max(begin, map.get(ch) + 1);
                map.put(ch, end);
            } else {
                //不存在则加入
                map.put(ch, end);
            }
            maxLength = Math.max(maxLength, end - begin + 1);
        }

        return maxLength;
    }


    /**
     * 自己写的 只能应对一部分情况 有bug
     */
    public int lengthOfLongestSubstring1(String s) {
        int a = 0, b = 1;
        int max = 1;
        int num = 0;
        while (b < s.length()) {
            char a1 = s.charAt(a);
            char b1 = s.charAt(b);
            if (num > max) {
                max = num;
            }
            if (a1 == b1) {
                num = 0;
                ++a;
                b = a + 1;
            } else {
                num++;
                b++;
            }
        }
        return max;


    }

}
