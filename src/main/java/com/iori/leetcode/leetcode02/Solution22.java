package com.iori.leetcode.leetcode02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 22. 括号生成
 */
public class Solution22 {

    //节点类
    static class Node {
        private String res;

        private int left;

        private int right;

        public Node(String res, int left, int right) {
            this.res = res;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "res='" + res + '\'' +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis1(3));
    }

    //广度优先遍历
    public static List<String> generateParenthesis(int n) {


        List<String> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n,n));
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                result.add(curNode.res);
            }
            if (curNode.left > 0) {
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right -1));
            }
        }

        return null;

    }


    //深度优先遍历
    public static List<String> generateParenthesis1(int n) {

        return null;
    }



    //动态规划
    public static List<String> generateParenthesis0(int n) {
        List<String>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<>(java.util.List.of(""));
        dp[1] = new ArrayList<>(List.of("()"));
        for (int i = 2; i < n + 1; i++) {
            dp[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String k1 : dp[j]) {
                    for (String k2 : dp[i - 1 - j]) {
                        dp[i].add("(" + k1 + ")" + k2);
                    }
                }
            }
        }
        return (dp[n]);

    }


}
