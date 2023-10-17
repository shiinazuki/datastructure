package com.iori.datastructure.redblacktree;

import static com.iori.datastructure.redblacktree.RedBlackTreeLianXi.Color.BLACK;
import static com.iori.datastructure.redblacktree.RedBlackTreeLianXi.Color.RED;

/**
 * <h3>红黑树</h3>
 */
public class RedBlackTreeLianXi {

    //左倾红黑树 2-3树

    enum Color {
        RED, BLACK;
    }

    Node root;

    public static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;
        Color color = Color.RED;

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

        /**
         * 是否是左孩子
         *
         * @return
         */
        boolean isLetChild() {
            return parent != null && parent.left == this;
        }

        /**
         * 是否是叔叔节点
         *
         * @return
         */
        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            if (parent.isLetChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        /**
         * 查找兄弟节点
         *
         * @return
         */
        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (this.isLetChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }

    /**
     * 判断节点是否是红色
     *
     * @param node
     * @return
     */
    boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    /**
     * 判断节点是否是黑色
     *
     * @param node
     * @return
     */
    boolean isBlack(Node node) {
        //return !isRed(node);
        return node == null || node.color == BLACK;
    }

    /**
     * 右旋方法
     * 1.parent 的处理 2.旋转后新根的父子关系
     *
     * @param pink
     */
    private void rightRotate(Node pink) {
        Node parent = pink.parent;

        Node yellow = pink.left;
        Node green = yellow.right;
        //该节点有可能为hull 所以得加判断
        if (green != null) {
            green.parent = pink;  //处理父节点
        }
        yellow.right = pink;
        yellow.parent = parent; //处理父节点
        pink.left = green;
        pink.parent = yellow;  //处理父节点

        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }

    }

    /**
     * 左旋方法
     *
     * @param pink
     */
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

    /**
     * 新增方法
     * 正常增 遇到红红不平衡进行调整
     *
     * @param key   - 键
     * @param value - 值
     */
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
                p.value = value;  //更新
                return;
            }
        }

        Node inserted = new Node(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent; //处理父节点
        } else {
            parent.right = inserted;
            inserted.parent = parent; //处理父节点
        }
        fixRedRed(inserted);
    }


    /**
     * 删除的是双红 后的平衡处理
     *
     * @param node
     */
    private void fixRedRed(Node node) {
        //case 1 插入节点是根节点 变黑即可
        if (node == root) {
            node.color = BLACK;
            return;
        }
        //case 2 插入节点父亲是黑色 无需调整
        if (isBlack(node.parent)) {
            return;
        }
        //插入节点的父亲为红色 触发红红相邻
        //case 3 叔叔为红色
        // 父亲变为黑色 为了保证黑色平衡 连带的叔叔也变成黑色
        // 祖父如果黑色不变 会造成这颗子树黑色过多 因此祖父节点变成红色
        // 祖父如果变成红色 可能会接着触发红红相邻 因此对将祖父及逆行递归调整

        Node parent = node.parent;
        Node uncle = node.uncle();
        Node grandparent = parent.parent;
        if (isRed(uncle)) {
            parent.color = BLACK;
            uncle.color = BLACK;
            grandparent.color = RED;
            fixRedRed(grandparent);
            return;
        }
        //case 4 叔叔为黑色
        //父亲为左孩子 插入节点也是左孩子 此时即LL不平衡
        //父亲为左孩子 插入节点是右孩子 此时即LR不平衡
        //父亲为右孩子 插入节点也是右孩子 此时即RR不平衡
        //父亲为右孩子 插入节点是左孩子 此时即RL不平衡
        if (parent.isLetChild() && node.isLetChild()) { //LL
            //修改颜色
            parent.color = BLACK;
            grandparent.color = RED;
            //右旋
            rightRotate(grandparent);
        } else if (parent.isLetChild() /*&& !node.isLetChild()*/) { //LR
            rightRotate(parent);
            node.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);

        } else if ( /*!parent.isLetChild() && */ !node.isLetChild()) { //RR
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        } else {  //RL
            rightRotate(parent);
            node.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }


    }


    /**
     * 删除
     * 正常删 会用到李代桃僵技巧 遇到黑黑不平衡进行调整
     *
     * @param key 键
     */
    public void remove(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    public boolean contains(int key) {
        return find(key) != null;
    }

    /**
     * 删除的是双黑 后的平衡处理
     *
     * @param node
     */
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
            if (node.isLetChild()) {
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
                // 将兄弟变红  目的是将删除节点和兄弟那边的黑色高度同时减少1
                sibling.color = RED;
                //如果父亲是红 则需将父亲变为黑,避免红红 此时路径黑节点数目不变
                if (isRed(parent)) {
                    parent.color = BLACK;
                } else {
                    //如果父亲是黑 说明这条路径还是少黑,再次让父亲节点触发双黑
                    fixDoubleBlack(parent);
                }
            } else { //case 5 兄弟是黑色 侄子是红色
                //如果兄弟是左孩子 左侄子是红色 LL
                if (sibling.isLetChild() && isRed(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = BLACK;
                    sibling.color = parent.color;
                }
                //如果兄弟是左孩子 右侄子是红色 LR
                else if (sibling.isLetChild() && isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);

                }
                //如果兄弟是右孩子 右侄子是红色 RR
                else if (!sibling.isLetChild() && isRed(sibling.left)) {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                //如果兄弟是右孩子 左侄子是红色 RL
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

    /**
     * 递归删除逻辑
     *
     * @param deleted
     */
    private void doRemove(Node deleted) {
        //拿到删剩下的
        Node replaced = findReplaced(deleted);
        //删除节点的父节点
        Node parent = deleted.parent;
        //没有孩子
        if (replaced == null) {
            //case 1
            if (deleted == root) { ////删除的是根节点
                root = null;
            } else { // 删除的不是根节点 并且没有孩子
                //判断删除节点是不是黑色
                if (isBlack(deleted)) { //是黑色
                    //复杂调整
                    //先调整 在删除 deleted
                    fixDoubleBlack(deleted);
                } else { //要删除的不是黑色
                    //红色叶子 无需任何处理
                }

                if (deleted.isLetChild()) { //被删除节点是左孩子
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
                root.value = replaced.value;
                root.left = null;
                root.right = null;
            } else { //删除的不是根节点 有一个孩子
                if (deleted.isLetChild()) { //被删除节点是父节点的左孩子
                    //将删剩下的给父节点
                    parent.left = replaced;
                } else {//被删除节点是父节点的右孩子
                    //将删剩下的给父节点
                    parent.right = replaced;
                }
                //更改删剩下的父节点
                replaced.parent = parent;
                //将删出节点的左右 父亲都置空
                deleted.left = null;
                deleted.right = null;
                deleted.parent = null;

                //如果被删除的是黑 并且剩下的也是黑
                if (isBlack(deleted) && isBlack(replaced)) {
                    //做复杂处理
                    //先删除了 然后在调整 replaced
                    fixDoubleBlack(replaced);

                } else {
                    //case 2 删的是黑 剩下的是红
                    replaced.color = BLACK;
                }
            }

            return;
        }
        //case 0 有两个孩子 => z转换成只有一个孩子 或者没有孩子的节点
        //交换删除节点 和后继节点的键值
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;

        //递归删除
        doRemove(replaced);

    }


    /**
     * 查找删除节点
     *
     * @param key
     * @return
     */
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

    /**
     * 查找剩余节点
     *
     * @param deleted
     * @return
     */
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

        Node s = deleted;
        while (s.left != null) {
            s = s.left;
        }
        return s;

    }


}