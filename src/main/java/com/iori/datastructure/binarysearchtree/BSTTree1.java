package com.iori.datastructure.binarysearchtree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Binary Search Tree 二叉搜索树
 */
@SuppressWarnings("all")
public class BSTTree1 {

    BSTNode root; //根节点

    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
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
    public Object get(int key) {

        //非递归实现
        BSTNode node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /*    public Object get(int key) {
        return doGet(root,key);

    }*/

    private Object doGet(BSTNode node, int key) {
        //当一个函数 最后一步 调用的是函数自身时 是伪递归  可以转换为迭代
        //使用递归来查找元素
        if (node == null) {
            return null; //没找到
        }
        if (key < node.key) {
            return doGet(node.left, key); //向左找
        } else if (key > node.key) {
            return doGet(node.right, key); //向右找
        } else {
            return node.value; //找到了 直接返回值
        }
    }


    /**
     * <h3>查找最小关键字对应值</h3>
     *
     * @return 关键字对应的值
     */
    public Object min() {
      /*  if (root == null) {
            return null;
        }
        BSTNode reversePrint = root;
        while (reversePrint.left != null) {
            reversePrint = reversePrint.left;
        }*/
        return min(root);
    }

    private Object min(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;

    }

        /*
    public Object min() {
        return doMin(root);

    }
    */


    private Object doMin(BSTNode node) {
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

    public Object max() {
       /* if (root == null) {
            return null;
        }
        BSTNode reversePrint = root;
        while (reversePrint.right != null) {
            reversePrint = reversePrint.right;
        }*/
        return max(root);
    }


/*    public Object max() {
        return doMax(root);
    }*/

    private Object doMax(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.value;
        }
        return doMax(node.right);
    }

    private Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }

    /**
     * <h3>存储关键字和对应值</h3>
     *
     * @param key   关键字
     * @param value 值
     */
    public void put(int key, Object value) {
        // 1 key 有  更新
        // 2 key 没有 新增
        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            parent = node;//存父节点
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                //找到了 更新
                node.value = value;
                return;
            }
        }
        //树空
        if (parent == null) {
            root = new BSTNode(key, value);
            return;
        }
        //没找到 新增
        if (key < parent.key) {
            parent.left = new BSTNode(key, value);
        } else {
            parent.right = new BSTNode(key, value);
        }

    }

    /**
     * 新增 递归实现
     * @param node
     * @param key
     * @param value
     * @return
     */
    private BSTNode doPut(BSTNode node, int key, Object value) {
        if (node == null) {
            return new BSTNode(key, value);
        }
        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else if (node.key < key) {
            node.right = doPut(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;

    }

    /**
     * <h3>查找关键字的前任值</h3>
     *
     * @param key 关键字
     * @return 前任值
     */
    public Object predecessor(int key) {

        BSTNode p = root;
        BSTNode ancestorFromLeft = null;//定义一个自左而来的变量
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
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
    public Object successor(int key) {

        BSTNode p = root;
        BSTNode ancestorFromRight = null;//定义一个自右而来的变量
        while (p != null) {
            if (key < p.key) {
                ancestorFromRight = p;
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                break;
            }
        }
        //没找到节点
        if (p == null) {
            return null;
        }

        //情况1: 节点有右子树,此时后继节点即为右子树的最小值
        if (p.right != null) {
            return min(p.right);
        }

        //情况2: 节点没有右子树,若离它最近的祖先自从右而来,此祖先即为后继
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
    public Object remove(int key) {

        BSTNode p = root;
        BSTNode parent = null;
        while (p != null) {
            if (key < p.key) {
                parent = p;
                p = p.left;
            } else if (key > p.key) {
                parent = p;
                p = p.right;
            } else {
                break;
            }
        }
        //要删除的节点不存在
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
            //情况4
            //4.1 被删除节点找后事
            BSTNode s = p.right;
            BSTNode sparent = p; //后继父亲
            while (s.left != null) {
                sparent = s;
                s = s.left;
            }
            //后继节点即为 s
            if (sparent != p) { //不相邻
                //4.2 删除节点与后继节点不相邻处理后继的后事
                shift(sparent, s, s.right); //不可能有左孩子
                s.right = p.right; //修改右孩子
            }
            //4.3 后继取代被删除节点
            shift(parent, p, s);
            s.left = p.left; //修改左指针
        }
        return p.value;
    }

  /*  public Object remove(int key) {
        //保存被删除节点的值
        ArrayList<Object> reslut = new ArrayList<>();
        root = doRemove(root, key, reslut);
        return reslut.isEmpty() ? null : reslut.get(0);
    }*/

    /*
            4
           / \
          2   6
         /     \
        1       7

        node 起点
        返回值 删剩下的孩子 或 null
     */
    private BSTNode doRemove(BSTNode node, int key, ArrayList<Object> reslut) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = doRemove(node.left, key, reslut);
            return node;
        }
        if (key > node.key) {
            node.right = doRemove(node.right, key, reslut);
            return node;
        }
        reslut.add(node.value);
        //情况1 只有右孩子
        if (node.left == null) {
            return node.right;
        }
        //情况2 只有左孩子
        if (node.right == null) {
            return node.left;
        }
        //情况3 有两个孩子
        BSTNode s = node.right;
        while (s.left != null) {
            s = s.left;
        }
        s.right = doRemove(node.right, s.key, new ArrayList<>());
        s.left = node.left;
        return s;
    }

    /**
     * 托孤方法
     *
     * @param parent  被删除节点的父亲
     * @param deleted 被删除节点
     * @param child   被顶上去的节点
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        //父节点为 null 让 child节点成为根节点
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
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
    public List<Object> less(int key) {
        ArrayList<Object> result = new ArrayList<>();

        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                BSTNode pop = stack.pop();
                //处理值
                if (key > pop.key) {
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
    public List<Object> greater(int key) {


       /*
        //采用中序遍历
        ArrayList<Object> result = new ArrayList<>();

        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                BSTNode pop = stack.pop();
                //处理值
                if (key < pop.key) {
                    result.add(pop.value);
                }
                curr = pop.right;
            }
        }
        return result;*/

        //使用反向中序遍历
        ArrayList<Object> result = new ArrayList<>();

        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.right;
            } else {
                BSTNode pop = stack.pop();
                //处理值
                if (key < pop.key) {
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
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();

        LinkedList<BSTNode> stack = new LinkedList<>();
        BSTNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                BSTNode pop = stack.pop();
                //处理值
                if (key1 <= pop.key && key2 >= pop.key) {
                    result.add(pop.value);
                } else if (pop.key > key2) {
                    break;
                }
                curr = pop.right;
            }
        }
        return result;
    }

}
