package com.iori.algorithm.leetcode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * <h3>跳表</h3>
 */
public class SkipListLeetcode1206 {

    public static void main(String[] args) {
        SkipList list = new SkipList();
        SkipList.Node head = list.head;
        SkipList.Node n3 = new SkipList.Node(3);
        SkipList.Node n7 = new SkipList.Node(7);
        SkipList.Node n11 = new SkipList.Node(11);
        SkipList.Node n12 = new SkipList.Node(12);
        SkipList.Node n16 = new SkipList.Node(16);
        SkipList.Node n19 = new SkipList.Node(19);
        SkipList.Node n22 = new SkipList.Node(22);
        SkipList.Node n23 = new SkipList.Node(23);
        SkipList.Node n26 = new SkipList.Node(26);
        SkipList.Node n30 = new SkipList.Node(30);
        SkipList.Node n37 = new SkipList.Node(37);
        head.next[0] = head.next[1] = head.next[2] = n3;
        head.next[3] = head.next[4] = head.next[5] = head.next[6] = head.next[7] = n19;
        n3.next[0] = n3.next[1] = n3.next[2] = n7;
        n7.next[0] = n11;
        n7.next[1] = n12;
        n7.next[2] = n16;
        n11.next[0] = n12;
        n12.next[0] = n12.next[1] = n16;
        n16.next[0] = n16.next[1] = n16.next[2] = n19;
        n19.next[0] = n19.next[1] = n19.next[2] = n22;
        n19.next[3] = n26;
        n22.next[0] = n23;
        n22.next[1] = n22.next[2] = n26;
        n23.next[0] = n26;
        n26.next[0] = n30;
        n26.next[1] = n37;
        n30.next[0] = n37;

        System.out.println(Arrays.toString(list.find(30)));
    }

    private static void testRandomLevel() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int level = randomLevel(5);
            map.compute(level, (k, v) -> v == null ? 1 : v + 1);
        }
        System.out.println(map.entrySet().stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        e -> String.format("%d%%", e.getValue() * 100 / 1000)
                )
        ));
    }

    /*
       设计一个方法，方法会随机返回 1~max 的数字
       从 1 开始，数字的几率逐级减半，例如 max = 4，让大概
           - 50% 的几率返回 1
           - 25% 的几率返回 2
           - 12.5% 的几率返回 3
           - 12.5% 的几率返回 4
    */
    static Random r = new Random();

    static int randomLevel(int max) {

        //return r.nextBoolean() ? 1 : r.nextBoolean() ? 2 : r.nextBoolean() ? 3 : 4;
        int x = 1;
        while (x < max) {
            if (r.nextBoolean()) {
                return x;
            }
            x++;
        }
        return x;
    }


    static class SkipList {
        private static final int MAX = 10; //redis 32 java 62
        private final Node head = new Node(-1); //头节点

        //节点类
        static class Node {
            int val; //值
            Node[] next = new Node[MAX]; //next 数组

            public Node(int val) {
                this.val = val;
            }

            @Override
            public String toString() {
                return "Node(" + val + ")";
            }
        }

        //下楼梯方法
        public Node[] find(int val) {
            //记录路径
            Node[] path = new Node[MAX];
            Node curr = head;
            //从上向下找
            for (int level = MAX - 1; level >= 0; level--) {
                //如果当前节点下个数组的第level号元素等于null 并且这个元素的值 < val就向右找
                while (curr.next[level] != null && curr.next[level].val < val) {
                    curr = curr.next[level];
                }
                path[level] = curr;
            }
            return path;
        }

        //查询方法
        public boolean search(int val) {
            Node[] path = find(val);
            Node node = path[0].next[0];
            return node != null && node.val == val;
        }


        public void add(int val) {
            //1. 确定添加位置 (把val当作目标查询,经历的路径就可以确定添加位置)
            Node[] path = find(val);
            //2. 创建新节点随机高度
            Node node = new Node(val);
            int level = randomLevel(MAX);
            //3. 修改路径节点 next 指针 以及新结点 next 指针
            for (int i = 0; i < level; i++) {
                node.next[i] = path[i].next[i];
                path[i].next[i] = node;
            }
        }

        public boolean erase(int val) {
            Node[] path = find(val);
            //拿到最底层的下一个节点
            Node node = path[0].next[0];
            //如果没找到 或者 当前节点的值和待删除的值不一样
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
