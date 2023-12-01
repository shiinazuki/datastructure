package com.iori.review.review04;


import java.util.Arrays;

/**
 * B树
 */
public class Review27 {

    static class Node {
        int[] keys;
        Node[] children;
        int keyNumber;
        boolean leaf = true;
        int t;

        public Node(int t) {
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

        //向keys指定索引处插入key
        void insertKey(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }

        //向children指定索引处插入child
        void insertChile(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }

        //移除指定索引处的key
        int removeKey(int index) {
            int t = keys[index];
            System.arraycopy(keys, index + 1, keys, index, --keyNumber - index);
            return t;
        }

        //移除最左边的key
        int removeLeftmostKey() {
            return removeKey(0);
        }

        //移除最右边的key
        int removeRightmostKey() {
            return removeKey(keyNumber - 1);
        }

        //移除指定index处的child
        Node removeChild(int index) {
            Node t = children[index];
            System.arraycopy(children, index + 1, children, index, keyNumber - index);
            children[keyNumber] = null;
            return t;
        }

        //移除最左边的孩子
        Node removeRightmostChild() {
            return removeChild(keyNumber);
        }

        //孩子处左边的兄弟
        Node childLeftSibling(int index) {
            return index > 0 ? children[index - 1] : null;
        }

        //孩子处右边的兄弟
        Node childRightSibling(int index) {
            return index == keyNumber ? null : children[index + 1];
        }

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

    int t;
    final int MIN_KEY_NUMBER;
    final int MAX_KEY_NUMBER;

    public Review27() {
        this(2);
    }

    public Review27(int t) {
        this.t = t;
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    //判断key是否存在
    public boolean containsKey(int key) {
        return root.get(key) != null;
    }

    //新增方法
    public void put(int key) {
        dpPut(root, key, null, 0);
    }

    private void dpPut(Node node, int key, Node parent, int index) {
        //先查找
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                return;
            }
            if (node.keys[i] > key) {
                break;
            }
            i++;
        }
        if (node.leaf) {
            node.insertKey(key, i);
        } else {
            dpPut(node.children[i], key, node, i);
        }
        //检查叶子 或 非叶子节点元素上限
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    //分裂方法
    private void split(Node left, Node parent, int index) {
        //判断要分裂的是不是根节点
        if (parent == null) { //分裂的是根节点
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.insertChile(left, 0);
            this.root = newRoot;
            parent = newRoot;
        }
        //创建right 节点 把left中 t之后的key和child移动过去
        Node right = new Node(t);
        right.leaf = left.leaf;//新节点是否是叶子节点 看分裂的节点是不是叶子节点
        //将较大的值 拷贝到新节点数组中
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        //分裂节点是非叶子的情况
        if (!left.leaf) {
            //将left的叶子节点拷贝到新节点的叶子节点
            System.arraycopy(left.children, t, right.children, 0, t);
            //将left叶子结点的值都置为null
            for (int i = t; i < left.keyNumber; i++) {
                left.children[i] = null;
            }
        }
        right.keyNumber = t - 1;
        left.keyNumber = t - 1;
        //中间的key(t-1)处插入到父亲节点
        int mid = left.keys[t - 1];
        parent.insertKey(mid, index);
        //right 节点作为父节点的孩子
        parent.insertChile(right, index - 1);
    }

    //删除方法
    public void remove(int key) {
        doRemove(null, root, 0, key);
    }

    //递归删除方法
    private void doRemove(Node parent, Node node, int index, int key) {
        //从当前位置查找索引位置
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] >= key) {
                break;
            }
            i++;
        }
        //当前节点是叶子节点
        if (node.leaf) {
            //i 找到了 代表待删除key的索引   i没找到 代表到第i个节点的孩子节点继续查找
            if (!found(node, key, i)) {
                return;
            } else { //case 2 找到了key
                //直接删除找到的key
                node.removeKey(i);
            }
        }
        //当前节点不是叶子节点
        else {
            //i 找到了 代表待删除key的索引, i 没找到 代表到第i个节点的孩子节点继续查找
            if (!found(node, key, i)) { //case 3 没找到key
                //去当前节点的第 i 个孩子继续查找
                doRemove(node, node.children[i], i, key);
            } else { //case 4 找到了key
                //拿到当前节点的后继节点
                Node s = node.children[i + 1];
                //如果s不是叶子节点
                while (!s.leaf) {
                    //一直往左走
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
        //case 5 删除后判断key的数目 是否小于下限 (不平衡)
        if (node.keyNumber < MIN_KEY_NUMBER) {
            //调整平衡 case 5 ,6
            balance(parent, node, index);
        }
    }

    //平衡方法
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
            //case 5 -1 左边富裕 右旋
            //将父节点的前驱 key 旋转下来
            node.insertKey(parent.keys[index - 1], 0);
            //如果左边的兄弟不是叶子节点
            if (!left.leaf) {
                //就将左兄弟里最右边的孩子移除 并添加到被调整节点的最左边孩子
                node.insertChile(left.removeRightmostChild(), 0);
            }
            //left 兄弟中 最大的key 移除 并旋转上去替换到旋转下去的key
            parent.keys[index - 1] = left.removeRightmostKey();
            return;
        }
        //右边不为null 并且 右边key个数大于最小值
        if (right != null && right.keyNumber > MIN_KEY_NUMBER) {
            //case 5 -2 右边富裕 左旋
            //父节点中后继key旋转下来
            node.insertKey(parent.keys[index], node.keyNumber);
            //如果右兄弟不是叶子节点
            if (!right.leaf) {
                //就将右兄弟里最左边的孩子移除并添加到被调整节点的最右边的孩子
            }
            //right 兄弟中最小的key移除 并选装上去替换掉旋转下去的key
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
            //父亲节点合并到被调整节点
            node.insertKey(parent.removeKey(index), node.keyNumber);
            //将右兄弟所有的孩子节点合并
            right.moveToTarget(node);
        }
    }

    //判断有没有找到元素
    private boolean found(Node node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }

    public void travel() {
        doTravel(root);
    }

    private void doTravel(Node root) {
        if (root == null) {
            return;
        }
        int i = 0;
        for (; i < root.keyNumber; i++) {
            doTravel(root.children[i]);
        }
        doTravel(root.children[i]);
    }

}
