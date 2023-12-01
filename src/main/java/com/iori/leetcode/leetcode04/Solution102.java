package com.iori.leetcode.leetcode04;

import com.iori.datastructure.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 */
public class Solution102 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new TreeNode(9),
                3,
                new TreeNode(new TreeNode(15), 20, new TreeNode(7)));

        System.out.println(levelOrder(root));

    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        return null;

    }
}
