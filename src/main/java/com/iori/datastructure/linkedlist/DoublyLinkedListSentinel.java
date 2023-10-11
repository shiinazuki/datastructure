package com.iori.datastructure.linkedlist;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 双向链表  带哨兵
 * 链表整体
 */
public class DoublyLinkedListSentinel implements Iterable<Integer> {

    private Node head; // 头哨兵节点
    private Node tail; // 尾哨兵节点


    public DoublyLinkedListSentinel() {
        head = new Node(null, 777, null);
        tail = new Node(null, 999, null);
        head.next = tail;
        tail.prev = head;
    }


    /**
     * 节点类
     */
    private static class Node {
        Node prev; //上一个节点指针
        int value; //值
        Node next; //下一个节点指针

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 根据索引得到对应的节点
     *
     * @param index
     * @return
     */
    private Node findNode(int index) {

        int i = -1;
        for (Node p = head; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        //没找到
        return null;
    }

    /**
     * 每次都往头节点添加元素
     *
     * @param value
     */
    public void addFirst(int value) {
        insert(0, value);

    }

    /**
     * 遍历链表
     */
    public void loop1(Consumer<Integer> consumer) {

    }

    /**
     * 使用for循环遍历
     *
     * @param consumer
     */
    public void loop2(Consumer<Integer> consumer) {

    }

    /**
     * 迭代器遍历
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {

        //匿名内部类 ->
        return new Iterator<>() {
            Node point = head.next;

            @Override
            public boolean hasNext() { //是否有下一个元素
                return point != tail;
            }

            @Override
            public Integer next() { //返回当前元素的值,并且指向下一个元素
                int value = point.value;
                point = point.next;
                return value;
            }
        };
    }

    /**
     * 每次都往尾节点添加元素
     *
     * @param value
     */
    public void addLast(int value) {
        //原来的最后一个节点
        Node last = tail.prev;
        //建一个新节点
        Node added = new Node(last, value, tail);
        last.next = added;
        tail.prev = added;

    }


    /**
     * 遍历得到链表的索引
     */
/*    public void test() {
        int i = 0;
        for (Node p = head; p != null; p = p.next,i++) {
            System.out.println(p.value + " 索引是 " + i);
        }
    }*/


    /**
     * 根据索引得到对应的节点
     *
     * @param index
     * @return
     */
    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            //抛异常
            throw getIllegalArgumentException(index);
        }
        return node.value;
    }

    /**
     * 向指定位置插入元素
     *
     * @param index
     * @param value
     */
    public void insert(int index, int value) {
        //找到上一个节点
        Node prev = findNode(index - 1);
        //找不到
        if (prev == null) {
            throw getIllegalArgumentException(index);
        }
        //下一个节点
        Node next = prev.next;
        //新节点
        Node inserted = new Node(prev, value, next);
        prev.next = inserted;
        next.prev = inserted;
    }

    /**
     * 异常方法
     *
     * @param index
     * @return
     */
    private static IllegalArgumentException getIllegalArgumentException(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }


    /**
     * 删除第一个节点
     */
    public void removeFirst() {
        remove(0);
    }

    /**
     * 按索引删除
     *
     * @param index
     */
    public void remove(int index) {
        //找到上一个节点
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw getIllegalArgumentException(index);
        }

        //被删除的节点
        Node removed = prev.next;
        if (removed == tail) {
            throw getIllegalArgumentException(index);
        }
        //后一个节点
        Node next = removed.next;

        prev.next = next;
        next.prev = prev;


    }

    /**
     * 删除最后一个节点
     */
    public void removeLast() {
        //先拿到最后一个节点
        Node last = tail.prev;
        if (last == head) {
            throw getIllegalArgumentException(0);
        }
        //获取最后一个节点的前一个节点
        Node prev = last.prev;
        //将前一个节点的下一个指向tail  tail的前一个指向prev
        prev.next = tail;
        tail.prev = prev;


    }

}



