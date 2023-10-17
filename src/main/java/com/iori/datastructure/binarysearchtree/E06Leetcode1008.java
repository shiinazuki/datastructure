package com.iori.datastructure.binarysearchtree;

/**
 * 根据前序遍历构造二叉搜索树
 */
public class E06Leetcode1008 {

    public static void main(String[] args) {
        /*
                8
               / \
              5   10
             / \   \
            1   7  12
         */
        TreeNode t1 = new E06Leetcode1008().bstFromPreorder3(new int[]{8, 5, 1, 7, 10, 12});
//        TreeNode t1 = new E06Leetcode1008().bstFromPreorder(new int[]{8, 5, 7});
        TreeNode t2 = new TreeNode(8, new TreeNode(5, new TreeNode(1), new TreeNode(7)), new TreeNode(10, null, new TreeNode(12)));
        System.out.println(isSameTree(t1, t2));
    }

    public static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }


    //分治法
    /*
        8, 5, 1, 7, 10, 12

        根8, 左 5, 1, 7, 右 10, 12
        根 5 左 1 右 7
        根10 左null 右 12

     */

    public TreeNode bstFromPreorder3(int[] preorder) {
        return partition(preorder,0, preorder.length - 1);
    }

    private TreeNode partition(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);
        int index = start + 1;
        while (index <= end) {
            if (preorder[index] > preorder[start]) {
                break;
            }
            index++;
        }
        //index 就是右子树的起点
        root.left = partition(preorder, start + 1, index - 1);
        root.right = partition(preorder, index, end);
        return root;
    }

    /**
     * 上限法
     *
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        return insert(preorder, Integer.MAX_VALUE);
    }

    //上限法  8,5,1,7,10,12
    /*
        1.遍历数组中每一个值,根据值创建节点
            - 每个节点若成功创建都有: 左孩子上限,右孩子上限
        2.处理下一个值时,如果超过此上限,那么
            - 将null作为上个节点的孩子
            - 不能创建节点对象
            - 知道不超过上限为止
        3. 重复1. 2两步
     */

    int i = 0;

    private TreeNode insert(int[] preorder, int max) {
        if (i == preorder.length) {
            return null;
        }
        int val = preorder[i];
        if (val > max) {
            return null;
        }
        TreeNode node = new TreeNode(val);
        i++;

        node.left = insert(preorder, val);
        node.right = insert(preorder, max);

        return node;
    }


    /**
     * 递归解法
     *
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            int val = preorder[i];
            insert(root, val);
        }
        return root;
    }

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }
        return root;
    }


}
