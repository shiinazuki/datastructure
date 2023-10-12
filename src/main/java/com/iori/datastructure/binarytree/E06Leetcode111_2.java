package com.iori.datastructure.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最小深度 - 使用层序遍历法求解
 */
public class E06Leetcode111_2 {


    /*
     思路：使用层序遍历法 遇到的第一个叶子节点所在层 就是最小深度
  */
    public int minDepth(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int min = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            min++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                //判断是不是叶子节点
                if (poll.left == null && poll.right == null) {
                    return min;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right == null) {
                    queue.offer(poll.right);
                }
            }
        }

        return min;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4),
                        2,
                        new TreeNode(new TreeNode(7), 5, null)
                ),
                1,
                new TreeNode(null, 3, new TreeNode(6)));
        new E06Leetcode111_2().minDepth(root);
    }


}
