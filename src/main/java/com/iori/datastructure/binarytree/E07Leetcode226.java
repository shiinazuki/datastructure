package com.iori.datastructure.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转二叉树
 */
public class E07Leetcode226 {


    /**
     * 迭代实现二叉树翻转
     * @return
     */
    public static TreeNode invertTree1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            TreeNode temp = poll.left;
            poll.left = poll.right;
            poll.right = temp;
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        return root;
    }

    /**
     * 递归解题  自上而下
     * @param node
     * @return
     */
    public static TreeNode invertTree(TreeNode node) {
        fn(node);
        return node;

    }

    public static void fn(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        fn(node.left);
        fn(node.right);
    }

    //自下而上 递归
    public static TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree2(root.left);
        root.left = invertTree2(root.right);
        root.right = left;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(1),
                        2,
                        new TreeNode(3)
                ),
                4,
                new TreeNode(new TreeNode(6), 7,
                        new TreeNode(9)));
        System.out.println(invertTree(root));

    }


}
