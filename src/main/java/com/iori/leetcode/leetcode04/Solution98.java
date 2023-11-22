package com.iori.leetcode.leetcode04;

import com.iori.datastructure.binarytree.TreeNode;

import java.util.Stack;

/**
 * 98. 验证二叉搜索树
 */
public class Solution98 {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(new TreeNode(1),
                2,
                new TreeNode(3));

        System.out.println(isValidBST(root));

    }

    public static boolean isValidBST(TreeNode root) {
      return true;
    }

}
