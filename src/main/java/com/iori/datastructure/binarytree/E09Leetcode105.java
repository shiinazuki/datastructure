package com.iori.datastructure.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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

    public static void main(String[] args) {


    }


}
