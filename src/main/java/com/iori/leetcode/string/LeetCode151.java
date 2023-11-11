package com.iori.leetcode.string;

import java.util.ArrayList;

/**
 * 反转字符串中的单词
 */
public class LeetCode151 {

    public static void main(String[] args) {

        String s = "  hello world  ";
        System.out.println(reverseWords(s));

    }

    public static String reverseWords(String s) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == ' ') {
                list.add(sb.toString());
                sb.setLength(0);
            }else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            list.add(sb.toString());
        }
        sb.setLength(0);

        int left = 0;
        int right = list.size() - 1;
        Object[] str =  list.toArray();
        while (left < right) {
            Object temp = str[left];
            str[left] = str[right];
            str[right] = temp;
            left++;
            right--;
        }

        for (int j = 0; j < str.length; j++) {
            if (str[j] == "") {
                continue;
            }
            sb.append(str[j]);
            if (j != str.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString().trim();
    }

}
