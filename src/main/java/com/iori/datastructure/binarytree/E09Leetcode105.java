package com.iori.datastructure.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 根据前序中序遍历结果构造二叉树
 */
public class E09Leetcode105 {


 /*
        preOrder = {1,2,4,3,6,7}
        inOrder = {4,2,1,6,3,7}

        根 1
            pre         in
        左  2,4         4,2
        右  3,6,7       6,3,7


        根 2
        左 4

        根 3
        左 6
        右 7
     */

    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0) {
            return null;
        }

        //创建根节点
        int rootVal = preOrder[0];
        TreeNode root = new TreeNode(rootVal);
        //区分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootVal) {
                //0 ~i - 1 左子树
                //i + 1 ~ inOrder.length - 1 右子树
                //中序遍历左右子树拆分
                int[] inLeft = Arrays.copyOfRange(inOrder, 0, i); //[ 4, 2]
                int[] inRight = Arrays.copyOfRange(inOrder, i + 1, inOrder.length); //[6,3,7]

                //前序遍历左右子树拆分
                int[] preLeft = Arrays.copyOfRange(preOrder, 1, i + 1); //[2,4]
                int[] preRight = Arrays.copyOfRange(preOrder, i + 1, preOrder.length); //[3,7,7]

                //递归返回根节点
                root.left = buildTree(preLeft, inLeft);
                root.right = buildTree(preRight, inRight);
                break;
            }
        }

        return root;

    }

    /**
     * 根据前序中序遍历结果构造二叉树  迭代法
     *
     * @param preOrder
     * @param inOrder
     * @return
     */
    public static TreeNode buildTree1(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0) {
            return null;
        }
        Stack<TreeNode> roots = new Stack<>();
        int pre = 0;
        int in = 0;
        //先遍历第一个值作为根节点
        TreeNode curRoot = new TreeNode(preOrder[pre]);
        TreeNode root = curRoot;
        roots.push(curRoot);
        pre++;
        //遍历前序遍历的数组
        while (pre < preOrder.length) {
            //出现了当前节点的值 和 中序遍历数组的值想等 寻找是谁的右子树
            if (curRoot.val == inOrder[in]) {
                //每次进行出栈,实现倒着遍历
                while (!roots.isEmpty() && roots.peek().val == inOrder[in]) {
                    curRoot = roots.peek();
                    roots.pop();
                    in++;
                }
                //设为当前的右孩子
                curRoot.right = new TreeNode(preOrder[pre]);
                //更新curRoot
                curRoot = curRoot.right;
                roots.push(curRoot);
                pre++;
            } else {
                //否则的话就一直作为左子树
                curRoot.left = new TreeNode(preOrder[pre]);
                curRoot = curRoot.left;
                roots.push(curRoot);
                pre++;
            }
        }
        return root;

    }


    public static void main(String[] args) {
        int[] preOrder = new int[]{3, 9, 20, 15, 7};
        int[] inOrder = new int[]{20, 9, 15, 3, 7};
        TreeNode treeNode = buildTree1(preOrder, inOrder);
        TreeNode cur = treeNode;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                System.out.println(cur.val);
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                cur = pop.right;
            }
        }

    }


}
