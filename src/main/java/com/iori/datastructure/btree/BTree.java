package com.iori.datastructure.btree;


import java.util.Arrays;


/**
 * <h3>B-树</h3>
 */
public class BTree {

    static class Node {
        int[] keys; //关键字
        Node[] children; //孩子
        int keyNumber; //有效节点个数
        boolean leaf = true; //是否是叶子节点
        int t; // 最小度数(最小孩子数)


        public Node(int t) { // t >= 2
            this.t = t;
            this.children = new Node[2 * t];
            this.keys = new int[2 * t - 1];
        }

        public Node(int[] keys) {
            this.keys = keys;
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }


        /**
         * 多路查找
         *
         * @param key
         * @return
         */
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
            //执行到此时 keys[i] > key 或 i == keyNumber
            //是叶子节点
            if (leaf) {
                return null;
            }
            //非叶子情况
            return children[i].get(key);
        }

        /**
         * 向keys指定索引插入key
         *
         * @param key
         * @param index
         */
        void insertKey(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }

        /**
         * 向children指定索引处插入child
         *
         * @param child
         * @param index
         */
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

    int t; //数中节点的最小度数
    final int MIN_KEY_NUMBER; //最小key数目
    final int MAX_KEY_NUMBER; //最大key数目

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean containsKey(int key) {
        return root.get(key) != null;
    }

    /**
     * 新增方法
     *
     * @param key
     */
    public void put(int key) {
        doPut(root, key, null, 0);
    }

    /**
     * 递归插入
     *
     * @param node
     * @param key
     */
    private void doPut(Node node, int key, Node parent, int index) {
        //先查找
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                return; //更新
            }
            if (node.keys[i] > key) {
                break;  //找到插入位置 即为此时的i
            }
            i++;
        }
        //是叶子节点
        if (node.leaf) {
            node.insertKey(key, i);
        } else {
            doPut(node.children[i], key, node, i);
        }
        // //检查叶子 或 非叶子节点元素上限
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    /**
     * <h3>分裂方法</h3>
     *
     * @param left   要分裂的节点
     * @param parent 分裂节点的父节点
     * @param index  分裂节点是第几个孩子
     */
    public void split(Node left, Node parent, int index) {
        //判断要分裂的是不是根节点
        if (parent == null) { //分裂的是根节点
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.insertChild(left, 0);
            this.root = newRoot;
            parent = newRoot;
        }
        //创建right 节点 把 left中t之后的key和child移动过去
        Node right = new Node(t);
        right.leaf = left.leaf;  //新节点是否是叶子节点 看分裂的节点是否是叶子节点
        //将较大的值 拷贝到新节点数组中
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        //分裂节点是非叶子的情况
        if (!left.leaf) {
            //将left的叶子节点拷贝到新节点的叶子节点
            System.arraycopy(left.children, t, right.children, 0, t);
            //将left叶子节点的值都置为null  help gc
            for (int i = t; i <= left.keyNumber; i++) {
                left.children[i] = null;
            }
        }
        right.keyNumber = t - 1;
        left.keyNumber = t - 1;
        //2. 中间的key (t - 1) 处插入到父亲节点
        int mid = left.keys[t - 1];
        parent.insertKey(mid, index);
        //3. right 节点作为父亲节点的孩子
        parent.insertChild(right, index + 1);

    }

    /**
     * 删除方法
     *
     * @param key
     */
    public void remove(int key) {
        doRemove(null, root, 0, key);
    }

    /**
     * 递归删除方法
     *
     * @param parent
     * @param node
     * @param index
     * @param key
     */
    private void doRemove(Node parent, Node node, int index, int key) {
        //从当前位置找索引位置
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] >= key) {
                break;
            }
            i++;
        }
        //当前节点是叶子节点
        if (node.leaf) {
            // i 找到了 代表待删除key的索引  i 没找到 代表到第i个节点的孩子节点继续查找
            if (!found(node, key, i)) { //case 1 没找到 key
                return;
            } else { //case 2 找到了 key
                //直接删除找到的key
                node.removeKey(i);
            }
        }
        //当前节点不是叶子节点
        else {
            //i找到了 代表待删除key的索引,i没找到 代表到第 i个节点的孩子节点继续查找
            if (!found(node, key, i)) { //case 3 没找到 key
                //去当前节点的第 i 个孩子继续查找
                doRemove(node, node.children[i], i, key);
            } else { //case 4 找到了 key
                //拿到当前节点的后继节点
                Node s = node.children[i + 1];
                //如果s不是叶子节点
                while (!s.leaf) {
                    //则一直往左走
                    s = s.children[0];
                }
                //找到了后继key
                int skey = s.keys[0];
                //替换待删除的key
                node.keys[i] = skey;
                //删除后继key
                doRemove(node, node.children[i + 1], i + 1, skey);
            }
        }

        //case 5 删除后判断 key 的数目 是否 小于下限 (不平衡)
        if (node.keyNumber < MIN_KEY_NUMBER) {
            //调整平衡 case 5 case 6
            balance(parent, node, index);
        }

    }

    /**
     * 平衡方法
     *
     * @param parent
     * @param node
     * @param index
     */
    private void balance(Node parent, Node node, int index) {
        //case 6 根节点
        if (node == root) {
            if (root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }
        Node left = parent.childLeftSibling(index);
        Node right = parent.childRightSibling(index);
        //左边不为null 并且 左边key个数大于最小值
        if (left != null && left.keyNumber > MIN_KEY_NUMBER) {
            //case 5-1 左边富裕 右旋
            //将父节点的 前驱 key 旋转下来
            node.insertKey(parent.keys[index - 1], 0);
            //如果左边的兄弟不是叶子节点
            if (!left.leaf) {
                //就将左兄弟里最右边的孩子移除 并添加到 被调整节点的最左边的孩子
                node.insertChild(left.removeRightmostChild(), 0);
            }
            //left 兄弟中 最大的key 移除 并旋转上去替换掉旋转下去的key
            parent.keys[index - 1] = left.removeRightmostKey();
            return;
        }
        //右边不为null 并且 右边key个数大于最小值
        if (right != null && right.keyNumber > MIN_KEY_NUMBER) {
            //case 5-2 右边富裕 左旋
            //父节点中后继 key 旋转下来
            node.insertKey(parent.keys[index], node.keyNumber);
            //如果右兄弟不是叶子节点
            if (!right.leaf) {
                //就将右兄弟里最左边的孩子移除 并添加到 被调整节点的最右边的孩子
                node.insertChild(right.removeLeftmostChild(), node.keyNumber);
            }
            //right兄弟中 最小的key移除 并旋转上去替换掉旋转下去的key
            parent.keys[index] = right.removeLeftmostKey();
            return;
        }
        //case 5-3 两边都不够借 向左合并
        if (left != null) {
            //向左兄弟合并
            //先移除父节点的一个孩子(被调整节点)
            parent.removeChild(index);
            //移除父节点的一个key (被调整节点的索引 - 1) 合并到左兄弟里
            left.insertKey(parent.removeKey(index - 1), left.keyNumber);
            //将调整节点以及子节点合并到左兄弟节点
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

    /**
     * 判断有没有找到元素
     *
     * @param node
     * @param key
     * @param i
     * @return
     */
    private boolean found(Node node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }

    public void travel() {
        doTravel(root);
    }

    public void doTravel(Node node) {
        if (node == null) {
            return;
        }
        int i = 0;
        for (; i < node.keyNumber; i++) {
            doTravel(node.children[i]);
            System.out.println(node.keys[i]);
        }
        doTravel(node.children[i]);
    }


}
