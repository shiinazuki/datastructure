package com.iori.datastructure.binarysearchtree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Binary Search Tree 二叉搜索树
 * 二叉搜索树, 泛型 key 版本
 */
@SuppressWarnings("all")
public class BSTTree2LianXi<K extends Comparable<K>, V> {

    private BSTNode<K, V> root; //根节点

    static class BSTNode<K, V> {
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        public BSTNode(K key) {
            this.key = key;
        }

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * <h3>查找关键字对应的值</h3>
     *
     * @param key 关键字
     * @return 关键字对应的值
     */
    public V get(K key) {
        if (key == null || root == null) {
            return null;
        }
        BSTNode<K, V> node = root;
        while (node != null) {
            int result = key.compareTo(node.key);
            if (result < 0) { //往左
                node = node.left;
            } else if (result > 0) { //往右
                node = node.right;
            } else { //找到了
                return node.value;
            }
        }
        return null;
    }

/*    public V get(K key) {
        return  doGet(root,key);
    }*/


    private V doGet(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int result = key.compareTo(node.key);
        if (result < 0) {
            return doGet(node.left, key);
        } else if (result > 0) {
            return doGet(node.right, key);
        } else {
            return node.value;
        }
    }

    /**
     * <h3>查找最小关键字对应值</h3>
     *
     * @return 关键字对应的值
     */
    public V min() {
        return min(root);
    }

    /**
     * 找最小值
     *
     * @param node
     * @return
     */
    private V min(BSTNode<K, V> node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    /**
     * 递归找最小
     *
     * @param node
     * @return
     */
    private V doMin(BSTNode<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.value;
        }
        return doMin(node.left);
    }

    /**
     * <h3>查找最大关键字对应值</h3>
     *
     * @return 关键字对应的值
     */

    public V max() {
        return max(root);
    }

    /**
     * 找最大值
     *
     * @param node
     * @return
     */
    private V max(BSTNode<K, V> node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
    }

    /**
     * 递归找最大值
     *
     * @param node
     * @return
     */
    private V doMax(BSTNode<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.value;
        }
        return doMax(node.right);
    }

    /**
     * <h3>存储关键字和对应值</h3>
     *
     * @param key   关键字
     * @param value 值
     */
    public void put(K key, V value) {
        BSTNode<K, V> p = root;
        BSTNode<K, V> parent = null;
        while (p != null) {
            parent = p;//记录父节点
            int result = key.compareTo(p.key);
            if (result < 0) {
                p = p.right;
            } else if (result > 0) {
                p = p.right;
            } else {
                p.value = value;
                return;
            }
        }
        if (parent == null) {
            root = new BSTNode<>(key, value);
            return;
        }
        if (key.compareTo(parent.key) < 0) {
            parent.left = new BSTNode<>(key, value);
        } else {
            parent.right = new BSTNode<>(key, value);
        }


    }

    /**
     * <h3>查找关键字的前任值</h3>
     *
     * @param key 关键字
     * @return 前任值
     */
    public V predecessor(K key) {
        BSTNode<K, V> p = root;
        BSTNode<K, V> fromLeft = null;
        while (p != null) {
            int result = key.compareTo(p.key);
            if (result < 0) {
                p = p.left;
            } else if (result > 0) {
                fromLeft = p;
                p = p.right;
            } else {
                break;
            }
        }

        if (p == null) {
            return null;
        }

        if (p.left != null) {
            return max(p.left);
        }

        if (fromLeft != null) {
            return fromLeft.value;
        }
        return null;
    }

    /**
     * <h3>查找关键字的后任值</h3>
     *
     * @param key 关键字
     * @return 后任值
     */
    public V successor(K key) {

        BSTNode<K, V> p = root;
        BSTNode<K, V> fromRight = null;
        while (p != null) {
            int result = key.compareTo(p.key);
            if (result < 0) {
                fromRight = p;
                p = p.left;
            } else if (result > 0) {
                p = p.right;
            } else {
                break;
            }
        }

        if (p == null) {
            return null;
        }

        if (p.right != null) {
            return min(p.right);
        }

        if (fromRight != null) {
            return fromRight.value;
        }
        return null;

    }

    /**
     * <h3>根据关键字删除</h3>
     *
     * @param key 关键字
     * @return 被删除关键字对应值
     */
    public V remove(K key) {
        BSTNode<K, V> p = root;
        BSTNode<K, V> parent = null;
        while (p != null) {
            int result = key.compareTo(p.key);
            if (result < 0) {
                parent = p;
                p = p.left;
            } else if (result > 0) {
                parent = p;
                p = p.right;
            } else {
                break;
            }
        }

        if (p == null) {
            return null;
        }

        if (p.left == null) {
            shift(parent, p, p.right);
        } else if (p.right == null) {
            shift(parent, p, p.left);
        } else {
            //左右都有节点
            //处理后继节点
            BSTNode<K, V> s = p.right;
            BSTNode<K, V> sparent = p;
            //处理后继节点不相邻的情况
            //先找到后继节点
            while (s.left != null) {
                //先找出后继节点的父节点
                sparent = s;
                s = s.left;
            }
            if (p != sparent) {
                shift(sparent, s, s.right);
                s.right = p.right;
            }

            shift(parent, p, s);
            s.left = p.left;

        }
        return p.value;


    }

    /**
     * 托孤方法
     *
     * @param parent  被删除节点的父亲
     * @param deleted 被删除节点
     * @param child   被顶上去的节点
     */
    private void shift(BSTNode<K, V> parent, BSTNode<K, V> deleted, BSTNode<K, V> child) {
        if (parent == null) {
            root = child;
        } else if (parent.left == deleted) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    /*
    public V remove(K key) {
        //保存白删除节点的值
        ArrayList<V> result = new ArrayList<>();
        root = deRemove(root, key, result);
        return result.isEmpty() ? null : result.get(0);
    }
    */







    private BSTNode<K, V> deRemove(BSTNode<K, V> node, K key, ArrayList<V> result) {

        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = deRemove(node.left, key, result);
            return node;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = deRemove(node.right, key, result);
            return node;
        }
        result.add(node.value);

        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }

        BSTNode<K, V> s = node.right;
        while (s.left != null) {
            s = s.left;
        }
        s.right = deRemove(s, key, new ArrayList<>());
        s.left = node.left;

        return s;
    }

    //中序遍历

    /*
                 4
               /   \
              2     6
             / \   / \
            1   3 5   7
     */

    // 找 < key 的所有 value
    public List<V> less(K key) {
        List<V> result = new ArrayList<>();

        BSTNode<K, V> p = root;
        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (key.compareTo(pop.key) > 0) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.right;
            }
        }

        return result;

    }

    // 找 > key 的所有 value
    public List<V> greater(K key) {

        List<V> result = new ArrayList<>();

        BSTNode<K, V> p = root;
        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.right;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (key.compareTo(pop.key) < 0) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.left;
            }
        }
        return result;

    }

    // 找 >= key1 且 <= key2 的所有值
    public List<V> between(K key1, K key2) {
        List<V> result = new ArrayList<>();

        BSTNode<K, V> p = root;
        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (key1.compareTo(p.key) <= 0 && key2.compareTo(p.key) >= 0) {
                    result.add(p.value);
                } else if (key2.compareTo(pop.key) < 0) {
                    break;
                }
            }
        }
        return result;
    }

}
