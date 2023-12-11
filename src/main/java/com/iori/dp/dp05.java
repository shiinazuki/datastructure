package com.iori.dp;

/**
 *
 */
public class dp05 {

    /*
        规定1和A对应、2和B对应、3和C对应..
        那么一个数字字符串比如”111”就可以转化为  “AAA” ”KA" 和” AK”
        给定一个只有数字字符组成的字符串str，返回有多少种转化结果
     */

    public static void main(String[] args) {
        String str = "111";
        System.out.println(number(str));
        System.out.println(number1(str));
        System.out.println(number2(str));
    }


    /*--------------------------------------*/
    //动态规划 2
    public static int number2(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        int[] dp = new int[len + 1];

        dp[0] = 1;
            for (int i = 1; i <= len; i++) {
            if (chars[i - 1] != '0') {
                int ways = dp[i - 1];
                if (i >= 2 && (chars[i - 2] - '0') * 10 + chars[i - 1] - '0' < 27) {
                    ways += dp[i - 2];
                }
                dp[i] = ways;
            }
        }

        return dp[len];
    }

    /*--------------------------------------*/
    //动态规划 1
    public static int number1(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        int[] dp = new int[len + 1];

        dp[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (chars[i] != '0') {
                int ways = dp[i + 1];
                if (i + 1 < len && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }

        return dp[0];
    }




    /*--------------------------------------*/
    //暴力递归

    //str 只含有数字字符0~9
    //返回多少种转化方案
    public static int number(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    //str[0,i-1]
    private static int process(char[] chars, int i) {
        if (i == chars.length) {
            return 1;
        }
        //i 没到最后 有字符
        //如果第 i 号字符是 0 说明选的不对 因为0 没有对应的解
        if (chars[i] == '0') {
            return 0;
        }
        //chars[i] != 0;
        //可能性1
        int ways = process(chars, i + 1);
        if (i + 1 < chars.length && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27) {
            ways += process(chars, i + 2);
        }
        return ways;
    }


}
