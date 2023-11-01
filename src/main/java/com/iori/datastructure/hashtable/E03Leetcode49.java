package com.iori.datastructure.hashtable;


import java.util.*;

/**
 * 字母异位词分组
 */
public class E03Leetcode49 {

    public static void main(String[] args) {
        String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(str));
        System.out.println(groupAnagrams1(str));
    }

    static class ArrayKey {
        int[] key = new int[26];

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayKey arrayKey = (ArrayKey) o;
            return Arrays.equals(key, arrayKey.key);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(key);
        }

        public ArrayKey(String str) {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                key[ch - 'a']++;
            }
        }
    }

    /*
    题目中有说明：strs[i] 仅包含小写字母
    key = [2, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]  26
    key = [2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]  26
    "eaat", "teaa"
 */
    public static List<List<String>> groupAnagrams1(String[] strs) { // 5ms
        HashMap<ArrayKey, List<String>> map = new HashMap<>();
        for (String str : strs) {
            ArrayKey key = new ArrayKey(str);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());

    }


    /*
        解法1 思路
        1. 遍历字符串数组，每个字符串中的字符重新排序后作为 key
        2. 所谓分组，其实就是准备一个集合，把这些单词加入到 key 相同的集合中
        3. 返回分组结果
    */
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            sort(chars);
            String key = new String(chars);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void sort(char[] chars) {
        for (int i = 1; i < chars.length; i++) {
            char ch = chars[i];
            int j = i - 1;
            while (j >= 0 && chars[j] > ch) {
                chars[j + 1] = chars[j];
                j--;
            }
            chars[j + 1] = ch;
        }
    }


}
