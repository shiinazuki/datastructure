package com.iori.datastructure.binarytree;


import com.iori.datastructure.stack.LinkedListStack;

/**
 * 二叉树的前序 中序 后序遍历
 * 前序遍历 先访问该节点 然后是左子树 最后是右子树
 * 中序遍历 先访问左子树 然后是该节点 最后是右子树
 * 后序遍历 先访问左子树 然后是右子树 最后是该节点
 */
public class TreeTraversal {
    public static void main(String[] args) {
           /*
                1
               / \
              2   3
             /   / \
            4   5   6
         */
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, new TreeNode(7)),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))

        );
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();

        //循环 前序 或 中序 遍历
        //preOrderOrinOrder(root);

        //后序遍历 非递归
        //postOrderNoRecursion(root);


        //前序  中序  后序 遍历  非递归
        preOrderOrinOrderOrPostOrder(root);


    }


    /**
     * 前序  中序  后序 遍历  非递归
     * 三种遍历 一种写法
     *
     * @param node 节点
     */
    public static void preOrderOrinOrderOrPostOrder(TreeNode node) {

        TreeNode curr = node;
        LinkedListStack<TreeNode> stack = new LinkedListStack<>();
        TreeNode pop = null;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                //待处理左子树
                //colorPrintln("前序:" + curr.val, 31); //前序遍历打印时机
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                //没有右子树
                if (peek.right == null) {
                    //中序遍历打印时机
                    //colorPrintln("中序:" + peek.val, 36);
                    pop = stack.pop();
                    //后序遍历打印时机
                    colorPrintln("后序:" + pop.val, 34);
                }
                //右子树处理完成
                else if (peek.right == pop) {
                    pop = stack.pop();
                    //后序遍历打印时机
                    colorPrintln("后序:" + pop.val, 34);
                }
                //处理节点的右子树
                else {
                    //中序遍历打印时机
                    //colorPrintln("中序:" + peek.val, 36);
                    curr = peek.right;
                }
            }
        }

    }


    /**
     * 前序 或 中序 遍历  非递归
     * 循环
     *
     * @param node 节点
     */
    public static void preOrderOrinOrder(TreeNode node) {

        LinkedListStack<TreeNode> stack = new LinkedListStack<>();
        TreeNode curr = node; //代表当前节点
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) { //去  这里就是前序遍历
                colorPrintln("前序:" + curr.val, 31);
                stack.push(curr); //当前节点加入栈 为了记住回来的路
                curr = curr.left;
            } else {  //回 这里就是中序遍历
                TreeNode pop = stack.pop();
                colorPrintln("中序:" + pop.val, 34);
                curr = pop.right;
            }

        }
    }


    /**
     * 后序 遍历  非递归
     * 循环
     *
     * @param node 节点
     */
    public static void postOrderNoRecursion(TreeNode node) {

       /* LinkedListStack<TreeNode> stack = new LinkedListStack<>();
        TreeNode curr = node; //代表当前节点
        //代表最近一次弹栈的元素
        TreeNode pop = null;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) { //去  这里就是前序遍历
                //colorPrintln("前序:" + curr.val, 31);
                stack.push(curr); //当前节点加入栈 为了记住回来的路
                curr = curr.left;
            } else {  //回 这里判断一下 就是后序遍历
                //先 peek 栈顶元素
                TreeNode peek = stack.peek();
                //表示右子树已经处理完成
                if (peek.right == null || peek.right == pop) {
                    //记录下来最近一次弹栈的元素
                    pop = stack.pop();
                    colorPrintln("后序:" + pop.val, 34);
                } else {
                    curr = peek.right;
                }
            }

        }*/


        TreeNode curr = node;
        //使用栈结构 来记录父节点位置
        LinkedListStack<TreeNode> stack = new LinkedListStack<>();
        TreeNode pop = null;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                //colorPrintln("前序:" + curr.val, 31);
                //把当前节点压入栈中
                stack.push(curr);
                curr = curr.left;
            } else {  //后序需要在这个基础上修改一下
                //先从栈中 peek一个元素
                TreeNode peek = stack.peek();
                //从栈中取出  并将右节点赋给 curr
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                    colorPrintln("后序:" + pop.val, 34);
                } else {
                    curr = peek.right;
                }
            }
        }


    }


    /**
     * 前序遍历  递归
     *
     * @param node 节点
     */
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + "\t"); //值
        preOrder(node.left); //左
        preOrder(node.right); //右

    }


    /**
     * 中序遍历  递归
     *
     * @param node 节点
     */
    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left); //左
        System.out.print(node.val + "\t"); //值
        inOrder(node.right); //右
    }

    /**
     * 后序遍历  递归
     *
     * @param node 节点
     */
    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left); //左
        postOrder(node.right); //右
        System.out.print(node.val + "\t");  //值
    }


    /*
    31 红
    32 黄
    33 橙
    34 蓝
    35 紫
    36 绿
 */
    public static void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

}
