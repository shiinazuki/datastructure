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
public class AVLTree {

    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1; //高度


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

    /**
     * 求节点的高度
     *
     * @param node
     * @return
     */
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 更新节点高度(新增,删除,旋转)
     *
     * @param node
     */
    private void updateHeight(AVLNode node) {
        //取左右孩子里最大的高度
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 求左右子树的高度差
     * 平衡因子(balance factor) = 左子树高度 - 右子树高度
     *
     * @return
     */
    private int balanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
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
        AVLNode green = yellow.right;
        yellow.right = red;  //上位
        red.left = green;  //换爹
        updateHeight(red); //更新节点高度
        updateHeight(yellow); //更新节点高度
        return yellow;

    }

    /**
     * @param red 要旋转的节点
     * @return 新的根节点
     */
    private AVLNode leftRotate(AVLNode red) {
        AVLNode yellow = red.right;
        AVLNode green = yellow.left;
        yellow.left = red;
        red.right = green;
        updateHeight(red);//更新节点高度
        updateHeight(yellow);//更新节点高度
        return yellow;
    }

    /**
     * 先左旋左子树 在右旋根节点
     *
     * @param node
     * @return
     */
    private AVLNode leftRightRotate(AVLNode node) {
        //先把失衡节点的左子树进行左旋 并重新赋给根节点
        node.left = leftRotate(node.left);
        //将整个失衡的根节点进行右旋
        return rightRotate(node);
    }

    /**
     * 先右旋右子树 在左旋根节点
     *
     * @param node
     * @return
     */
    private AVLNode rightLeftRotate(AVLNode node) {
        //先把失衡节点的右子树进行右旋 并重新赋给根节点
        node.right = rightRotate(node.right);
        //将整个失衡的根节点进行左旋
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

    /**
     * 添加元素  非递归写法
     *
     * @param key
     * @param value
     */

    public void put(int key, Object value) {
        AVLNode newNode = new AVLNode(key, value);
        //如果根节点为空 直接把新节点赋值返回
        if (root == null) {
            root = newNode;
            return;
        }
        AVLNode current = root;
        Stack<AVLNode> stack = new Stack<>();
        //循环找插入位置 并插入
        while (current != null) {
            stack.push(current);
            if (key < current.key) {
                if (current.left == null) {
                    current.left = newNode;
                    break;
                }
                current = current.left;
            } else if (key > current.key) {
                if (current.right == null) {
                    current.right = newNode;
                    break;
                }
                current = current.right;
            } else {
                //key 已经存在 更新值
                current.value = value;
                return;
            }
        }
        //平衡avl树
        while (!stack.isEmpty()) {
            //从栈中取出节点
            AVLNode node = stack.pop();
            //更新节点高度
            updateHeight(node);
            //检查节点是否失衡 并拿到平衡后的节点
            node = balance(node);
            //如果栈为空 直接将node赋值给根节点
            if (stack.isEmpty()) {
                root = node;
            } else {
                //从栈中获取栈顶元素
                AVLNode parent = stack.peek();
                //如果父节点的左子树等于node 就将平衡后的node重新赋值父节点的左子树  否则赋值给父节点的右子树
                if (parent.left == node) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }
        }
    }


    /*
    public void put(int key, Object value) {
        root = doPut(root, key, value);
    }*/

    /**
     * 添加递归实现
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private AVLNode doPut(AVLNode node, int key, Object value) {
        //1. 找到空位了 创建新节点
        if (node == null) {
            return new AVLNode(key, value);
        }
        //2. key 已存在 更新
        if (key == node.key) {
            node.value = value;
            return node;
        }

        //3. 继续查找
        if (key < node.key) {
            node.left = doPut(node.left, key, value); //向左
        } else {
            node.right = doPut(node.right, key, value); //向右
        }
        //更新节点高度
        updateHeight(node);
        //重新调整节点平衡
        return balance(node);
    }

    /**
     * 删除方法 非递归实现
     *
     * @param key
     */

    /*
    public void remove(int key) {
        if (root == null) {
            return;
        }

        AVLNode current = root;
        AVLNode parent = null; //用来保存父节点
        Stack<AVLNode> stack = new Stack<>();

        while (current != null && current.key != key) {
            stack.push(current);
            if (key < current.key) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }
        //key 不存在
        if (current == null) {
            return;
        }

        //如果左右子树都等于null
        if (current.left == null && current.right == null) {
            //如果和root节点相同 直接将root置空
            if (current == root) {
                root = null;
            } else if (parent.left == current) { //如果在父节点的左边
                parent.left = null;
            } else {  //如果在父节点的右边
                parent.right = null;
            }
        } else if (current.left != null && current.right != null) {
            //后继节点
            AVLNode successor = current.right;
            //找到最左边的
            while (successor.left != null) {
                stack.push(successor);  //放入栈中
                successor = successor.left;
            }
            current.key = successor.key;
            current = successor;
            parent = stack.isEmpty() ? null : stack.peek();

        } else {
            AVLNode child = current.left != null ? current.left : current.right;
            if (current == root) {
                root = child;
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }

        //平衡栈中的元素
        while (!stack.isEmpty()) {
            AVLNode node = stack.pop();
            //拿到平衡后的节点
            node = balance(node);

            //如果栈空 将node 赋值给root
            if (stack.isEmpty()) {
                root = node;
            } else {
                AVLNode parent1 = stack.peek();
                if (parent1.left == node) {
                    parent1.left = node;
                } else {
                    parent1.right = node;
                }
            }
        }
    }

    */



    public void remove(int key) {
        root = doRemove(root, key);
    }

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
