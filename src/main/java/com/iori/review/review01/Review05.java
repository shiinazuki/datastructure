package com.iori.review.review01;

import java.util.Iterator;

/**
 * 单向链表(带哨兵)
 */
public class Review05 implements Iterable<Integer> {

    //节点类
    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    //哨兵节点
    private Node head = new Node(Integer.MIN_VALUE);

    //根据索引获取节点
    private Node findNode(int index) {
        int i = -1;
        for (Node curr = head; curr != null; curr = curr.next, i++) {
            if (i == index) {
                return curr;
            }
        }
        return null;
    }

    //获取最后一个节点
    private Node findLast() {
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr;
    }

    //链表最后添加元素
    public void addLast(int value) {
        Node last = findLast();
        last.next = new Node(value);
    }

    //指定索引插入元素
    public void insert(int index, int value) {
        Node node = findNode(index - 1);
        if (node != null) {
            node.next = new Node(value, node.next);
        } else {
            throw new RuntimeException();
        }
    }

    //移除元素
    public void remove(int index) {
        Node node = findNode(index - 1);
        Node curr;
        if (node != null && (curr = node.next) != null) {
            node.next = curr.next;
        } else {
            throw new RuntimeException();
        }
    }

    //在链表头插入元素
    public void addFirst(int value) {
        head.next = new Node(value, head.next);
    }



    //while遍历
    public void loopWhile() {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }

    //for 遍历
    public void loopFor() {
        for (Node curr = head; curr != null; curr = curr.next) {
            System.out.println(curr.value);
        }
    }

    //迭代器遍历
    private class NodeIterator implements Iterator<Integer> {
        Node curr = head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Integer next() {
            int value = curr.value;
            curr = curr.next;
            return value;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new NodeIterator();
    }
        //递归遍历
    public void loop() {
        recursion(head);
    }

    private void recursion(Node curr) {
        if (curr == null) {
            return;
        }
        System.out.println(curr.value);
        recursion(curr.next);
    }

        //尾部添加多个
    public void addLastMore(int first, int... rest) {
        /*
            - 先串成一串 sublist
            - 再作为一个整体添加
         */
        Node sublist = new Node(first);
        Node curr = sublist;
        for (int value : rest) {
            curr.next = new Node(value);
            curr = curr.next;
        }
        Node last = findLast();
        if (last == null) {
            head = sublist;
            return;
        }
        last.next = sublist;
    }

        //根据索引获取
    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        return node.value;
    }


    public static void main(String[] args) {
        Review05 review05 = new Review05();
        review05.addFirst(0);
        review05.addFirst(1);
        review05.addLast(2);
        review05.addLastMore(3,4,5,6,7,8,9);
        review05.loopWhile();
        System.out.println("----------------");
        review05.loopFor();
        System.out.println("----------------");
        review05.loop();
        System.out.println("----------------");
        System.out.println(review05.findLast());
        System.out.println(review05.get(0));
        review05.insert(9, 10);
        System.out.println(review05.head);
        review05.remove(9);
        System.out.println(review05.head);
    }


}
