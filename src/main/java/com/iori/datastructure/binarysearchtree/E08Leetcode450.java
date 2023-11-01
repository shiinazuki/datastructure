package com.iori.datastructure.binarysearchtree;

/**
 * 删除二叉搜索树中的节点
 */
public class E08Leetcode450 {

    public static void main(String[] args) {

    }

    public TreeNode remove1(TreeNode node,int key) {
        TreeNode p = node,parent = null;
        while(p != null && p.val != key) {
            parent = p;
            if (key < p.val) {
                p = p.left;
            }else {
                p = p.right;
            }
        }
        if (p == null) {
            return node;
        }
        if (p.left == null && p.right == null) {
            p = null;
        }else if (p.right == null) {
            p = p.left;
        }else if (p.left == null) {
            p = p.right;
        }else {
            TreeNode s = p.right,sparent = p;
            while (s.left != null) {
                sparent = s;
                s = s.left;
            }
            if (sparent.val == p.val) {
                sparent.right = s.right;
            }else {
                sparent.left = s.right;
            }
            s.right = p.right;
            s.left = p.left;
            p = s;
        }
        if (parent == null) {
            return p;
        }else {
            if (parent.left != null && parent.left.val == key) {
                parent.left = p;
            }else {
                parent.right = p;
            }
            return node;

        }

    }




}
