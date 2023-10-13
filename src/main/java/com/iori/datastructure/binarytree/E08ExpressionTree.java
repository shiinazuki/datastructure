package com.iori.datastructure.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 根据后缀表达式构造表达式树
 */
public class E08ExpressionTree {


    /*
       中缀表达式           (2-1)*3
       后缀（逆波兰）表达式   21-3*

       1.遇到数字入栈
       2.遇到运算符, 出栈两次, 与当前节点建立父子关系, 当前节点入栈

       栈
       |   |
       |   |
       |   |
       _____

       表达式树
           *
          / \
         -   3
        / \
       2   1

       21-3*
    */
    public TreeNode constructExpressionTree(String[] tokens) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        for (String token : tokens) {
            switch (token) {
                case "+", "-", "*", "/" -> { //运算符
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode parent = new TreeNode(token);
                    parent.left = left;
                    parent.right = right;
                    stack.push(parent);
                }
                default -> { //数字
                    stack.push(new TreeNode(token));
                }
            }
        }

        return stack.peek();
    }


    static class TreeNode {
        public String val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, String val, TreeNode right) {
            this.left = left;
            this.val = val;
            this.right = right;
        }

        @Override
        public String toString() {
            return this.val;
        }
    }

}
