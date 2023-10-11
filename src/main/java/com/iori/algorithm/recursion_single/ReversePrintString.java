package com.iori.algorithm.recursion_single;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * 反向打印字符串
 */
public class ReversePrintString {

    public static void main(String[] args) {
        //print("abcd",0);
        //print1("abcd");

        String str = "hehllooo,worlddddd";
        ArrayList<Integer> arrayList = new ArrayList<>();


        //diff(str);
        //diff1(str);
        //diff2(str);

        System.out.println("----------------------");

        //char[] charArr = {'a', 'b', 'c', 'a', 'd', 'e', 'b'};
        //char[] tmp = new char[charArr.length];
        //System.out.println(Arrays.toString(tmp));
        //System.out.println(Arrays.toString(charArr));
        //clear(charArr, tmp);
        //System.out.println(Arrays.toString(tmp));
        System.out.println("----------------------");
        s1();

    }


    /**
     * 升序数组去重,返回去重后的数组
     */
    public static void s1() {
        int[] nums = {0,1,1,1,1,2,2,3,3,4};

        int p = 0;
        int q = 1;
        while(q < nums.length) {
            if (nums[p] != nums[q]) {
                if(q - p > 1){
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        int[] array = new int[p + 1];
        for (int i  = 0; i < array.length; i ++) {
            array[i] = nums[i];
        }

        nums = array;
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 字符串去重
     *
     * @return
     */
    public static char[] clear(char[] charArr, char[] tmp) {

        boolean flag;
        int m = 0;
        for (int i = 0; i < charArr.length; i++) {
            flag = true;
            for (int j = 0; j < m; j++) {
                if (tmp[j] == charArr[i]) {
                    flag = false;
                }
            }
            if (flag) {
                tmp[m++] = charArr[i];
            }
        }
        return tmp;
    }




    /**
     * 字符串去重
     *
     * @param str
     * @return
     */
    public static String diff1(String str) {

        HashSet<String> hashSet = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!hashSet.contains(c + "")) {
                hashSet.add(c + "");
                stringBuilder.append(c);
            }
        }

        System.out.println(stringBuilder);
        return "";
    }


    /**
     * 字符串去重
     *
     * @param s
     * @return
     */
    public static String diff(String s) {
        int len = s.length();
        int k = 0;
        int count = 0;
        String str = "";
        char[] c = new char[len];
        for (int i = 0; i < len; i++) {
            c[i] = s.charAt(i);
        }
        for (int i = 0; i < len; i++) {
            k = i + 1;
            while (k < len - count) {
                if (c[i] == c[k]) {
                    for (int j = k; j < len - 1; j++) {
                        c[j] = c[j + 1];//出现重复字母，从k位置开始将数组往前挪位
                    }
                    count++;//重复字母出现的次数
                    k--;
                }
                k++;
            }
        }
        for (int i = 0; i < len - count; i++) {
            str += String.valueOf(c[i]);
        }
        System.out.println(str);
        return str;
    }

    /**
     * 反向打印字符串
     *
     * @param str
     */
    public static void print1(String str) {
        String st1r = "Hello World";
        StringBuilder sb = new StringBuilder(st1r);
        sb.reverse();
        System.out.println(sb.toString());
        for (int i = str.length() - 1; i >= 0; i--) {
            System.out.println(str.charAt(i));
        }
    }

    /**
     * 递归反向打印字符串
     *
     * @param str
     */
    public static void print(String str, int n) {
        if (n == str.length()) {
            return;
        }
        //System.out.println(str.charAt(n));
        print(str, n + 1);
        System.out.println(str.charAt(n));
    }
}
