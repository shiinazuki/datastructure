package com.iori.algorithm.greedy;

import java.util.*;

/**
 * <h3>Huffman树以及编解码</h3>
 */
public class HuffmanTree {

    /*
        Huffman 树的构建过程

        1. 将统计了出现频率的字符,放入优先级队列

        2. 每次出队两个频次最低的元素,给它俩找个爹
        3.把爹重新放入队列, 重复 2~3
        4. 当队列只剩一个元素时, Huffman 树构建完成
     */

    static class Node {
        Character ch; //字符
        int freq; //频次
        Node left;
        Node right;
        String code; //编码


        public Node(Character ch) {
            this.ch = ch;
        }

        public Node(int freq, Node left, Node right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        int freq() {
            return freq;
        }

        boolean isLeaf() {
            return left == null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", freq=" + freq +
                    '}';
        }

    }

    Node root;
    int length;
    String origin;
    Map<Character, Node> map = new HashMap<>();

    public HuffmanTree(String str) {
        build(str);


    }

    /**
     * 构建Huffman树方法
     * @param str
     */
    private void build(String str) {
        //功能1: 统计频率
        char[] chars = str.toCharArray();
        for (char c : chars) {
         /*   if (!map.containsKey(c)) {
                map.put(c, new Node(c));
            }
                Node node = map.get(c);
                node.freq++;*/
            Node node = map.computeIfAbsent(c, Node::new);
            node.freq++;
        }
        //功能2: 构造树
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::freq));
        queue.addAll(map.values());
        while (queue.size() >= 2) {
            Node left = queue.poll();
            Node right = queue.poll();
            int freq = left.freq + right.freq;
            queue.offer(new Node(freq, left, right));
        }
        root = queue.poll();
        //功能3: 计算每个字符的编码, 功能4: 字符串编码后占用 bits
        length = dfs(root, new StringBuilder());

        for (Node value : map.values()) {
            System.out.println(value + " " + value.code);
        }
       origin = str;
    }


    /**
     * 递归找编码
     *
     * @param node
     * @param code
     */
    private int dfs(Node node, StringBuilder code) {
        int sum = 0;
        if (node.isLeaf()) {
            //找到编码
            node.code = code.toString();
            sum = node.freq * code.length();
        } else {
            sum += dfs(node.left, code.append("0"));
            sum += dfs(node.right, code.append("1"));
        }
        if (code.length() > 0) {
            code.deleteCharAt(code.length() - 1);
        }
        return sum;
    }


    /**
     * 编码
     *
     * @return
     */
    public String encode() {
        char[] chars = origin.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(map.get(c).code);
        }
        return sb.toString();
    }

    /**
     * 解码
     *
     * @return
     */
    public String decode(String str) {

          /*
            从根节点，寻找数字对应的字符
                数字是 0 向左走
                数字是 1 向右走
                如果没走到头，每走一步数字的索引 i++
            走到头就可以找到解码字符，再将 node 重置为根节点

            a 00
            b 10
            c 1

                            i
            0   0   0   1   0   1   1   1   1   1   1   1   1
         */
        char[] chars = str.toCharArray();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        Node p = root;
        while (i < chars.length) {
            if (!p.isLeaf()) { //非叶子节点
                if (chars[i] == '0') { //向左走
                    p = p.left;
                } else if (chars[i] == '1') { //向右走
                    p = p.right;
                }
                i++;
            }
            //叶子
            if (p.isLeaf()) {
                sb.append(p.ch);
                p = root;
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        HuffmanTree tree = new HuffmanTree("abbccccccc");
        String encoded = tree.encode();
        System.out.println(encoded);

        System.out.println(tree.decode(encoded));
    }

}
