package com.iori.leetcode.leetcode09;

import java.util.Random;

/**
 * 1206. 设计跳表
 */
public class Solution1206 {


    public static void main(String[] args) {
        System.out.println(randomLevel(4));
    }

    static Random random = new Random();

    //按几率返回数字 越大记录越小
    public static int randomLevel(int max) {
        int x = 1;

        while (x < max) {
            if (random.nextBoolean()) {
                return x;
            }
            x++;
        }
        return x;
    }

    @SuppressWarnings("all")
    static class Skiplist {
        private static final int MAX = 10;

        //节点类
        static class Node {
            int val;//值
            Node[] next = new Node[MAX]; //next 数组

            public Node(int val) {
                this.val = val;
            }
        }

        //头节点
        private final Node head = new Node(-1);

        //查找方法
        public Node[] find(int val) {
            Node[] path = new Node[MAX];
            Node curr = head;
            //从上向下找
            for (int level = MAX - 1; level >= 0; level--) {
                //从左向右找
                while (curr.next[level] != null && curr.next[level].val < val) {
                    curr = curr.next[level];
                }
                path[level] = curr;
            }
            return path;
        }

        public boolean search(int val) {
            Node[] path = find(val);
            Node node = path[0].next[0];
            return node != null && node.val == val;
        }

        //添加方法
        public void add(int val) {
            //确定添加位置
            Node[] path = find(val);
            //创建新节点随机高度
            Node node = new Node(val);
            int level = randomLevel(MAX);
            //修改路径节点 next指针 以及新节点next指针
            for (int i = 0; i < level; i++) {
                node.next[i] = path[i].next[i];
                path[i].next[i] = node;
            }
        }
        //删除方法
        public boolean erase(int val) {
            Node[] path = find(val);
            Node node = path[0].next[0];
            if (node == null || node.val != val) {
                return false;
            }

            for (int i = 0; i < MAX; i++) {
                if (path[i].next[i] != node) {
                    break;
                }
                path[i].next[i] = node.next[i];
            }

            return true;
        }
    }

}
