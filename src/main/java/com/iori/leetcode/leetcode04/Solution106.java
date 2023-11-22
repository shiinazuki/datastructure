package com.iori.leetcode.leetcode04;

import com.iori.datastructure.binarytree.TreeNode;

import java.util.HashMap;
import java.util.Stack;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 */
public class Solution106 {

    public static void main(String[] args) {

        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        System.out.println(buildTree1(inorder, postorder));
    }

    //递归
    public static TreeNode buildTree1(int[] inorder, int[] postorder) {

        return null;

    }

    //迭代
    public static TreeNode buildTree(int[] inorder, int[] postorder) {

        return null;
    }


}
