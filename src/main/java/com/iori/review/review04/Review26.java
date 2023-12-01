package com.iori.review.review04;

import static com.iori.review.review04.Review26.Color.RED;
import static com.iori.review.review04.Review26.Color.BLACK;

/**
 * 红黑树
 */
public class Review26 {

    enum Color {
        RED, BLACK;
    }

    Node root;

    //节点类
    public static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;
        Color color = RED;


        public Node(int key) {
            this.key = key;
        }

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node(int key, Color color) {
            this.key = key;
            this.color = color;
        }

        public Node(int key, Color color, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.color = color;
            if (left != null) {
                left.parent = this;
            }
            if (right != null) {
                right.parent = this;
            }
        }

        //是否是左孩子
        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        //获取叔叔节点
        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        //查找兄弟节点
        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }

    //判断节点是否是红色
    boolean isRed(Node node) {
        return node == null && node.color == RED;
    }

    //判断节点是否是黑色
    boolean isBlack(Node node) {
        //return !isRed(node);
        return node == null || node.color == BLACK;
    }

    //右旋方法
    private void rightRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.left;
        Node green = yellow.right;
        if (green != null) {
            green.parent = pink;
        }
        yellow.right = pink;
        yellow.parent = parent;
        pink.left = green;
        pink.parent = yellow;

        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    //左旋方法
    private void leftRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.right;
        Node green = yellow.left;
        if (green != null) {
            green.parent = pink;
        }
        yellow.left = pink;
        yellow.parent = parent;
        pink.right = green;
        pink.parent = yellow;
        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    //新增方法
    public void put(int key, Object value) {
        Node p = root;
        Node parent = null;

        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                p.value = value;
                return;
            }
        }

        Node inserted = new Node(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }
        fixRedRed(inserted);
    }

    private void fixRedRed(Node node) {
        //插入节点是根节点 变黑即可
        if (node == root) {
            node.color = BLACK;
            return;
        }
        //插入节点父亲是黑色 无需调整
        if (isBlack(node.parent)) {
            return;
        }
        //插入节点的父亲为红色 触发红红相邻
        Node parent = node.parent;
        Node uncle = node.uncle();
        Node grandparent = parent.parent;
        //叔叔为红色
        if (isRed(uncle)) {
            //父亲变为黑色 为了保证黑色平衡 来带的叔叔也变成黑色
            parent.color = BLACK;
            uncle.color = BLACK;
            //祖父如果黑色不变 会造成这颗子树黑色过多 因此祖父节点变成红色
            grandparent.color = RED;
            //祖父如果变成红色 可能会接着触发红红相邻 因此对祖父进行递归调整
            fixRedRed(grandparent);
            return;
        }

        //叔叔为黑色
        //父亲为左孩子 插入节点也是左孩子 此时即LL不平衡
        if (parent.isLeftChild() && node.isLeftChild()) {
            parent.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        }
        //父亲为左孩子 插入节点是右孩子 此时即LR不平衡
        else if (parent.isLeftChild() && !node.isLeftChild()) {
            leftRotate(parent);
            node.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        }
        //父亲为右孩子 插入几点也是右孩子 此时即RR不平衡
        else if (!parent.isLeftChild() && !node.isLeftChild()) {
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }
        //父亲为右孩子 插入节点是左孩子 此时即RL不平衡
        else {
            rightRotate(parent);
            node.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }
    }

    //删除节点
    public void remove(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    //删除的是双黑 后的平衡处理
    private void fixDoubleBlack(Node node) {
        //调整到树的顶部了
        if (node == root) {
            return;
        }
        //拿到父亲和兄弟
        Node parent = node.parent;
        Node sibling = node.sibling();
        //case 3 被调整节点的兄弟是红色
        if (isRed(sibling)) {
            //如果被调整节点是左孩子 就左旋 是右孩子就右旋
            if (node.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = RED;
            sibling.color = BLACK;
            //递归
            fixDoubleBlack(node);
            return;
        }
        //case 4 被调整节点的兄弟是黑色 两个侄子都是黑色
        if (sibling != null) {
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //将兄弟变红 目的是将删除节点和兄弟那边的黑色高度同时减少1
                sibling.color = RED;
                //如果父亲是红 则需要将父亲变黑 避免红红 此时路径黑节点数目不变
                if (isRed(parent)) {
                    parent.color = BLACK;
                } else {
                    //如果父节点是黑 说明这条路径还是少黑,再次让父亲节点触发双黑
                    fixDoubleBlack(parent);
                }
            } else { //case 5 兄弟是黑色 侄子是红色
                //如果兄弟是左孩子 左侄子是黑色 LL
                if (sibling.isLeftChild() && isRed(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = BLACK;
                    sibling.color = parent.color;
                }
                //如果兄弟是左孩子 右侄子是红色 LR
                else if (sibling.isLeftChild() && isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                //如果兄弟是右孩子 右侄子是红色 RL
                else if (!sibling.isLeftChild() && isRed(sibling.left)) {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                //如果兄弟是右孩子 左侄子是红色 RR
                else {
                    leftRotate(parent);
                    sibling.right.color = BLACK;
                    sibling.color = parent.color;
                }
                parent.color = BLACK;
            }
        } else {
            //让父亲去做递归调整
            fixDoubleBlack(parent);
        }

    }

    //递归删除逻辑
    private void doRemove(Node deleted) {
        //拿到删剩下的
        Node replaced = findReplaced(deleted);
        //删除节点的父节点
        Node parent = deleted.parent;
        //没有孩子
        if (replaced == null) {
            //case 1
            if (deleted == root) { //删除的是根节点
                root = null;
            } else { //删除的不是根节点 并且没有孩子
                //判断删除节点是不是黑色
                if (isBlack(deleted)) { //是黑色
                    //触发双黑
                    fixDoubleBlack(deleted);
                } else { //不是黑色
                    //红色叶子 无需任何处理
                }
                //被删除节点是左孩子
                if (deleted.isLeftChild()) {
                    //父节点的左孩子置空
                    parent.left = null;
                } else { //删除节点是右孩子
                    //父节点的右孩子置空
                    parent.right = null;
                }
                //删除节点的父亲置空
                deleted.parent = null;
            }
            return;
        }
        //有一个孩子的情况
        if (deleted.left == null || deleted.right == null) {
            //case 1
            if (deleted == root) { //删除的是根节点
                root.key = replaced.key;
                ;
                root.value = replaced.value;
                root.left = null;
                root.right = null;
            } else { //删除的不是根节点 有一个孩子
                //被删除节点的父节点是左孩子
                if (deleted.isLeftChild()) {
                    //将删剩下的给父节点
                    parent.left = replaced;
                } else { //被删除节点是父节点的右孩子
                    //将删剩下的给父节点
                    parent.right = replaced;
                }
                //更改删剩下的父节点
                replaced.parent = parent;
                //将删除节点的左右 父亲都置空
                deleted.left = null;
                deleted.right = null;
                deleted.parent = null;

                //如果被删除的是黑 并且剩下的也是黑
                if (isBlack(deleted) && isBlack(replaced)) {
                    //先删了 然后再调整replaced
                    fixDoubleBlack(replaced);
                } else {
                    //case 2 删的是黑 剩下的是红
                    replaced.color = BLACK;
                }
            }
            return;
        }
        //case 0 有两个孩子 => 转换成只有一个孩子 或者没有孩子的节点
        //交换删除节点和后继节点的键值
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;
        //递归删除
        doRemove(replaced);
    }


    //查找删除节点
    private Node find(int key) {
        Node p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    //查找剩余节点
    private Node findReplaced(Node deleted) {
        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        Node s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }


}
