package com.iori.algorithm.recursion_single;

import java.util.Arrays;

public class Factorial {

    public static void main(String[] args) {
        System.out.println(factorial(5));

    }


    /**
     * 求某个数的阶乘
     * @param num
     * @return
     */
    public static int factorial(int num) {
        if (num == 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }

}
