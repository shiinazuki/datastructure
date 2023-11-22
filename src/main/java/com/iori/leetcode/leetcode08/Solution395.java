package com.iori.leetcode.leetcode08;

import java.util.Arrays;

/**
 * 395. 至少有 K 个重复字符的最长子串
 */
public class Solution395 {

    public static void main(String[] args) {
        String s = "ababbc";
        int k = 2;
        System.out.println(longestSubstring1(s, k));
    }

    private static int longestSubstring1(String s, int k) {
      return 0;
    }

    public static int longestSubstring10(String s, int k) {

        int ans = 0;
        int len = s.length();
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];

        //循环26次 每次都比较
        for (int p = 1; p <= 26; p++) {
            //每次都重置数组
            Arrays.fill(cnt, 0);
            int left = 0, tot = 0, sum = 0;
            for (int right = 0; right < len; right++) {
                int u = cs[right] - 'a';
                cnt[u]++;
                //如果cnt[u] == 1说明新加了一个种类
                if (cnt[u] == 1) {
                    tot++;
                }
                //如果cnt[u] == k 说明字符从不达标变为达标
                if (cnt[u] == k) {
                    sum++;
                }
                //如果种类数量大于 限制的数量 就删除种类 即 左指针 右移
                while (tot > p) {
                    int t = cs[left++] - 'a';
                    cnt[t]--;
                    //如果cnt[t] == 0说明种类 - 1;
                    if (cnt[t] == 0) {
                        tot--;
                    }
                    //如果cnt[t] == k - 1 说明从 达标变为不达标
                    if (cnt[t] == k - 1) {
                        sum--;
                    }
                }
                if (tot == sum) {
                    ans = Math.max(ans, right - left + 1);
                }
            }
        }
        return ans;
    }

}
