package com.iori.datastructure.deque;

import com.iori.datastructure.binarytree.TreeNode;

import java.util.*;
import java.util.Deque;

/**
 * 二叉树Z字层序遍历
 */
public class E01Leetcode103 {





     /*
                  1
                 / \
                2   3
               /\   /\
              4  5 6  7
             /\
            8  9

              1
              3 2
              4 5 6 7
              9 8
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(
                        new TreeNode(4),
                        2,
                        new TreeNode(5)
                ),
                1,
                new TreeNode(
                        new TreeNode(6),
                        3,
                        new TreeNode(7)
                )
        );
        List<List<Integer>> lists = new E01Leetcode103().zigzagLevelOrder2(root);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int num = 1; //当前层节点数
        boolean odd = true; //奇数层
        while (!queue.isEmpty()) {
            //使用双端队列来保存数据
            LinkedList<Integer> level = new LinkedList<>();
            int size = 0;
            for (int i = 1; i <= num; i++) {
                TreeNode node = queue.poll();
                if(odd) { //如果是奇数层
                    level.offerLast(node.val);
                }else {
                    level.offerFirst(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                    size++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    size++;
                }
            }
            result.add(level);
            num = size;
            odd = !odd;
        }
        return result;

    }

    /**
     * 题解的答案
     * @param root
     * @return
     */
/*    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> list1 = new ArrayList<>();
        if (root == null) {
            return list1;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        //用来记录左右的
        boolean isOrderLeft = true;
        while (!queue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                TreeNode node = queue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(node.val);
                } else {
                    levelList.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);

                }
            }
            list1.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return list1;
    }*/


}
