package com.iori.algorithm.greedy;

import com.iori.datastructure.binarytree.TreeNode;

import java.util.*;

/**
 * <h3>Huffman树以及编解码</h3>
 */
public class HuffmanTreeLianXi {

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
        String code;

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

        //是否是叶子节点
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

    public HuffmanTreeLianXi(String str) {
    }




    public static void main(String[] args) {
        HuffmanTreeLianXi tree = new HuffmanTreeLianXi("abbccccccc");
        //String encoded = tree.encode();
        //System.out.println(encoded);
        //System.out.println(tree.decode(encoded));
    }

}
