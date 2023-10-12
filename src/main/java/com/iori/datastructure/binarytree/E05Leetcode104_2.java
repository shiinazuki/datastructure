package com.iori.datastructure.binarytree;

import java.util.LinkedList;

/**
 * 二叉树最大深度 - 使用后序遍历(非递归)求解
 */
public class E05Leetcode104_2 {


    /*
     思路：使用非递归后序遍历 栈的最大高度即为最大深度
  */
    public int maxDepth(TreeNode node) {
        int max = 0;
        TreeNode curr = node;
        TreeNode pop = null;
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                int size = stack.size();
                if (size > max) {
                    max = size;
                }
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                } else {
                    curr = peek.right;
                }
            }
        }
        return max;

    }


}
