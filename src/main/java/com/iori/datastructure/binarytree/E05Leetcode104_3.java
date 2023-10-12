package com.iori.datastructure.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最大深度 - 使用后序遍历(非递归)求解
 */
public class E05Leetcode104_3 {


    /*
     思路：使用层序遍历法
  */
    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int max = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            max++;
        }
        return max;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4),
                        2,
                        new TreeNode(new TreeNode(7), 5, null)
                ),
                1,
                new TreeNode(null, 3, new TreeNode(6)));
        new E05Leetcode104_3().maxDepth(root);
    }


}
