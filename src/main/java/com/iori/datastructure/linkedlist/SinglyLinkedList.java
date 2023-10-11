package com.iori.datastructure.linkedlist;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 单向链表
 * 链表整体
 */
public class SinglyLinkedList implements Iterable<Integer> {

    private Node head; // 头指针


    /**
     * 节点类
     */
    private static class Node {
        int value; //值
        Node next; //下一个节点指针

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 每次都往头节点添加元素
     *
     * @param value
     */
    public void addFirst(int value) {
        //1.链表为空
        //    head = new Node(value, null);
        //2.链表非空
        head = new Node(value, head);
    }

    /**
     * 遍历链表
     */
    public void loop1(Consumer<Integer> consumer) {
        Node point = head;
        while (point != null) {
            consumer.accept(point.value);
            point = point.next;
        }
    }

    /**
     * 使用for循环遍历
     *
     * @param consumer
     */
    public void loop2(Consumer<Integer> consumer) {
        for (Node point = head; point != null; point = point.next) {
            consumer.accept(point.value);
        }
    }

    /**
     * 递归遍历
     */
    public void loop3(Consumer<Integer> before,Consumer<Integer> after) {
        recursion(head,before,after);
    }

    private void recursion(Node curr, Consumer<Integer> before, Consumer<Integer> after) { //某个节点要进行的操作
        if (curr == null) {
            return;
        }

        before.accept(curr.value);
        recursion(curr.next, before, after);
        after.accept(curr.value);

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
            SinglyLinkedList.Node point = head;

            @Override
            public boolean hasNext() { //是否有下一个元素
                return point != null;
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

        Node last = findLast();
        if (last == null) {
            addFirst(value);
            return;
        }
        last.next = new Node(value, null);
    }

    /**
     * 往链表最后插入一个节点
     *
     * @return
     */
    private Node findLast() {
        //1.链表为空
        if (head == null) {
            return null;
        }
        //定义一个指针
        Node point = head;

        //2.链表非空
        while (point.next != null) {
            point = point.next;
        }
        return point;
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
    private Node findNode(int index) {

        int i = 0;
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        //没找到
        return null;
    }

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
        //索引小于等于0 特殊处理
        if (index <= 0) {
            addFirst(value);
            return;
        }
        //找到上一个节点
        Node prev = findNode(index - 1);
        //找不到
        if (prev == null) {
            throw getIllegalArgumentException(index);
        }
        prev.next = new Node(value, prev.next);
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
        if (head == null) {
            throw getIllegalArgumentException(0);
        }
        head = head.next;
    }

    /**
     * 按索引删除
     *
     * @param index
     */
    public void remove(int index) {
        if (index == 0) {
            removeFirst();
            return;
        }
        //找到上一个节点
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw getIllegalArgumentException(index);
        }
        //被删除的节点
        Node removed = prev.next;
        if (removed == null) {
            throw getIllegalArgumentException(index);
        }
        prev.next = removed.next;


    }

}



