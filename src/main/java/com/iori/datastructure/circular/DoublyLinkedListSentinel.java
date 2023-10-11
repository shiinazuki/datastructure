package com.iori.datastructure.circular;

import java.util.Iterator;

/**
 * 环形链表
 */
public class DoublyLinkedListSentinel implements Iterable<Integer> {


    public static class Node {
        Node prev;
        int value;
        Node next;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private Node sentinel = new Node(null, -1, null);

    public DoublyLinkedListSentinel() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**
     * 迭代器遍历
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            Node point = sentinel.next;

            @Override
            public boolean hasNext() {
                return point != sentinel;
            }

            @Override
            public Integer next() {
                int value = point.value;
                point = point.next;
                return value;
            }
        };
    }

    /**
     * 添加到第一个
     *
     * @param value 待添加的值
     */
    public void addFirst(int value) {
        Node prev = sentinel;
        Node next = sentinel.next;
        Node node = new Node(prev, value, next);
        prev.next = node;
        next.prev = node;
    }

    /**
     * 添加到最后一个
     *
     * @param value 待添加的值
     */
    public void addLast(int value) {
        //先拿到最后一个节点
        Node prev = sentinel.prev;
        Node temp = sentinel;
        Node node = new Node(prev, value, temp);
        prev.next = node;
        temp.prev = node;

    }

    /**
     * 删除第一个元素
     */
    public void removeFirst() {
        Node removed = sentinel.next;
        if (removed == sentinel) {
            throw getIllegalArgumentException(0);
        }
        Node prev = sentinel;
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
    }

    /**
     * 删除最后一个元素
     */
    public void removeLast() {
        Node removed = sentinel.prev;
        if (removed == sentinel) {
            throw getIllegalArgumentException(0);
        }
        Node prev = removed.prev;
        Node next = sentinel;
        prev.next = next;
        next.prev = prev;
    }

    /**
     * 根据值删除
     *
     * @param value 目标值
     */
    public void removeByValue(int value) {
        Node node = findByValue(value);
        if (node == null) {
           return;
        }
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    /**
     * 根据值找到对应的节点
     * @param value
     * @return
     */
    private Node findByValue(int value) {
        Node temp = sentinel.next;
        while (temp != sentinel) {
            if (temp.value == value) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
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


}
