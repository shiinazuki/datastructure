package com.iori.datastructure.binarysearchtree;

import java.util.LinkedList;

/**
 * 求范围和
 */
public class E05Leetcode938 {


    public static void main(String[] args) {
        /*
                 10
                /  \
               5    15
              / \    \
             3   7    18        low=7  high=15
         */
        TreeNode n1 = new TreeNode(5, new TreeNode(3), new TreeNode(7));
        TreeNode n2 = new TreeNode(15, null, new TreeNode(18));
        TreeNode root = new TreeNode(10, n1, n2);

        //int sum = new E05Leetcode938().rangeSum1(root, 7, 15);
        int sum = new E05Leetcode938().rangeSum2(root, 7, 15);
        System.out.println(sum); // 应为 32
    }


    /**
     * 使用上下限递归 0ms
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSum2(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val < low) {
            return rangeSum2(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSum2(root.left, low, high);
        }
        //在范围内
        return root.val + rangeSum2(root.left, low, high) + rangeSum2(root.right, low, high);
    }


    /**
     * 使用中序遍历
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSum1(TreeNode root, int low, int high) {
        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                if (pop.val > high) {
                    break;
                }
                if (pop.val >= low) {
                    sum += pop.val;
                }
                p = pop.right;
            }
        }
        return sum;

    }

}
