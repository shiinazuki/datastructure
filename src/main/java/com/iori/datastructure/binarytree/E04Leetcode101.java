package com.iori.datastructure.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.iori.datastructure.binarytree.E01Leetcode144.colorPrintln;

/**
 * 对称二叉树
 */
public class E04Leetcode101 {


    public static void main(String[] args) {

    }

    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    public boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (right == null || left == null || left.val != right.val) {
            return false;
        }

        return check(left.left,right.right) && check(left.right,right.left);
    }







}
