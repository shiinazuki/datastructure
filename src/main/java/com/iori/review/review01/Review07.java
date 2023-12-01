package com.iori.review.review01;

import java.util.Iterator;

/**
 * 环形链表(带哨兵)
 */
public class Review07 implements Iterable<Integer> {


    //节点类
    private static class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(Node prev, int val, Node next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }
    }

    //头节点
    private final Node sentinel = new Node(null, -1, null);

    public Review07() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    //添加到第一个
    public void addFirst(int value) {
        Node prev = sentinel;
        Node next = sentinel.next;
        Node node = new Node(prev, value, next);
        prev.next = node;
        next.prev = node;
    }

    //添加到最后一个
    public void addLast(int value) {
        Node prev = sentinel.prev;
        Node next = sentinel;
        Node node = new Node(prev, value, next);
        prev.next = node;
        next.prev = node;
    }

    //删除第一个
    public void removeFirst() {
        Node next = sentinel.next;
        if (next == sentinel) {
            return;
        }
        Node a = sentinel;
        Node b = next.next;
        a.next = b;
        b.prev = a;
    }

    //删除最后一个
    public void removeLast() {
        Node prev = sentinel.prev;
        if (prev == sentinel) {
            return;
        }
        Node a = sentinel;
        Node b = prev.prev;
        a.next = b;
        b.prev = a;
    }

    //根据值删除节点
    public void removeByValue(int value) {
        Node removed = findNodeByValue(value);
        if (removed != null) {
            Node prev = removed.prev;
            Node next = removed.next;
            prev.next = next;
            next.prev = prev;
        }
    }

    private Node findNodeByValue(int value) {
        Node curr = sentinel.next;
        while (curr != sentinel) {
            if (curr.val == value) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            Node p = sentinel.next;
            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public Integer next() {
                int val = p.val;
                p = p.next;
                return val;
            }
        };
    }

}
