package com.iori.review.review04;


/**
 * AVL树
 */
public class Review25 {

    //AVL节点类
    static class AVLNode {
        int height = 1;
        int key;
        Object value;
        AVLNode left;
        AVLNode right;

        public AVLNode() {
        }

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    //求二叉树高度方法
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    //更新二叉树高度代码
    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    //失衡判断方法
    private int bf(AVLNode node) {
        /*
            平衡因子 = 左子树高度 - 右子树高度
            bf 返回 0 , 1 , -1 时 表示左右平衡
            bf > 1 时 表示左边太高
            bf < -1 时 表示右边太高
         */
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    //右旋代码
    private AVLNode rightRotate(AVLNode red) {
        AVLNode yellow = red.left;
        red.left = yellow.right;
        yellow.right = red;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    //左旋代码
    private AVLNode leftRotate(AVLNode red) {
        AVLNode yellow = red.right;
        red.right = yellow.left;
        yellow.left = red;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    //左右旋代码
    private AVLNode leftRightRotate(AVLNode root) {
        root.left = leftRotate(root.left);
        return rightRotate(root.left);
    }

    //右左旋代码
    private AVLNode rightLeftRotate(AVLNode root) {
        root.right = rightRotate(root.right);
        return leftRotate(root.right);
    }


    //判断以及调整平衡代码
    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = bf(node);

        if (bf > 1 && bf(node.left) >= 0) { //LL
            return rightRotate(node);
        } else if (bf > 1 && bf(node.left) < 0) { //LR
            return leftRightRotate(node);
        } else if (bf < -1 && bf(node.right) > 0) { //RL
            return rightLeftRotate(node);
        } else if (bf < -1 && bf(node.right) <= 0) { //RR
            return leftRotate(node);
        }
        return node;
    }

    AVLNode root;

    //新增节点方法
    public void put(int key, Object value) {
        root = doPut(root, key, value);
    }

    private AVLNode doPut(AVLNode node, int key, Object value) {
        if (node == null) {
            return new AVLNode(key, value);
        }
        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        }
        if (key > node.key) {
            node.right = doPut(node.right, key, value);
        } else {
            node.value = value;
            return node;
        }
        //更新树的高度
        updateHeight(node);
        //返回平衡后的树
        return balance(node);
    }

    //删除节点方法
    public void remove(int key) {
        root = doRemove(root, key);
    }

   /* private AVLNode doRemove(AVLNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.leaf = doRemove(node.leaf, key);
        } else if (key > node.key) {
            node.right = doRemove(node.right, key);
        } else {
            if (node.leaf == null) {
                return node.right;
            } else if (node.right == null) {
                return node.leaf;
            } else {
                AVLNode s = node.right;
                while (s.leaf != null) {
                    s = s.leaf;
                }
                s.right = doRemove(node.right, s.key);
                s.leaf = node.leaf;
            }
        }

        updateHeight(node);
        return balance(node);
    }*/

      /**
     * 递归删除
     *
     * @param node
     * @param key
     * @return
     */
    private AVLNode doRemove(AVLNode node, int key) {
        //1.node == null
        if (node == null) {
            return null;
        }
        //2.没找到key
        if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else if (key > node.key) {
            node.right = doRemove(node.right, key);
        } else {
            //3.找到key了 1)没有孩子 2)只有一个孩子 3)有两个孩子
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                //s 代表后继节点
                s.right = doRemove(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }
        //4.更新高度
        updateHeight(node);
        //5.balance
        return balance(node);
    }


}
