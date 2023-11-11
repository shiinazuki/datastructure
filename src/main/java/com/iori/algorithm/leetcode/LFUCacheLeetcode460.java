package com.iori.algorithm.leetcode;

import java.util.HashMap;

/**
 * <h3>设计 LFU 缓存</h3>
 */
public class LFUCacheLeetcode460 {


    static class LFUCache {

        //节点类 有前后指针
        static class Node {
            int key;
            int value;
            int freq = 1; //使用频次
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
            int size = 0;
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
                size++;
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
                size--;
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

            public boolean isEmpty() {
                return size == 0;
            }

        }

        private int capacity;
        private int minFreq = 1; //最小频度
        //存放键值的map
        private final HashMap<Integer, Node> kvMap = new HashMap<>();
        //存放频率的map
        private final HashMap<Integer, DoublyLinkedList> freqMap = new HashMap<>();

        public LFUCache(int capacity) {
            this.capacity = capacity;
        }

        /*
            key 不存在
                返回 -1
            key 存在
                返回 value 值
                增加节点的使用频度，将其转移到频度+1的链表当中
          */
        public int get(int key) {
            if (!kvMap.containsKey(key)) {
                return -1;
            }
            //根据key 在kvMap中找到对应额值
            Node node = kvMap.get(key);
            //根据找到值的频次 从频次map中删除对应的节点
            DoublyLinkedList list = freqMap.get(node.freq);
            list.remove(node);
            //如果移除后 频次列表为空了 并且 节点的频次 就是最小频次
            if (list.isEmpty() && node.freq == minFreq) {
                //让频次 加 1
                minFreq++;
            }
            //值的频次+ 1
            node.freq++;
         /*   //根据加完频次的值去频次map里找对应的链表
            DoublyLinkedList list = freqMap.get(node.freq);
            //如果 list 为 空 则创建一个新链表 放入频次map中 key 就是对应的频次
            if(list == null) {
                list = new DoublyLinkedList();
                freqMap.put(node.freq,list);
            }*/
            //根据节点的频次去频次map里找对应的链表 如果不为空 返回对应的值   为空返回新节点
            freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList())
                    .addFirst(node); //将节点加入链表的头部

            return node.value;
        }

        /*
              更新
                  将节点的 value 更新，增加节点的使用频度，将其转移到频度+1的链表当中
              新增
                  检查是否超过容量，若超过，淘汰 minFreq 链表的最后节点
                  创建新节点，放入 kvMap，并加入频度为 1 的双向链表
          */
        public void put(int key, int value) {
            //如果键 存在 更新
            if (kvMap.containsKey(key)) {
                //根据key 在kvMap中找到对应额值
                Node node = kvMap.get(key);
                //根据找到值的频次 从频次map中删除对应的节点
                DoublyLinkedList list = freqMap.get(node.freq);
                list.remove(node);
                //如果移除后 频次列表为空了 并且 节点的频次 就是最小频次
                if (list.isEmpty() && node.freq == minFreq) {
                    //让频次 加 1
                    minFreq++;
                }
                //值的频次+ 1
                node.freq++;
                //根据节点的频次去频次map里找对应的链表 如果不为空 返回对应的值   为空返回新节点
                freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList())
                        .addFirst(node);   //将节点加入链表的头部
                node.value = value;

            } else { //新增
                //如果kvMap元素个数 等于 容量了
                if (kvMap.size() == capacity) {
                    //根据最小频次 从 freq中获取到链表 然后移除最后一个
                    Node node = freqMap.get(minFreq).removeLast();
                    //lvMap也做对应的删除
                    kvMap.remove(node.key);
                }
                //构建一个新节点
                Node node = new Node(key, value);
                //放入到kvMap
                kvMap.put(key,node);
                //如果freqMap中有对应的链表 就放入链表头部  没有就新建一个链表放入链表头部
                freqMap.computeIfAbsent(node.freq,k -> new DoublyLinkedList())
                        .addFirst(node);
                //更新最小频次为新节点的频次
                minFreq = node.freq;
            }

        }

    }


    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1 freq=2
        cache.put(3, 3);
        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 3 freq=2
        cache.put(4, 4);
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3  freq=3
        System.out.println(cache.get(4)); // 4  freq=2

    }

}
