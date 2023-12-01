package com.iori.datastructure.avltree;


import java.util.LinkedList;

/**
 * <h3>AVL 树</h3>
 * <ul>
 *     <li>二叉搜索树在插入和删除时，节点可能失衡</li>
 *     <li>如果在插入和删除时通过旋转, 始终让二叉搜索树保持平衡, 称为自平衡的二叉搜索树</li>
 *     <li>AVL 是自平衡二叉搜索树的实现之一</li>
 * </ul>
 */
public class AVLTreeLianXi {

    //节点类
    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1; //高度

        public AVLNode() {
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    //求节点的高度
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    //更新节点高度(新增,删除,旋转)
    private void updateHeight(AVLNode node) {
        //取左右孩子里最大的高度 + 1
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    //求左右子树的高度差
    private int balanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    //LL不平衡 右旋方法
    private AVLNode rightRotate(AVLNode red) {
        AVLNode yellow = red.left;
        red.left = yellow.right;
        yellow.right = red;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    //RR不平衡 左旋方法
    private AVLNode leftRotate(AVLNode red) {
        AVLNode yellow = red.right;
        red.right = yellow.left;
        yellow.left = red;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    //LR不平衡  先旋转左子树 在右旋根节点
    private AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    //RL不平衡 先右旋右子树 在左旋根节点
    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    //检查节点是否失衡 重新平衡
    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int balanceFactor = balanceFactor(node);
        if (balanceFactor > 1 && balanceFactor(node.left) >= 0) { //LL
            return rightRotate(node);
        } else if (balanceFactor > 1 && balanceFactor(node.left) < 0) { //LR
            return leftRightRotate(node);
        } else if (balanceFactor < -1 && balanceFactor(node.right) > 0) { //RL
            return rightLeftRotate(node);
        } else if (balanceFactor < -1 && balanceFactor(node.right) <= 0) { //RR
            return leftRotate(node);
        }
        return node;
    }

    AVLNode root;

    //添加节点方法 递归
    public void put(int key, Object value) {
        //递归实现
        root = doPut(root, key, value);
        //非递归实现
        put(root, key, value);
    }

    //非递归实现添加
    private void put(AVLNode node, int key, Object value) {
        AVLNode newNode = new AVLNode(key, value);
        if (root == null) {
            root = newNode;
            return;
        }
        AVLNode p = root;
        LinkedList<AVLNode> stack = new LinkedList<>();
        while (p != null) {
            stack.push(p);
            if (key < p.key) {
                if (p.left == null) {
                    p.left = newNode;
                    break;
                }
                p = p.left;
            } else if (key > p.key) {
                if (p.right == null) {
                    p.right = newNode;
                    break;
                }
                p = p.right;
            } else {
                p.value = value;
                return;
            }
        }

        //平衡avl树
        while (!stack.isEmpty()) {
            AVLNode pop = stack.pop();
            updateHeight(pop);
            pop = balance(pop);
            if (stack.isEmpty()) {
                root = pop;
            } else {
                AVLNode parent = stack.peek();
                if (parent.left == pop) {
                    parent.left = pop;
                } else {
                    parent.right = pop;
                }
            }
        }

    }

    //递归方法
    private AVLNode doPut(AVLNode node, int key, Object value) {
        if (node == null) {
            return new AVLNode(key, value);
        }

        if (key == node.key) {
            node.value = value;
            return node;
        }

        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else {
            node.right = doPut(node.right, key, value);
        }
        updateHeight(node);
        return balance(node);
    }

    public void remove(int key) {
        //递归删除
        root = doRemove(root, key);
        //迭代删除
        remove(root, key);
    }

    //迭代删除
    private void remove(AVLNode node, int key) {
        if (node == null) {
            return;
        }
        AVLNode p = node;
        AVLNode parent = null;
        LinkedList<AVLNode> stack = new LinkedList<>();
        while (p != null && p.key != key) {
            stack.push(p);
            if (key < p.key) {
                parent = p;
                p = p.left;
            } else {
                parent = p;
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }
        if (p.left == null && p.right == null) {
            if (p == root) {
                root = null;
            } else if (parent.left == p) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (p.left != null && p.right != null) {
            AVLNode successor = p.right;
            while (successor.left != null) {
                stack.push(successor);
                successor = successor.left;
            }
            p.key = successor.key;
            p = successor;
            parent = stack.isEmpty() ? null : stack.peek();
        } else {
            AVLNode child = p.left != null ? p.left : p.right;
            if (p == root) {
                root = child;
            } else if (parent.left == p) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
        //平衡栈中的元素
        while (!stack.isEmpty()) {
            AVLNode pop = stack.pop();
            pop = balance(pop);
            if (stack.isEmpty()) {
                root = pop;
            } else {
                AVLNode peek = stack.peek();
                if (peek.left == pop) {
                    peek.left = pop;
                } else {
                    peek.right = pop;
                }
            }
        }
    }

    //递归删除方法
    private AVLNode doRemove(AVLNode node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else if (key > node.key) {
            node.right = doRemove(node.right, key);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node =  node.left;
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
