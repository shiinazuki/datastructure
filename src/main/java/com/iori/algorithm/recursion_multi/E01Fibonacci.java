package com.iori.algorithm.recursion_multi;

import java.util.Arrays;

/**
 * 斐波那契第 n 项
 */
public class E01Fibonacci {

    /**
     * 递归求斐波那契第 n 项
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        return f(n, cache);
    }

    /**
     * 递归求斐波那契第 n 项
     * @param n
     * @return
     */
    public static int fibonacci2(int n) {

        return f1(n);
    }


    /**
     * 记忆法 递归求
     * @param n
     * @param cache
     * @return
     */
    public static int f(int n, int[] cache) {
        //if (n == 0) {
        //    return 0;
        //}
        //if (n == 1) {
        //    return 1;
        //}

        if (cache[n] != -1) {
            return cache[n];
        }
        int x = f(n - 1, cache);
        int y = f(n - 2, cache);
        //存入数组
        cache[n] = x + y;
        return cache[n];


    }

    /**
     * 递归求
     * @param n
     * @return
     */
    public static int f1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }


        int x = f1(n - 1);
        int y = f1(n - 2);
        //返回
        return x + y;


    }

    /**
     * 递归解决兔子问题
     *
     * @param n
     * @return
     */
    public static int rabbit(int n) {

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        int x = rabbit(n - 1);
        int y = rabbit(n - 2);
        return x + y;
    }


}
