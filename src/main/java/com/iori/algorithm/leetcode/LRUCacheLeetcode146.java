package com.iori.algorithm.leetcode;

import java.util.HashMap;

/**
 * <h3>LRU 缓存  最近最少使用   相当于 最久未使用</h3>
 */
public class LRUCacheLeetcode146 {


    static class  LRUCache{

        //节点类 有前后指针
        static class Node{

            int key;
            int value;
            Node prev;
            Node next;

            public Node() {
            }
            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        //双向链表类
        static class DoublyLinkedList {
            Node head; //头节点
            Node tail; //尾节点

            public DoublyLinkedList() {
                head = tail = new Node();
                head.next = tail;
                tail.prev = head;
            }
            //头部添加
            public void addFirst(Node newFirst) {
                //先拿到头节点的下一个节点
                Node oldFirst = head.next;
                //当前节点的前一个指向头节点
                newFirst.prev = head;
                //当前节点的下一个指向旧节点
                newFirst.next = oldFirst;
                //头节点的下一个指向新节点
                head.next = newFirst;
                //旧节点的前一个指向新节点
                oldFirst.prev = newFirst;
            }

            //已知节点删除
            public void remove(Node node) {
                //拿到待删除节点的前后节点
                Node prev = node.prev;
                Node next = node.next;
                //前节点的下一个指向后节点
                prev.next = next;
                //后节点的前一个指向前节点
                next.prev = prev;
            }

            //尾部删除
            public Node removeLast() {
                //拿到尾节点的前一个
                Node last = tail.prev;
                //直接调用删除节点的方法
                remove(last);
                //返回已删除节点
                return last;
            }
        }


        private Integer capacity;
        private final HashMap<Integer,Node>  map = new HashMap<>();
        private final DoublyLinkedList list = new DoublyLinkedList();

        public LRUCache() {
        }

        public LRUCache(Integer capacity) {
            this.capacity = capacity;
        }

        //添加方法
        public void  put(int key ,int value) {
            //如果map 里有这个key 就更新值
            if (map.containsKey(key)) { //更新
                Node node = map.get(key);
                node.value = value;
                //将更新的值放到双向链表的前面
                //先将这个节点删除
                list.remove(node);
                //然后再添加到双向链表头部
                list.addFirst(node);
            }else { //新增
                //如果没有key 那就构建一个新节点 加入map与双向链表的头部
                Node node = new Node(key, value);
                map.put(key,node);
                list.addFirst(node);
                //如果map中元素的个数大于容量了
                if (map.size() > capacity) {
                    //就移除双向链表中最后一个元素
                    Node removed = list.removeLast();
                    //map中也移除对应的元素
                    map.remove(removed.key);
                }
            }
            
        }

        //根据key找值方法
        public  int get(int key) {
            //如果map中不存在这个key 返回-1
            if (!map.containsKey(key)) {
                return -1;
            }
            //根据key 拿到节点
            Node node = map.get(key);
            //将使用的值放到双向链表的头部
            //先将这个节点删除
            list.remove(node);
            //然后添加到头部
            list.addFirst(node);
            return node.value;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1
        cache.put(3, 3);
        System.out.println(cache.get(2)); // -1
        cache.put(4, 4);
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
    }



}
