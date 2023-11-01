package com.iori.datastructure.redblacktree;

import static com.iori.datastructure.redblacktree.RedBlackTreeLianXi.Color.BLACK;
import static com.iori.datastructure.redblacktree.RedBlackTreeLianXi.Color.RED;

/**
 * <h3>红黑树</h3>
 */
public class RedBlackTreeLianXi {

    enum Color {
        RED, BLACK;
    }

    static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;
        Color color = RED;


        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        //是否是左孩子
        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        //叔叔节点
        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            if ((parent.isLeftChild())) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        //兄弟节点
        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (parent.left == this) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }

    Node root;


    //判断节点是红
    boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    //判断节点是黑
    boolean isBlack(Node node) {
        return node == null || node.color == BLACK;
    }

    //右旋
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

    //左旋
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

    //添加方法
    public void put(int key, int value) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                //找到了 替换
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

        //平衡
        fixRedRed(inserted);

    }

    //红红相邻 平衡
    private void fixRedRed(Node node) {
        //如果是根节点 直接变黑
        if (node == root) {
            node.color = BLACK;
            return;
        }
        //如果父节点是黑色 则不用变
        if (isBlack(node.parent)) {
            return;
        }
        //走到这里说明父节点是红色
        //父节点是红色  看叔叔节点是红色吗 是直接变色 不是进行旋转调整
        Node parent = node.parent;
        Node uncle = node.uncle();
        Node grandparent = parent.parent;
        if (isRed(uncle)) {
            parent.color = BLACK;
            uncle.color = BLACK;
            grandparent.color = RED;

            //有可能祖父节点也触发红红 递归调整 直到根节点
            fixRedRed(grandparent);
            return;
        }

        //叔叔节点不是红色 分四种情况
        //父节点是左孩子  插入节点也是左孩子 LL
        if (parent.isLeftChild() && node.isLeftChild()) {
            parent.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        }
        //父节点是左孩子  插入节点是右孩子 LR
        else if (parent.isLeftChild() && !node.isLeftChild()) {
            leftRotate(parent);
            node.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        }
        //父节点是右孩子  插入节点是左孩子 RL
        else if (!parent.isLeftChild() && node.isLeftChild()) {
            rightRotate(parent);
            node.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }
        //父节点是右孩子  插入节点也是右孩子 RR
        else {
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }


    }


    //删除
    public void remove(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }

        doRemove(deleted);

    }

    //递归删除
    private void doRemove(Node deleted) {
        //先拿到剩下的
        Node replaced = findReplaced(deleted);
        //拿到待删除节点的父亲
        Node parent = deleted.parent;

        //case 1 被删除节点没有孩子
        if (replaced == null) {
            //被删除的是根节点
            if (deleted == root) {
                root = null;
            } else {

                //删除的是黑色
                if (isBlack(deleted)) {
                    //复杂调整
                    fixDoubleBlack(deleted);
                } else {
                    //删除的是红色

                }

                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.left = deleted.right = deleted.parent = null;


            }

            return;
        }


        //case 2 被删除节点有一个孩子
        if (deleted.left == null || deleted.right == null) {
            if (deleted == root) {
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null;
            } else {
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;

                //删除的是黑色  并且剩下的也是黑
                if (isBlack(deleted) && isBlack(replaced)) {
                    //复杂调整
                    fixDoubleBlack(replaced);
                } else { //删除的是黑色 剩下的是红色
                    //case 2
                    replaced.color = BLACK;
                }
            }

            return;
        }


        //case 3 被删除节点有两个孩子  //转换成一个孩子 或者 没有孩子

        int temp = deleted.key;
        deleted.key = replaced.key;
        replaced.key = temp;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;

        //递归删除
        doRemove(deleted);
    }

    //删除节点和剩下节点都是黑色的 触发双黑
    private void fixDoubleBlack(Node node) {
        if (node == root) {
            return;
        }


        Node parent = node.parent;
        Node sibling = node.sibling();

        //case 3 兄弟节点是红色
        if (isRed(sibling)) {
            //被调整节点是左孩子 左旋
            if (node.isLeftChild()) {
                leftRotate(parent);
            } else { //是右孩子 右旋
                rightRotate(parent);
            }
            //父亲变红 兄弟变黑
            parent.color = RED;
            sibling.color = BLACK;
            //递归
            fixDoubleBlack(node);
            return;
        }
        //兄弟节点不为null
        if (sibling != null) {
            //case 4 兄弟是黑色 并且两侄子都是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = RED;
                if (isRed(parent)) {//如果父亲是红色 直接变黑
                    parent.color = BLACK;
                } else {
                    fixDoubleBlack(parent); //否则递归继续触发双黑
                }
            }
            //case 5 兄弟是黑色 侄子是红色
            else {
                //兄弟是左孩子 左侄子是红色 LL
                if (sibling.isLeftChild() && isRed(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = BLACK;
                    sibling.color = parent.color;
                }
                //兄弟是左孩子 右侄子是红色 LR
                else if (sibling.isLeftChild() && isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                //兄弟是右孩子 右侄子是红色 RR
                else if (!sibling.isLeftChild() && isRed(sibling.right)) {
                    leftRotate(parent);
                    sibling.right.color = BLACK;
                    sibling.color = parent.color;
                }
                //兄弟是右孩子 左侄子是红色 RL
                else {
                    sibling.left.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);

                }
                parent.color = BLACK;
            }
        } else {
            fixDoubleBlack(parent);
        }


    }

    //查找删除节点
    public Node find(int key) {
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

    //查找后继节点 删剩下的
    public Node findReplaced(Node deleted) {

        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        Node s = deleted;
        while (s.left != null) {
            s = s.left;
        }
        return s;

    }

}


































