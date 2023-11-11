package com.iori.algorithm.dynamicprogramming;


import java.util.ArrayList;
import java.util.List;

/**
 * <h3>括号生成</h3>
 */
public class Leetcode22 {

    public static void main(String[] args) {
        Leetcode22 code = new Leetcode22();
        System.out.println(code.generateParenthesis(3));
    }

    private List<String> generateParenthesis(int n) {
        List<String>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<>(List.of(""));
        dp[1] = new ArrayList<>(List.of("()"));
//        dp[2] = ()(), (())
//        dp[3] = ()()(), ()(()), (())(), (()()), ((()))
        for (int i = 2; i < n + 1; i++) {
            dp[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                // i 对应的集合是内层要嵌套的括号, j - 1 - i 对应的集合是平级要拼接的括号
                //System.out.printf("(%d,%d)\t", j, i - 1 - j);
                for (String k1 : dp[j]) { // ()(), (())
                    for (String k2 : dp[i - 1 - j]) { // ""
                        dp[i].add("(" + k1 + ")" + k2);
                    }
                }

            }
        }

       return (dp[n]);
    }

}
