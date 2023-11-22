package com.iori.leetcode.leetcode03;

import java.util.*;

/**
 * 49. 字母异位词分组
 */
public class Solution49 {

    static class ArrayKey {

        char[] chars = new char[128];

        public ArrayKey(String str) {
            char[] cha = str.toCharArray();
            for (int i = 0; i < cha.length; i++) {
                chars[cha[i]]++;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayKey arrayKey = (ArrayKey) o;
            return Arrays.equals(chars, arrayKey.chars);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(chars);
        }
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams0(strs));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        return null;
    }

    //使用hash表
    public static List<List<String>> groupAnagrams0(String[] strs) {

      return null;
    }

    private static void sort(char[] chars) {

    }


}
