package com.iori.leetcode.leetcode02;

import com.iori.datastructure.linkedlist.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点
 */
public class Solution19 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5,
                                                null)))));


        System.out.println(removeNthFromEnd(head, 2));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        return null;
    }


}
