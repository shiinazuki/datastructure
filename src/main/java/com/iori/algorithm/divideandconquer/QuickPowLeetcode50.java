package com.iori.algorithm.divideandconquer;

/**
 * <h3>快速幂 - 分治</h3>
 */
public class QuickPowLeetcode50 {

    public static void main(String[] args) {
        System.out.println(myPow(2, 16));  // 65536
        System.out.println(myPow(2, 10));  // 1024
        System.out.println(myPow(2, 0)); // 1.0
        System.out.println(myPow(2, -2)); // 0.25    2^-2 = 1/2^2
        System.out.println(myPow(2, -2147483648)); // 1.0   int
        System.out.println(myPow(2.1, 3)); // 9.261
    }

    /*

                           2^16                 65536  乘四次
                   /                  \
                 2^8                  2^8        256*256  乘三次
              /      \              /      \
             2^4     2^4           2^4     2^4    16*16  乘二次
            /  \     /  \         /  \     /  \
          2^2 2^2  2^2 2^2       2^2 2^2  2^2 2^2   4*4  乘一次
          /\  /\   /\  /\       /\  /\    /\  /\
         2 2 2 2  2 2 2 2      2 2 2 2   2 2 2  2


                  2^10
              /         \
            2^5         2^5    2*2^4
           /  \        /  \
        2 2^2 2^2    2 2^2 2^2
         / \  / \     / \  / \
        2  2  2  2   2  2  2  2

     */

    public static double myPow(double x, long n) {
        long p = n;
        if (p < 0) {
            p = -p;
        }
        double y = myPowPositive(x, p);

        return n < 0 ? 1 / y : y;
    }

    public static double myPowPositive(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        if (n == 1) {
            return x;
        }
        double y = myPowPositive(x, n / 2);
     /*
            1   001
            3   011
            5   101
            7   111
                001 &
                -----
                001

            2   010
            4   100
            6   110
            8  1000
                001 &
                -----
                000
         */

        //if (n % 2 == 0) { //偶数
        if ((n & 1) == 0) { //偶数
            return y * y;
        } else {
            return x * y * y;
        }

    }


}
