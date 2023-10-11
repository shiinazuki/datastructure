package com.iori.datastructure.linkedlist;

/**
 * 删除有序链表重复元素
 */
public class E04Leetcode83 {


    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 1, 2, 3, 3);

        //System.out.println(diff1(head));
        //System.out.println(diff2(head));
        //System.out.println(diff3(head));
        System.out.println(diff4(head));

    }


    /**
     * 递归解法 视频中的解法
     *
     * @param head
     * @return
     */
    public static ListNode diff4(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }


        if (head.val == head.next.val) {
            return diff4(head.next);
        } else {
            head.next = diff4(head.next);
            return head;
        }

    }


    /**
     * 单指针
     *
     * @param head
     * @return
     */
    public static ListNode diff3(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 双指针去重  视频中的解法
     *
     * @param head
     * @return
     */
    public static ListNode diff2(ListNode head) {
        //节点数小于2时
        if (head == null || head.next == null) {
            return head;
        }
        //节点数大于等于2
        ListNode p = head;
        ListNode q;

        while ((q = p.next) != null) {
            if (p.val == q.val) {
                //删除 p2
                p.next = q.next;
            } else {
                //向后平移
                p = p.next;
            }
        }

        return head;

    }

    /**
     * 双指针去重  自己的想法写出来的
     *
     * @param head
     * @return
     */
    public static ListNode diff1(ListNode head) {
        //节点数小于2时
        if (head == null || head.next == null) {
            return head;
        }
        //节点数大于等于2
        ListNode p = head;
        ListNode q = head.next;

        while (q != null) {
            if (p.val != q.val) {
                p.next = q;
                p = p.next;
            }
            q = q.next;
        }
        //防止最后两个节点相同
        p.next = null;
        return head;

    }

}
