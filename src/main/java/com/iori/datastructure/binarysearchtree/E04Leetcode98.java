package com.iori.datastructure.binarysearchtree;


import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 判断是否为合法的二叉搜索树
 */
public class E04Leetcode98 {

    long prev = Long.MIN_VALUE;


    public static void main(String[] args) {
        /*
                4
               / \
              2   6
             / \
            1   3
        */
        TreeNode root1 = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(6));
        System.out.println(new E04Leetcode98().isValidBST4(root1));
//        System.out.println("---------------");
        /*
                4
               / \
              2   6
                 / \
                3   7
         */
        TreeNode root2 = new TreeNode(4, new TreeNode(2), new TreeNode(6, new TreeNode(3), new TreeNode(7)));
        System.out.println(new E04Leetcode98().isValidBST4(root2));
//        System.out.println("---------------");

        /*
               1
              /
             1
         */
        TreeNode root3 = new TreeNode(1, new TreeNode(1), null);
        System.out.println(new E04Leetcode98().isValidBST4(root3));
//        System.out.println("---------------");

        /*
              3
               \
                4
               /
              5
         */
        TreeNode root4 = new TreeNode(3, null, new TreeNode(4, new TreeNode(5), null));
        System.out.println(new E04Leetcode98().isValidBST4(root4));
    }


    //解法1 中序遍历非递归实现
    public boolean isValidBST1(TreeNode node) {
        TreeNode p = node;
        LinkedList<TreeNode> stack = new LinkedList<>();
        long prev = Long.MIN_VALUE;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                if (prev >= pop.val) {
                    return false;
                }
                p = pop.right;
            }
        }
        return true;
    }

    //解法2 中序遍历递归实现
    public boolean isValidBST2(TreeNode node) {
        if (node == null) {
            return true;
        }
        boolean a = isValidBST2(node.left);
        if (!a) {
            return false;
        }
        //值
        if (prev >= node.val) {
            return false;
        }
        prev = node.val;

        return isValidBST2(node.right);
    }

    // 解法3. 中序遍历递归实现(局部变量记录 prev)
    public boolean isValidBST3(TreeNode node) {
        return doValid3(node, new AtomicLong(Long.MIN_VALUE));
    }

    private boolean doValid3(TreeNode node, AtomicLong prev) {
        if (node == null) {
            return true;
        }
        boolean a = doValid3(node.left, prev);
        if (!a) {
            return false;
        }
        if (prev.get() >= node.val) {
            return false;
        }
        prev.set(node.val);
        return doValid3(node.right, prev);
    }

    //解法4 上下限递归实现
    public boolean isValidBST4(TreeNode node) {
        return doValid4(node, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean doValid4(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return doValid4(node.left, min, node.val) && doValid4(node.right, node.val, max);

    }


}
