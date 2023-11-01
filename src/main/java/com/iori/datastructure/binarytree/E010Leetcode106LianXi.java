package com.iori.datastructure.binarytree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 根据中序和后序遍历结果构造二叉树
 */
public class E010Leetcode106LianXi {


 /*
        inOrder = {4,2,1,6,3,7}
        postOrder = {4,2,6,7,3,1}

        根 1
           in        post
        左 4,2       4,2
        右 6,3,7     6,7,3
     */

    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        if (inOrder.length == 0) {
            return null;
        }

        //创建根节点
        int rootVal = postOrder[postOrder.length - 1];
        TreeNode root = new TreeNode(rootVal);
        //切分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootVal) {
                //0 ~i - 1 左子树
                //i + 1 ~ inOrder.length - 1 右子树
                //中序遍历左右子树拆分
                int[] inLeft = Arrays.copyOfRange(inOrder, 0, i);
                int[] inRight = Arrays.copyOfRange(inOrder, i + 1, inOrder.length);

                //后序遍历左右子树拆分
                int[] postLeft = Arrays.copyOfRange(postOrder, 0, i);
                int[] postRight = Arrays.copyOfRange(postOrder, i, postOrder.length - 1);

                root.left = buildTree(inLeft, postLeft);
                root.right = buildTree(inRight, postRight);

                //递归返回根节点
                root.left = buildTree(inLeft, postLeft);
                root.right = buildTree(inRight, postRight);
                break;
            }
        }

        return root;

    }

    public static TreeNode buildTree1(int[] inOrder, int[] postOrder) {
        if (inOrder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(postOrder[postOrder.length - 1]);
        int in = inOrder.length - 1;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        for (int i = postOrder.length - 2; i >= 0; i--) {
            int postVal = postOrder[i];
            TreeNode node = stack.peek();
            if (node.val == inOrder[in]) {
                while (!stack.isEmpty() && stack.peek().val == inOrder[in]) {
                    node = stack.pop();
                    in--;
                }
                node.left = new TreeNode(postVal);
                stack.push(node.left);
            } else {
                node.right = new TreeNode(postVal);
                stack.push(node.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        int[] inOrder = new int[]{15, 9, 10, 3, 20, 5, 7, 8, 4};
        int[] postOrder = new int[]{15, 10, 9, 5, 4, 8, 7, 20, 3};
        TreeNode treeNode = buildTree1(inOrder, postOrder);
        TreeNode cur = treeNode;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);

                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                System.out.println(pop.val);
                cur = pop.right;
            }
        }
    }


}
