package com.iori.datastructure.linkedlist;

/**
 * 删除有序链表重复元素
 */
public class E05Leetcode82 {


    public static void main(String[] args) {
        //ListNode head = ListNode.of(1, 2, 3, 3, 4, 4, 5);
        //ListNode head = ListNode.of(1, 1, 1, 2, 3);
        ListNode head = ListNode.of(1, 1, 1, 1, 1);

        //System.out.println(diff1(head));
        System.out.println(diff2(head));
        //System.out.println(diff3(head));
        //System.out.println(diff4(head));


    }

    /**
     * 易懂的解法
     * @param head
     * @return
     */
    public static ListNode diff4(ListNode head) {
        if (head == null) {
            return null;
        }
        // 只要涉及头结点的操作，设置虚拟头结点避免对链表第 1 个结点的分类讨论
        ListNode dummyNode = new ListNode(-1,head);
        ListNode curNode = dummyNode;
        while (curNode.next != null && curNode.next.next != null) {
            // 如果接连两个结点的 val 相等，至少要把它们都删掉
            if (curNode.next.val == curNode.next.next.val) {
                // 要删除的起点至少应该是当前判断相同的结点的第 2 个
                ListNode delNode = curNode.next.next;
                // 如果后面还有相同的结点，delNode 后移一位，即 delNode 应该是指向相同的结点的最后一个
                while (delNode.next != null && delNode.next.val == delNode.val) {
                    delNode = delNode.next;
                }
                curNode.next = delNode.next;
                delNode.next = null;
            } else {
                curNode = curNode.next;
            }
        }
        return dummyNode.next;
    }


    /**
     * 使用三个个指针进行删除重复元素
     * 看视频的解法
     *
     * @param head
     * @return
     */
    public static ListNode diff3(ListNode head) {

        //先判断链表是否为null
        if (head == null || head.next == null) {
            return head;
        }
        //哨兵节点
        ListNode sen = new ListNode(-1, head);
        //用来指向cur的上一个节点
        ListNode p1 = sen;
        ListNode p2 = null;
        ListNode p3 = null;

        while ((p2 = p1.next) != null && (p3 = p2.next) != null) {
            if (p2.val == p3.val) {
                //将上一个节点赋值给prev 用来指向不重复的元素
               while ((p3 = p3.next) != null && p3.val == p2.val) {
               }
                //p3找到了不重复的值
                p1.next = p3;
            } else {
                p1 = p1.next;
            }
        }
        return sen.next;

    }


    /**
     * 递归解法
     * 看的视频 写的 自己根本写不出来
     *
     * @param head
     * @return
     */
    public static ListNode diff2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }


        if (head.val == head.next.val) {
            ListNode prev = head.next.next;
            while (prev != null && prev.val == head.val) {
                prev = prev.next;
            }
            //就是与p取值不同的节点
            return diff2(prev);
        } else {
            head.next = diff2(head.next);
            return head;
        }

    }


    /**
     * 自己想法办法  只能应对专门的题 不通用
     * 很垃圾！！！！
     * 使用三个指针进行删除重复元素
     */
    public static ListNode diff1(ListNode head) {

        //先判断链表是否为null
        if (head == null || head.next == null) {
            return head;
        }

        ListNode sen = new ListNode(-1, head);
        ListNode cur = sen.next;
        ListNode prev;
        //用来指向cur的上一个节点
        ListNode n1 = sen;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                //将上一个节点赋值给prev 用来指向不重复的元素
                prev = n1;
                prev.next = cur.next.next;
                //cur = cur.next;
                cur = cur.next.next;
            } else {
                n1 = n1.next;
                cur = cur.next;
            }
        }
        return sen.next;

    }


}
