package com.iori.datastructure.binarysearchtree;

/**
 * 新增节点
 */
public class E02Leetcode701 {

    public static void main(String[] args) {

    }

    /*
        val=1

        5
       / \
      2   6
       \
        3
 */
    public TreeNode insertIntoBST(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        if (val < node.val) {
            //建立父子关系
            node.left = insertIntoBST(node.left, val);
        } else if (val > node.val) {
            node.right = insertIntoBST(node.right, val);
        }
        return node;
    }

}
