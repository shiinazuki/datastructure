package com.iori.algorithm.recursion_single;

/**
 * 递归求和
 */
public class E06Sum {

    public static void main(String[] args) {
        System.out.println(sum(16500));
    }

    public static long sum(int num) {
        if (num == 1) {
            return 1;
        }
        return sum(num - 1) + num;
    }

}
