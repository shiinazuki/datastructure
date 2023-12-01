package com.iori.review.review03;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉搜索树 实现Comparable接口的实现方式
 * 特性:  1.树节点增加key属性 用来比较谁大谁小, key不可以重复
 *        2. 对于任意一个树节点,它的key比左子树的key都大,同时也比右子树的key都小
 */
public class Review24<K extends Comparable<K>, V> {

    //节点类
    private static class BSTNode<K, V> {
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

    BSTNode<K, V> root; //根节点

    //查找关键字对应的值
    public V get(K key) {
        BSTNode<K, V> p = root;
        while (p != null) {
            int result = key.compareTo(p.key);
            if (result < 0) { //向左找
                p = p.left;
            } else if (result > 0) { //向右找
                p = p.right;
            } else {
                return p.value;
            }
        }
        return null;
    }

    //递归写法
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

    //查找最小关键字对应值
    public V min() {
        return min(root);
        //return doMin(root);
    }

    //迭代
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

    //递归
    private V doMin(BSTNode<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.value;
        }
        return doMin(node.left);
    }

    //查找最大关键字对应值
    public V max() {
        return max(root);
        //return doMax(root);
    }


    //迭代
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

    private V doMax(BSTNode<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.value;
        }
        return doMax(node.right);
    }


    //存储关键字和对应值
    public void put(K key, V value) {
        //1. key 有 更新
        //2. key 没有 新增
        BSTNode<K, V> p = root;
        BSTNode<K, V> parent = null; //用来保存父节点

        while (p != null) {
            parent = p;
            int result = key.compareTo(p.key);
            if (result < 0) {
                p = p.left;
            } else if (result > 0) {
                p = p.right;
            } else {
                p.value = value;
                return;
            }
        }

        //树空
        if (parent == null) {
            root = new BSTNode<>(key, value);
            return;
        }

        //树不空
        if (key.compareTo(parent.key) < 0) {
            parent.left = new BSTNode<>(key, value);
        } else {
            parent.right = new BSTNode<>(key, value);
        }
    }

    //新增 递归实现
    private BSTNode<K, V> doPut(BSTNode<K, V> node, K key, V value) {
        if (node == null) {
            return new BSTNode<>(key, value);
        }
        int result = key.compareTo(node.key);
        if (result < 0) {
            node.left = doPut(node.left, key, value);
        } else if (result > 0) {
            node.right = doPut(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    //查找关键字的前任值
    public V predecessor(K key) {
        BSTNode<K, V> p = root;
        BSTNode<K, V> ancestorFromLeft = null; //定义一个自左而来的变量

        while (p != null) {
            int result = key.compareTo(p.key);
            if (result < 0) {
                p = p.left;
            } else if (result > 0) {
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

        //情况1 节点有左子树 此时前任就是左子树的最大值
        if (p.left != null) {
            return max(p.left);
        }
        //情况2 节点没有左子树 此时自左而来的祖先就是前任
        if (ancestorFromLeft != null) {
            return ancestorFromLeft.value;
        }
        return null;
    }

    //查找关键字的后任值
    public V successor(K key) {
        BSTNode<K, V> p = root;
        BSTNode<K, V> ancestorFromRight = null; //定义一个自右而来的变量

        while (p != null) {
            int result = key.compareTo(p.key);
            if (result < 0) {
                ancestorFromRight = p;
                p = p.left;
            } else if (result > 0) {
                p = p.right;
            } else {
                break;
            }
        }

        //没找到节点
        if (p == null) {
            return null;
        }
        //情况1 如果节点有右子树  此时 子树的最小值就是后任值
        if (p.right != null) {
            return min(p.right);
        }
        //情况2 节点没有右子树 此时自右而来的祖先 即为后继
        if (ancestorFromRight != null) {
            return ancestorFromRight.value;
        }
        return null;
    }

    //根据关键字删除
    public V remove(K key) {
        BSTNode<K, V> p = root;
        BSTNode<K, V> parent = null;

        while (p != null && key.compareTo(p.key) != 0) {
            parent = p;
            int result = key.compareTo(p.key);
            if (result < 0) {
                p = p.left;
            } else if (result > 0) {
                p = p.right;
            }
        }
        //删除的节点不存在
        if (p == null) {
            return null;
        }
        //删除操作
        if (p.left == null) {
            //情况1 没有左孩子
            shift(parent, p, p.right);
        } else if (p.right == null) {
            //情况2 没有右孩子
            shift(parent, p, p.left);
        } else {
            //被删除节点找后事
            BSTNode<K, V> s = p.right;
            BSTNode<K, V> sparent = p;
            while (s.left != null) {
                sparent = s;
                s = s.left;
            }
            if (sparent != p) {
                shift(sparent, s, s.right);
                s.right = p.right;
            }
            shift(parent, p, s);
            s.left = p.left;
        }

        return p.value;
    }

    //托孤方法
    private void shift(BSTNode<K, V> parent, BSTNode<K, V> deleted, BSTNode<K, V> child) {
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    //保存白删除节点的值
    /*
    public V remove(K key) {
        ArrayList<V> result = new ArrayList<>();
        root = doRemove(root,key,result);
        return result.isEmpty() ? null : result.get(0);
    }
    */
    //递归删除
    private BSTNode<K, V> doRemove(BSTNode<K, V> node, K key, ArrayList<V> result) {
        if (node == null) {
            return null;
        }
        //往左
        if (key.compareTo(node.key) < 0) {
            node.left = doRemove(node.left, key, result);
            return node;
        }
        //往右
        if (key.compareTo(node.key) > 0) {
            node.left = doRemove(node.right, key, result);
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
        s.right = doRemove(node.right, s.key, new ArrayList<>());
        s.left = node.left;
        return s;
    }

    //找到小于key的所有value
    public List<V> less(K key) {
        ArrayList<V> result = new ArrayList<>();

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

    //找到大于key的所有值
    public List<V> greater(K key) {
        ArrayList<V> result = new ArrayList<>();

        BSTNode<K, V> p = root;
        LinkedList<BSTNode<K, V>> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (key.compareTo(pop.key) < 0) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    //找 大于等于 key1  且 <= key2 的所有值
    public List<V> between(K key1, K key2) {
        ArrayList<V> result = new ArrayList<>();

        LinkedList<BSTNode<K,V>> stack = new LinkedList<>();
        BSTNode<K, V> p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            }else {
                BSTNode<K, V> pop = stack.pop();
                if (key1.compareTo(pop.key) <= 0 && key2.compareTo(pop.key) >= 0) {
                    result.add(pop.value);
                }else if (pop.key.compareTo(key2) > 0) {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }


}
