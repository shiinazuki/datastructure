package com.iori.review.review01;

import java.util.Iterator;

/**
 * 单向链表
 */
public class Review04 implements Iterable<Integer> {

    private Node head;

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


    //头部添加
    public void addFirst(int value) {
        this.head = new Node(value, this.head);
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

    //尾部添加

    public void addLast(int value) {
        Node last = findLast();
        if (last == null) {
            addFirst(value);
            return;
        }
        last.next = new Node(value);
    }

    //查询尾部节点
    private Node findLast() {
        if (this.head == null) {
            return null;
        }
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr;
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

    //获取指定索引处的节点
    private Node findNode (int index) {
        int i = 0;
        Node curr = head;
        while (curr != null) {
            if (i == index) {
                return curr;
            }
            curr  = curr.next;
            i++;
        }
        return null;
    }

    //插入
    public void insert(int index,int value) {
        Node node = findNode(index - 1);
        if (node == null) {
            head = new Node(value);
            return;
        }
        node.next = new Node(value, node.next);
    }

    //删除
    public void remove (int index) {
        if (index == 0) {
            if (head != null) {
                head = head.next;
                return;
            }else {
                throw new RuntimeException();
            }
        }
        Node node = findNode(index - 1);
        Node curr;
        if (node != null && (curr = node.next) != null) {
            node.next = curr.next;
        }else {
            throw  new RuntimeException();
        }
    }


    public static void main(String[] args) {
        Review04 review04 = new Review04();
        review04.addFirst(0);
        review04.addFirst(1);
        review04.addLast(2);
        review04.addLastMore(3,4,5,6,7,8,9);
        review04.loopWhile();
        System.out.println("----------------");
        review04.loopFor();
        System.out.println("----------------");
        review04.loop();
        System.out.println("----------------");
        System.out.println(review04.findLast());
        System.out.println(review04.get(0));
        review04.insert(9, 10);
        System.out.println(review04.head);
        review04.remove(9);
        System.out.println(review04.head);
    }

}
