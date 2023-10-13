package com.iori.datastructure.binarysearchtree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Binary Search Tree 二叉搜索树
 * 二叉搜索树, 泛型 key 版本
 */
@SuppressWarnings("all")
public class BSTTree2<K extends Comparable<K>, V> {

    BSTNode<K, V> root; //根节点

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

        public BSTNode(K key, V value, BSTNode left, BSTNode right) {
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

        BSTNode<K, V> p = root;
        while (p != null) {

            //     -1 key < p.key
            //     0 key = p.key
            //     1 key > p.key

            int result = key.compareTo(p.key);
            if (result < 0) {
                p = p.left; //向左走
            } else if (result > 0) {
                p = p.right; //向右找
            } else {
                return p.value;
            }
        }
        return null;
    }

/*    public V get(K key) {
        return  doGet(root,key);
    }*/


    private V doGet(BSTNode<K, V> node, K key) {
        //使用递归来查找元素
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
        //递归
        //return doMin(root);
    }

    /**
     * 找最大值
     *
     * @param node
     * @return
     */
    private V min(BSTNode<K, V> node) {
        if (node == null) {
            return null;
        }
        BSTNode<K, V> p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
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
        //递归
        //return doMax(root);

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
        BSTNode<K, V> p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
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
        // 1 key 有 更新
        // 2 key 没有 新增
        BSTNode<K, V> p = root;
        BSTNode<K, V> parent = null; //用来保存父节点
        while (p != null) {
            //这个比较一定要写循环里边!!!!!
            int result = key.compareTo(p.key);
            parent = p; //记录父节点
            if (result < 0) { //往左找
                p = p.left;
            } else if (result > 0) {
                p = p.right;
            } else {
                p.value = value;
                return;
            }
        }
        //树空
        if (p == null) {
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
        BSTNode<K, V> ancestorFromLeft = null; //定义一个自左而来的变量
        while (p != null) {
            int result = key.compareTo(p.key);

            if (result < 0) {//往左找
                p = p.left;
            } else if (result > 0) { //往右找
                ancestorFromLeft = p;
                p = p.right;
            } else {
                break;
            }
        }

        //没找到节点的情况
        if (p == null) {
            return null;
        }
        //找到节点
        //情况1: 节点有左子树,此时前任就是左子树的最大值
        if (p.left != null) {
            return max(p.left);
        }
        //情况2: 节点没有左子树,若离它最近的 自左而来的祖先就是前任
        if (ancestorFromLeft != null) {
            return ancestorFromLeft.value;
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
        BSTNode<K, V> ancestorFromRight = null;//定义一个自右而来的变量
        while (p != null) {
            int result = key.compareTo(p.key);
            if (result < 0) { //往左找
                ancestorFromRight = p;
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
        //情况1: 节点有右子树,此时后继节点即为右子树的最小值
        if (p.right != null) {
            return min(p.right);
        }
        //情况2:节点没有右子树,若离它最近的祖先自右而来,此祖先即为后继
        if (ancestorFromRight != null) {
            return ancestorFromRight.value;
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
            if (result < 0) {//往左找
                parent = p;
                p = p.left;
            } else if (result > 0) { //往右找
                parent = p;
                p = p.right;
            } else {
                break;
            }
        }
        //删除的节点不存在
        if (p == null) {
            return null;
        }
        //删除操作
        if (p.left == null) {
            //情况1: 没有左孩子
            shift(parent, p, p.right);
        } else if (p.right == null) {
            //情况2  没有右孩子
            shift(parent, p, p.left);
        } else {
            //情况4.1 被删除节点找后事
            BSTNode<K, V> s = p.right;
            BSTNode<K, V> sparent = p; //后继 父亲
            while (s.left != null) {
                sparent = s;
                s = s.left;
            }
            //后继节点为 s
            if (sparent != p) { //不相邻
                //情况4.2 删除节点与后继节点不相邻 处理后继的后事
                shift(sparent, s, s.right); //不可能有左孩子
                s.right = p.right;//修改右孩子
            }
            //情况4.3 后继取代被删除节点
            shift(parent, p, s);
            s.left = p.left; //修改左指针
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
        } else if (deleted == parent.left) {
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
        if (key.compareTo(node.key) < 0) { //往左
            node.left = deRemove(node.left, key, result);
            return node;
        }
        if (key.compareTo(node.key) > 0) { //往右
            node.right = deRemove(node.right, key, result);
            return node;
        }
        result.add(node.value);
        //情况1 只有右孩子
        if (node.left == null) {
            return node.right;
        }
        //情况2 只有左孩子
        if (node.right == null) {
            return node.left;
        }

        //情况3 有两个孩子
        BSTNode<K, V> s = node.right;
        while (s.left != null) {
            s = s.left;
        }
        s.right = deRemove(node.right, s.key, new ArrayList<>());
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
        ArrayList<V> result = new ArrayList<>();

        BSTNode<K, V> curr = root;
        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (key.compareTo(pop.key) > 0) {
                    result.add(pop.value);
                } else {
                    break;
                }
                curr = pop.right;
            }
        }
        return result;
    }

    // 找 > key 的所有 value
    public List<V> greater(K key) {
        ArrayList<V> result = new ArrayList<>();

        BSTNode<K, V> curr = root;
        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.right;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (key.compareTo(pop.key) < 0) {
                    result.add(pop.value);
                } else {
                    break;
                }
                curr = pop.left;
            }
        }
        return result;
    }

    // 找 >= key1 且 <= key2 的所有值
    public List<V> between(K key1, K key2) {
        ArrayList<V> result = new ArrayList<>();

        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        BSTNode<K, V> curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (key1.compareTo(pop.key) <= 0 && key2.compareTo(pop.key) >= 0) {
                    result.add(pop.value);
                } else if (pop.key.compareTo(key2) > 0) {
                    break;
                }
                curr = pop.right;
            }
        }
        return result;
    }

}
