package com.iori.datastructure.hashtable;

import org.checkerframework.checker.units.qual.min;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 字符串中的第一个不重复字符
 *
 * <p>s 中仅包含小写字符</p>
 */
public class E07Leetcode387 {

      /*
        输入: s = "leetcode"
        l t c o d e
        输出: 0

        输入: s = "loveleetcode"
        输出: 2

        输入: s = "aabb"
        输出: -1
     */

    public static void main(String[] args) {

        String str = "aadadaad";
        //firstUniqChar1(str);
        System.out.println(firstUniqChar1(str));
    }


    /**
     * 自己想想
     * 有 bug
     */
    public static int firstUniqChar1(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }


}
