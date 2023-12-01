package com.iori.algorithm.divideandconquer;

import java.util.Arrays;

/**
 * <h3>至少k个字符的最长子串</h3>
 */
public class LongestSubstringLeetcode395 {


    public static void main(String[] args) {
        //                                         i j
        //System.out.println(longestSubstring("aaaccbbb", 3)); // ababb
        //System.out.println(longestSubstring("dddxaabaaabaacciiiiefbff", 3));
        //System.out.println(longestSubstring("ababbc", 3)); // ababb
        System.out.println(longestSubstring("ababbc", 2)); // ababb
        //System.out.println(longestSubstring("aaabb", 3)); // ababb
        /*
            ddd aabaaabaa iiii fbff
                aa aaa aa      f ff

            统计字符串中每个字符的出现次数，移除哪些出现次数 < k 的字符
            剩余的子串，递归做此处理，直至
                 - 整个子串长度 < k (排除)
                 - 子串中没有出现次数 < k 的字符
         */
    }


    //视频解法  递归
    public static int longestSubstring1(String s, int k) {
        // s.length() < k
        //字串落选情况
        if (s.length() < k) {
            return 0;
        }

        int[] counts = new int[26]; // 索引对应字符  值用来存储该字符出现几次
        char[] chars = s.toCharArray();
        for (char c : chars) {
            counts[c - 'a']++;
        }

        //System.out.println(Arrays.toString(counts));

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int count = counts[c - 'a'];  //字符出现次数
            if (count > 0 && count < k) {
                int j = i + 1;
                while (j < s.length() && counts[chars[j] - 'a'] < k) {
                    j++;
                }
                //System.out.println(s.substring(0, i) + "\t" + s.substring(j));
                return Integer.max(longestSubstring0(s.substring(0, i), k),
                        longestSubstring0(s.substring(j), k));


            }
        }
        //入选了
        return s.length();

    }

    //自己试试
    public static int longestSubstring(String s, int k) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c - 'a']++;
        }
        int max = 0;
        int left = 0;
        int right = 0;
        while (right < chars.length) {
            char c = chars[right];
            if (count[c - 'a'] < k) {
                max = right - left + 1;
                left = right;
                break;
            }
            right++;
        }
        return max - 1;

    }

    //抄的题解 已经理解了
    public static int longestSubstring0(String s, int k) {
        int ans = 0;
        int len = s.length();
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        for (int p = 1; p <= 26; p++) {
            Arrays.fill(cnt, 0);
            // tot 代表 [left, right] 区间所有的字符种类数量；sum 代表满足「出现次数不少于 k」的字符种类数量
            int left = 0, tot = 0, sum = 0;
            for (int right = 0; right < len; right++) {
                int u = cs[right] - 'a';
                cnt[u]++;
                // 如果添加到 cnt 之后为 1，说明字符总数 +1
                if (cnt[u] == 1) {
                    tot++;
                }
                // 如果添加到 cnt 之后等于 k，说明该字符从不达标变为达标，达标数量 + 1
                if (cnt[u] == k) {
                    sum++;
                }
                // 当区间所包含的字符种类数量 tot 超过了当前限定的数量 reversePrint，那么我们要删除掉一些字母，即「左指针」右移
                while (tot > p) {
                    int t = cs[left++] - 'a';
                    cnt[t]--;
                    // 如果添加到 cnt 之后为 0，说明字符总数-1
                    if (cnt[t] == 0) {
                        tot--;
                    }
                    // 如果添加到 cnt 之后等于 k - 1，说明该字符从达标变为不达标，达标数量 - 1
                    if (cnt[t] == k - 1) {
                        sum--;
                    }
                }
                // 当所有字符都符合要求，更新答案
                if (tot == sum) {
                    ans = Math.max(ans, right - left + 1);
                }
            }

        }
        return ans;
    }


}
