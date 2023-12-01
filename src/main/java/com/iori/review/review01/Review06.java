package com.iori.review.review01;

import java.util.Iterator;

/**
 * 双向链表(带哨兵)
 */
public class Review06 implements Iterable<Integer> {


    //节点类
    private static class Node {
        int value;
        Node prev;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node(Node prev, int value, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private final Node head;
    private final Node tail;

    public Review06() {
        head = new Node(null, Integer.MIN_VALUE, null);
        tail = new Node(null, Integer.MAX_VALUE, null);
        head.next = tail;
        tail.prev = head;
    }

    //根据索引查询指定节点
    private Node findNode(int index) {
        int i = -1;
        for (Node p = head; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    //头部添加元素
    public void addFirst(int value) {
        insert(0, value);
    }

    //指定位置添加元素
    public void insert(int index, int value) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            return;
        }
        Node next = prev.next;
        Node inserted = new Node(prev, value, next);
        prev.next = inserted;
        next.prev = inserted;

    }

    //移除头部节点
    public void removeFirst() {
        remove(0);
    }

    //移除指定索引处元素
    private void remove(int index) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            return;
        }
        Node removed = prev.next;
        if (removed == head) {
            return;
        }
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
    }

    //根据索引得到对应的节点
    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            //抛异常
            throw new RuntimeException();
        }
        return node.value;
    }

    //在尾部添加元素
    public void addLast(int value) {
        Node prev = tail.prev;
        Node added = new Node(prev, value, tail);
        prev.next = added;
        tail.prev = added;
    }

    //移除尾部元素
    public void removeLast() {
        Node removed = tail.prev;
        if (removed == head) {
            return;
        }
        Node prev = removed.prev;
        prev.next = tail;
        tail.prev = prev;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            Node p = head.next;

            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }


    public static void main(String[] args) {
        Review06 review06 = new Review06();
        review06.addFirst(0);
        review06.addFirst(1);
        review06.addLast(2);
        review06.removeFirst();
        review06.removeLast();
        review06.insert(0, 10);
        System.out.println(review06.head);
        review06.remove(1);
        System.out.println(review06.head);
    }


}
