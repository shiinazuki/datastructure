package com.iori.leetcode.leetcode05;

import java.util.HashMap;

/**
 * 146. LRU 缓存
 */
public class Solution146 {

    //节点类
    static class Node {
        public int key; //键
        public int val; //值
        public Node prev; //前一个节点
        public Node next; //下一个节点

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

    }

    //双向链表类
    static class DoubleList {
        //定义头节点与尾节点 方便操作
        private Node head;
        private Node tail;
        //链表元素个数
        private int size;

        public DoubleList() {
            //初始化双向链表的数据
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        //在链表尾部添加节点
        public void addLast(Node node) {
            node.next = tail;
            node.prev = head;
            head.next = node;
            tail.prev = head;
            size++;
        }

        //删除链表中的节点
        //由于是双链表且给的是目标Node节点
        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        //删除链表中第一节节点 并返回该节点
        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }

        //返回链表长度
        public int size() {
            return size;
        }

    }

    //LRUCache类
    static class LRUCache {
        //key -> Node(key,val)
        private HashMap<Integer, Node> map;
        //Node(k1,v1) <-> Node(k2,v2)....
        private DoubleList cache;
        //最大容量
        private int cap;

        public LRUCache(int cap) {
            this.cap = cap;
            map = new HashMap<>();
            cache = new DoubleList();
        }


        //将某个key提升为最近使用
        private void makeRecently(int key) {
            Node x = map.get(key);
            //先从链表中删除这个节点
            cache.remove(x);
            //重新插到队尾
            cache.addLast(x);
        }

        //添加最近使用的元素
        private void addRecently(int key, int val) {
            Node x = new Node(key, val);
            //链表尾部就是最近使用的元素
            cache.addLast(x);
            //别忘了在map中添加key的映射
            map.put(key, x);
        }

        //删除某一个key
        private void deleteKey(int key) {
            Node node = map.get(key);
            //从链表中删除
            cache.remove(node);
            //从map中删除
            map.remove(key);
        }

        //删除最久未使用的元素
        private void removeLeastRecently() {
            //链表头部第一个就是最久未使用的
            Node deleteNode = cache.removeFirst();
            //同时别忘了从map中删除他的key
            int deletedKey = deleteNode.key;
            map.remove(deletedKey);
        }

        //get方法
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            //将数据提升为最近使用
            makeRecently(key);
            return map.get(key).val;
        }

        //put方法
        public void put(int key, int val) {
            //如果key存在 将原来的key删除了 然后添加新的key到最近使用
            //省去了更新的 变为新建节点插入到最近的
            if (map.containsKey(key)) {
                //删除旧数据
                deleteKey(key);
                //新插入的数据为最近使用的数据
                addRecently(key, val);
                return;
            }
            if (cap == cache.size) {
                //删除最久未使用的元素
                removeLeastRecently();
            }
            //key 不存在 添加最近使用的元素
            addRecently(key, val);
        }

    }


}
