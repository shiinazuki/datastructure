package com.iori.leetcode.string;

/**
 * 动态口令
 */
public class LeetCodeLCR182 {

    public static void main(String[] args) {

        String password = "s3cur1tyC0d3";
        System.out.println(dynamicPassword(password, 4));

    }

    public static String dynamicPassword(String password, int target) {

        StringBuilder sb = new StringBuilder();
        char[] chars = password.toCharArray();
        for (int i = target; i < chars.length; i++) {
            sb.append(chars[i]);
        }

        for (int i = 0; i < target; i++) {
            sb.append(chars[i]);
        }

        return sb.toString();
    }

}
