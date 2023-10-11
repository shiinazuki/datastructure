package com.iori.datastructure.linkedlist;

import java.util.Arrays;

/**
 * 检测链表环的入口
 */
public class E011Leetcode142 {


    public static void main(String[] args) {

        // 构造一个带环链表
        ListNode n12 = new ListNode(12, null);
        ListNode n11 = new ListNode(11, n12);
        ListNode n10 = new ListNode(10, n11);
        ListNode n9 = new ListNode(9, n10);
        ListNode n8 = new ListNode(8, n9);
        ListNode n7 = new ListNode(7, n8);
        ListNode n6 = new ListNode(6, n7);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
        n12.next = n1;

        System.out.println(search1(n1).val);

    }


    /**
     * 检测环的入口  视频解法
     * @param head
     * @return
     */
    public static ListNode search1(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                //进入第二阶段
                slow = head;

                while (true) {
                    if (slow == fast) {
                        return slow;
                    }
                    slow = slow.next;
                    fast = fast.next;

                }
            }
        }
        return null;
    }



    /**
     * 检测环的入口  自己的想法写的
     * @param head
     * @return
     */
    public static ListNode search(ListNode head) {


        //先拿到环的节点
        ListNode cur = head;
        ListNode pr = head;
        ListNode cycle = hasCycle(pr);
        //先判断有没有环
        if (cycle == null) {
            return null;
        }
        //如果只有两个节点构成环形链表
        if (head  == head.next.next) {
            return head;
        }
        while (cur != null && cycle != null) {
            //用来判断只有两个节点形成环

            cur = cur.next;
            cycle = cycle.next;
            if (cur == cycle) {
                return cur;
            }
        }
        return null;
    }


    /**
     * 判断链表是否有环  看了思想后 自己先尝试写写
     * 使用快慢指针
     * @param head
     * @return
     */
    public static ListNode hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return fast;
            }
        }
        return null;
    }

}
