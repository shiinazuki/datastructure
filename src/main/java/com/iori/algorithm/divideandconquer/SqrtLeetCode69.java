package com.iori.algorithm.divideandconquer;

/**
 * <h3>平方根整数部分</h3>
 */
public class SqrtLeetCode69 {


    public static void main(String[] args) {
        System.out.println(mySqrt2(99)); // 9
        System.out.println(mySqrt2(1)); // 1
        System.out.println(mySqrt2(2)); // 1
        System.out.println(mySqrt2(4)); // 2
        System.out.println(mySqrt2(5)); // 2
        System.out.println(mySqrt2(8)); // 2
        System.out.println(mySqrt2(9)); // 3
        System.out.println(mySqrt2(2147395599));
    }


    /*
        99 = 9.?

        1*1 = 1  10 次
        2*2 = 4
        ...
        9*9 = 81
        10*10 = 100


        x=99

        i j  m
        1 99 50  6 次
        1 49 25
        1 24 12
        1 11 6
        7 11 9
        10 11 10
     */

    public static int mySqrt2(int x) {

        int i = 1;
        int j = x;
        int r = 0;
        while (i <= j) {
            int mid = (i + j) >>> 1;
            //int mm = mid * mid;
            if (mid <= x / mid) {
                i = mid + 1;
                r = mid;
            } else {
                j = mid - 1;

            }
        }
        return r;


    }

    public static int mySqrt1(int x) {

        int i = 1;
        int j = x;
        int r = 0;
        while (i <= j) {
            int mid = (i +j) >>> 1;
            int mm = mid * mid;
            if (mm == x) {
                return mid;
            }else if (mm > x) {
                j = mid - 1;
            }else {
                i = mid + 1;
                r = mid;
            }
        }
        return r;


    }


    public static int mySqrt0(int x) {
        int a = 0;
        for (int i = 0; i * i <= x; i++) {
            a = i;
        }
        return a;
    }


}
