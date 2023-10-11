package com.iori.datastructure.linkedlist;

/**
 * 根据值删除节点
 */
public class E02Leetcode203 {

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 6, 3, 6);
        //ListNode head = ListNode.of(7, 7, 7, 7);
        //ListNode listNode = removeElements1(head, 7);
        //ListNode listNode = removeElements2(head, 7);
        ListNode listNode = removeElements3(head, 6);
        System.out.println(listNode);
    }

    /**
     * 根据值删除节点 方法三  递归
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements3(ListNode head, int val) {

        if (head == null) {
            return null;
        }

        head.next = removeElements3(head.next, val);

        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }


    }


    /**
     * 根据值删除节点 方法二 不带哨兵
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements2(ListNode head, int val) {

        //先循环删除头结点
        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return head;
        }


        ListNode tmp = head;

        while (tmp.next != null) {
            if (tmp.next.val == val) {
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
        return head;
    }

    /**
     * 根据值删除节点 方法一 带哨兵
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements1(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode prev = new ListNode(-1, head);
        ListNode tmp = prev;

        while (tmp.next != null) {
            //需要删除的
            if (tmp.next.val == val) {
                //删除操作
                tmp.next = tmp.next.next;
            } else {
                //向后移动
                tmp = tmp.next;
            }
        }
        return prev.next;
    }


}
