package com.iori.datastructure.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最小深度 - 使用递归求解
 */
public class E06Leetcode111_1 {


    /*
     思路：使用层序遍历法
  */
    public int minDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int d1 = minDepth(node.left);
        int d2 = minDepth(node.right);
        //当左子树为null  返回右子树深度 + 1
        if (d1 == 0) {
            return d2 + 1;
        }
        //如果右子树为null  返货左子树深度 + 1
        if (d2 == 0) {
            return d1 + 1;
        }
        int min = Integer.min(d1, d2);
        return min + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4),
                        2,
                        new TreeNode(new TreeNode(7), 5, null)
                ),
                1,
                new TreeNode(null, 3, new TreeNode(6)));
        new E06Leetcode111_1().minDepth(root);
    }


}
