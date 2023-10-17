package com.iori.datastructure.avltree;


import java.util.Stack;

/**
 * <h3>AVL 树</h3>
 * <ul>
 *     <li>二叉搜索树在插入和删除时，节点可能失衡</li>
 *     <li>如果在插入和删除时通过旋转, 始终让二叉搜索树保持平衡, 称为自平衡的二叉搜索树</li>
 *     <li>AVL 是自平衡二叉搜索树的实现之一</li>
 * </ul>
 */
public class AVLTreeLianXi {

    AVLNode root;

    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height; //高度

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(AVLNode left, AVLNode right) {
            this.left = left;
            this.right = right;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * 求节点的高度
     *
     * @param node
     * @return
     */
    private int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 更新节点高度(新增,删除,旋转)
     *
     * @param node
     */
    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 求左右子树的高度差
     * 平衡因子(balance factor) = 左子树高度 - 右子树高度
     *
     * @return
     */
    private int balanceFactor(AVLNode node) {
        return height(node.left) - height(node.right);
    }
    /*
        0 1 -1 平衡的
        >1 < -1不平衡
     */
    /*
        LL 左左
            -失衡节点(图中5 红色) 的balanceFactor > 1 即左边更高
            -失衡节点的左孩子(图中3 黄色) 的 balanceFactor >= 0 即左孩子这边也是左边更高或等高
        LR  左右
            -失衡节点(图中6) 的balanceFactor > 1 即左边更高
            -失衡节点的左孩子(图中2 红色) 的 balanceFactor < 0 即左孩子这边是右边更高
        RL 右左
            -失衡节点(图中2) 的balanceFactor < 1 即右边更高
            -失衡节点的右孩子(图中6 红色) 的 balanceFactor > 0 即右孩子这边是左边更高
        RR 右右
            -失衡节点(图中2 红色) 的balanceFactor < -1 即右边更高
            -失衡节点的右孩子(图中6 黄色) 的 balanceFactor <= 0 即右孩子这边是右边更高或等高
     */

    /**
     * @param red 要旋转的节点
     * @return 新的根节点
     */
    private AVLNode rightRotate(AVLNode red) {
        AVLNode yellow = red.left;
        red.left = yellow.right;
        yellow.right = red;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    /**
     * @param red 要旋转的节点
     * @return 新的根节点
     */
    private AVLNode leftRotate(AVLNode red) {
        AVLNode yellow = red.right;
        red.right = yellow.left;
        yellow.left = red;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    /**
     * 先左旋左子树 在右旋根节点
     *
     * @param node
     * @return
     */
    private AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    /**
     * 先右旋右子树 在左旋根节点
     *
     * @param node
     * @return
     */
    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    /**
     * 检查节点是否失衡 重新平衡代码
     *
     * @param node
     * @return
     */
    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = balanceFactor(node);
        if (bf > 1 && balanceFactor(node.left) >= 0) {
            return rightRotate(node);
        } else if (bf > 1 && balanceFactor(node.left) < 0) {
            return leftRightRotate(node);
        } else if (bf < -1 && balanceFactor(node.right) > 0) {
            return rightLeftRotate(node);
        } else if (bf < -1 && balanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        return node;
    }


    /**
     * 添加元素  非递归写法
     *
     * @param key
     * @param value
     */
    public void put(int key, Object value) {
        root = doPut(root, key, value);
    }

    /**
     * 添加递归实现
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private AVLNode doPut(AVLNode node, int key, Object value) {
        //如果为null  创建一个新节点返回
        if (node == null) {
            return new AVLNode(key, value);
        }
        //如果找到 key了
        if (key == node.key) {
            node.value = value;
            return node;
        }
        //3. 继续查找
        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else {
            node.right = doPut(node.right, key, value);
        }
        updateHeight(node);
        return balance(node);
    }

    /**
     * 删除方法
     *
     * @param key
     */
    public void remove(int key) {
        doRemove(root, key);
    }

    /**
     * 递归删除
     *
     * @param node
     * @param key
     * @return
     */
    private AVLNode doRemove(AVLNode node, int key) {
        //1 节点为 null
        if (node == null) {
            return null;
        }
        //2.没找到
        if (key < node.key) {
            node = doRemove(node.left, key);
        } else if (key > node.key) {
            node = doRemove(node.right, key);
        } else {
            //3 找到了 1)只有有左孩子 2)只有右孩子 3)左右都有
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
                s.right = doRemove(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }

        updateHeight(node);
        return balance(node);
    }

}
