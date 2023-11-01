package com.iori.datastructure.btree;

import java.util.Arrays;

/**
 * <h3>B-树练习</h3>
 */
public class BTreeLianXi {


    static class Node {
        int[] keys; //关键字
        Node[] children; //孩子
        int keyNumber;//有效关键字个数
        boolean leaf = true; //是否是叶子节点
        int t; //数最小孩子数

        public Node(int[] keys) {
            this.keys = keys;
        }

        public Node(int t) { //t >= 2
            this.t = t;
            keys = new int[2 * t - 1];
            children = new Node[t * 2];
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        //多路查找
        public Node get(int key) {
            int i = 0;
            while (i < keyNumber) {
                if (keys[i] == key) {
                    return this;
                }
                if (keys[i] > key) {
                    break;
                }
                i++;
            }
            if (leaf) {
                return null;
            }
            return children[i].get(key);

        }

        //向指定索引处插入key
        void insertKey(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }

        //向指定索引处插入child
        void insertChild(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }

        /**
         * 移除指定index 处的key
         *
         * @param index
         * @return
         */
        int removeKey(int index) {
            //保存要移除的元素
            int t = keys[index];
            //从要移除元素的下一个元素开始拷贝到原数组的移除元素下边  拷贝有效个数 -1 - 移除元素下标个
            System.arraycopy(keys, index + 1, keys, index, --keyNumber - index);
            return t;
        }

        /**
         * 移除最左边的key
         *
         * @return
         */
        int removeLeftmostKey() {
            return removeKey(0);
        }

        /**
         * 移除最右边的key
         *
         * @return
         */
        int removeRightmostKey() {
            return removeKey(keyNumber - 1);
        }

        /**
         * 移除指定 index 处的 child
         *
         * @param index
         * @return
         */
        Node removeChild(int index) {
            Node t = children[index];
            System.arraycopy(children, index + 1, children, index, keyNumber - index);
            children[keyNumber] = null;
            return t;
        }

        /**
         * 移除最左边的 child
         *
         * @return
         */
        Node removeLeftmostChild() {
            return removeChild(0);
        }

        /**
         * 移除最右边的孩子
         *
         * @return
         */
        Node removeRightmostChild() {
            return removeChild(keyNumber);
        }

        /**
         * 孩子处左边的兄弟
         *
         * @param index
         * @return
         */
        Node childLeftSibling(int index) {
            return index > 0 ? children[index - 1] : null;
        }

        /**
         * 孩子处右边的兄弟
         *
         * @param index
         * @return
         */
        Node childRightSibling(int index) {
            return index == keyNumber ? null : children[index + 1];
        }

        /**
         * 赋值当前节点的所有 key 和 child 到target
         *
         * @param target
         */
        void moveToTarget(Node target) {
            int start = target.keyNumber;
            if (!leaf) {
                for (int i = 0; i <= keyNumber; i++) {
                    target.children[start + i] = children[i];
                }
            }
            for (int i = 0; i < keyNumber; i++) {
                target.keys[target.keyNumber++] = keys[i];
            }
        }

    }

    Node root;
    int t; //数中节点最小度数
    final int MIN_KEY_NUMBER; //最小key数目
    final int MAX_KEY_NUMBER; //最大key数目

    public BTreeLianXi() {
        this(2);
    }

    public BTreeLianXi(int t) {
        this.t = t;
        root = new Node(t);
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    //key是否存在
    boolean containsKey(int key) {
        return root.get(key) != null;
    }

    //新增
    void put(int key) {
        doPut(root, key, null, 0);
    }

    private void doPut(Node node, int key, Node parent, int index) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                return; //更新
            }
            if (node.keys[i] > key) {
                break;
            }
            i++;
        }
        if (node.leaf) {
            node.insertKey(key, i);
        } else {
            doPut(node.children[i], key, node, i);
        }
        //分裂
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);

        }

    }

    //分裂方法
    private void split(Node left, Node parent, int index) {
        //分裂的是根节点
        if (parent == null) {
            Node newNode = new Node(t);
            newNode.leaf = false;
            //旧节点作为新节点的0号孩子
            newNode.insertChild(left, 0);
            this.root = newNode;
            parent = newNode;
        }

        //创建right节点 把t以后的key和child都拷贝过去
        Node right = new Node(t);
        //新节点的leaf属性与旧节点一样 因为是分裂出去的
        right.leaf = left.leaf;
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        //如果不是叶子节点 把孩子也拷贝下
        if (!left.leaf) {
            System.arraycopy(left.children, t, right.children, 0, t);
        }
        right.keyNumber = t - 1;
        left.keyNumber = t - 1;
        //将分裂节点中间的key 插入到父节点
        int mid = left.keys[t - 1];
        parent.insertKey(mid, index);
        //right 节点作为父节点的孩子
        parent.insertChild(right, index + 1);


    }

    //删除
    private void remove(int key) {
        doRemove(null, root, 0, key);
    }

    private void doRemove(Node parent, Node node, int index, int key) {
        int i = 0;
        while (i < root.keyNumber) {
            if (node.keys[i] >= key) {
                break;
            }
            i++;
        }
        //是叶子节点
        if (node.leaf) {
            //没找到key
            if (!found(node, key, i)) {
                return;
            } else { //找到了key
                node.removeKey(i);
            }
        } else {  //不是叶子节点
            //没找到key
            if (!found(node, key, i)) {
                doRemove(node, node.children[i], i, key);
            } else { //找到了key
                Node s = node.children[i + 1];
                while (!s.leaf) {
                    s = s.children[0];
                }
                int skey = s.keys[0];
                doRemove(node, node.children[i + 1], i + 1, skey);

            }
        }

        if (node.keyNumber < MIN_KEY_NUMBER) {
            //调整平衡
            balance(parent, node, index);
        }

    }

    private void balance(Node parent, Node node, int index) {
        if (node == root) {
            if (root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }


        Node left = parent.childLeftSibling(index);
        Node right = parent.childRightSibling(index);

        //右旋
        if (left != null && left.keyNumber > MIN_KEY_NUMBER) {
            node.insertKey(parent.keys[index - 1], 0);

            if (!left.leaf) {
                node.insertChild(left.removeRightmostChild(), 0);
            }

            parent.keys[index - 1] = left.removeRightmostKey();
            return;
        }
        //左旋
        if (right != null && right.keyNumber > MIN_KEY_NUMBER) {
            node.insertKey(parent.keys[index], node.keyNumber);

            if (!right.leaf) {
                node.insertChild(right.removeLeftmostChild(), node.keyNumber + 1);
            }

            parent.keys[index] = right.removeLeftmostKey();
            return;
        }
        //合并
        if (left != null) {
            //向左兄弟合并
            parent.removeChild(index);
            left.insertKey(parent.removeKey(index - 1), left.keyNumber);
            node.moveToTarget(left);
        } else {
            //向自己合并
            //移除右兄弟
            parent.removeChild(index + 1);
            //父节点合并到被调整节点
            node.insertKey(parent.removeKey(index), node.keyNumber);
            //将右兄弟所有的孩子节点合并
            right.moveToTarget(node);
        }


    }

    private static boolean found(Node node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }

}
