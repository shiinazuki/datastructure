package com.iori.datastructure.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 判断两个单词是否为字母异位词
 * 小写字母组成
 */
public class E06Leetcode242 {

      /*
        输入: s = "anagram", t = "nagaram"
        输出: true

        1. 拿到字符数组，排序后比较字符数组
        2. 字符打散放入 int[26]，比较数组
         a                 g1                 m  n           r
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

     */

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        //System.out.println(isAnagram1(s, t));
        //System.out.println(isAnagram2(s, t));
        //System.out.println(isAnagram3(s, t));
        System.out.println(Arrays.equals(getKey(s), getKey(t)));
    }


    /*
    public static int[] getKey(String s) {
        int[] array = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            array[ch-'a']++;
        }
        return array;
    }
    */

    public static int[] getKey(String s) {
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            array[c-'a']++;
        }
        return array;
    }


    /**
     * 使用哈希表
     */
    public static boolean isAnagram3(String s, String t) {
        HashSet<ArrayKey> set = new HashSet<>();
        ArrayKey key1 = new ArrayKey(s);
        ArrayKey key2 = new ArrayKey(t);
        set.add(key1);
        return !set.add(key2);

    }


    /**
     * 使用哈希表
     */
    public static boolean isAnagram2(String s, String t) {
        HashMap<ArrayKey, Object> map = new HashMap<>();
        ArrayKey key1 = new ArrayKey(s);
        ArrayKey key2 = new ArrayKey(t);
        map.put(key1, 0);
        return map.containsKey(key2);

    }


    /**
     * 先把字符串转为字符数组 然后进行排序
     */
    public static boolean isAnagram1(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        sort(ch1);
        sort(ch2);
        String str1 = new String(ch1);
        String str2 = new String(ch2);
        return str1.equals(str2);
    }

    public static void sort(char[] ch) {
        for (int i = 1; i < ch.length; i++) {
            char c = ch[i];
            int j = i - 1;
            while (j >= 0 && ch[j] > c) {
                ch[j + 1] = ch[j];
                j--;
            }
            ch[j + 1] = c;
        }
    }


    /**
     * 数组类
     */
    static class ArrayKey {
        int[] array = new int[26];

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayKey arrayKey = (ArrayKey) o;
            return Arrays.equals(array, arrayKey.array);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(array);
        }

        public ArrayKey(String str) {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                array[ch - 'a']++;
            }

        }

    }

}
