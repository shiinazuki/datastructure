package com.iori.leetcode.leetcode09;

/**
 *
 */
public class Solution707 {

    //节点类
    static class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    int size = 0;

    public Solution707() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }

    //根据 index 获取对应的值 如果下标无效 返回-1
    public int get(int index) {
        Node node = findBuIndex(index);
        return node == null ? -1 : node.val;

    }

    //将一个值为 val 的节点插入到链表中第一个元素之前。
    // 在插入完成后，新节点会成为链表的第一个节点。
    public void addAtHead(int val) {
        Node oldNode = head.next;
        Node node = new Node(val, head, oldNode);
        oldNode.prev = node;
        head.next = node;
        size++;
    }

    //将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
    public void addAtTail(int val) {
        Node oldLast = tail.prev;
        Node node = new Node(val, oldLast, tail);
        oldLast.next = node;
        tail.prev = node;
        size++;
    }

    /*
       将一个值为 val 的节点插入到链表中下标为 index 的节点之前。
       如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。
       如果 index 比长度更大，该节点将 不会插入 到链表中。
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index <= 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            Node oldNode = findBuIndex(index);
            Node newNode = new Node(val, oldNode.prev, oldNode);
            oldNode.prev.next = newNode;
            oldNode.prev = newNode;
            size++;
    }

}

    // 如果下标有效，则删除链表中下标为 index 的节点。
    public void deleteAtIndex(int index) {
        Node node = findBuIndex(index);
        if (node == null) {
            return;
        }
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }

    public Node findBuIndex(int index) {
        boolean isLeft = index < size / 2;
        if (!isLeft) {
            index = size - index - 1;
        }
        for (Node cur = isLeft ? head.next : tail.prev; cur != tail && cur != head; cur = isLeft ? cur.next : cur.prev) {
            if (index-- == 0) {
                return cur;
            }
        }
        return null;
    }


}
