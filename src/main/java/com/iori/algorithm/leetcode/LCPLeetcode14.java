package com.iori.algorithm.leetcode;

/**
 * <h3>最长公共前缀</h3>
 */
public class LCPLeetcode14 {

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {

        /*
          情况1：比较某一列时，遇到不同字符，i 之前的字符就是解
          情况2：比较某一列时，遇到字符串长度不够，i 之前的字符就是解
          情况3：i 循环自然结束，此时第一个字符串就是解
       */

        char[] first = strs[0].toCharArray();
        for (int i = 0; i < first.length; i++) {
            char ch = first[i];
            for (int j = 1; j < strs.length; j++) {
               /* //情况2：比较某一列时，遇到字符串长度不够，i 之前的字符就是解
                if(strs[j].length() == i) {
                    return new String(first,0,j);
                }
                //情况1：比较某一列时，遇到不同字符，i 之前的字符就是解
                if (ch != strs[j].charAt(i)) {
                    return new String(first,0,i);
                }*/

                //情况1：比较某一列时，遇到不同字符，i 之前的字符就是解
                //情况2：比较某一列时，遇到字符串长度不够，i 之前的字符就是解
                if (strs[j].length() == i || ch != strs[j].charAt(i)){
                    return new String(first, 0, i);
                }
            }

        }
        //情况3：i 循环自然结束，此时第一个字符串就是解
        return strs[0];
    }


    //看了题解的大概思路 自己想着写的  自己是想不出来的
    public static String longestCommonPrefix0(String[] strs) {
        //先拿到第一个
        String first = strs[0];
        StringBuilder sb = new StringBuilder();
        //从第一个开始遍历
        for (int i = 1; i < strs.length; i++) {
            //拿到第二个
            String str2 = strs[i];
            //定义一个指针
            int j = 0;
            //将StringBuilder的长度置0
            sb.setLength(0);
            //j < 两个字符串中长度小的
            while (j < Math.min(first.length(), str2.length())) {
                //如果字符串1的j号元素不等于字符串2的j号元素 就跳出循环
                if (first.charAt(j) != str2.charAt(j)) {
                    break;
                }
                //等于就拼接字符串
                sb.append(first.charAt(j));
                j++;
            }
            //将找到的公共部分赋值给str1供下次比对
            first = sb.toString();
        }
        return first;
    }

}
