package com.iori.datastructure.linkedlist;

/**
 * 合并两个有序链表
 */
public class E06Leetcode21 {


    public static void main(String[] args) {

        ListNode list1 = ListNode.of(1, 2, 4);
        ListNode list2 = ListNode.of(1, 3, 4);
        //System.out.println(merge2(list1, list2));
        System.out.println(merge3(list1, list2));

    }


    /**
     * 递归实现
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode merge3(ListNode list1, ListNode list2) {

        //if (list1 == null) {
        //    return list2;
        //}else if (list2 == null) {
        //    return list1;
        //} else if (list1.val <= list2.val) {
        //    list1.next = merge3(list1.next, list2);
        //    return list1;
        //}else {
        //    list2.next = merge3(list1, list2.next);
        //    return list2;
        //}

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = merge3(list1.next, list2);
            return list1;
        }else {
            list2.next = merge3(list1, list2.next);
            return list2;
        }

    }


    /**
     * 看了题解的思想来写的
     * 自己根本不会!!!
     */
    public static ListNode merge2(ListNode list1, ListNode list2) {
        //先定义一个哨兵节点
        ListNode sen = new ListNode(-1);
        ListNode prev = sen;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = list1 == null ? list2 : list1;

        return sen.next;

    }

    /**
     * 自己尝试写写  合并两个有序链表
     * 没写出来
     */
    public static ListNode merge1(ListNode list1, ListNode list2) {
        //如果两条链表都是null  直接返回其中一个即可
        if (list1 == null && list2 == null) {
            return list1;
        }

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode cur1 = list1;
        ListNode tmp = list1.next;
        ListNode p1 = null;
        while ((p1 = returnNode(list2)) != null) {

            if (cur1.val <= p1.val) {
                p1.next = tmp;
                cur1.next = p1;
                cur1 = tmp;
                tmp = tmp.next;

            }

            list2 = list2.next;
        }

        return list1;


    }

    /**
     * 定义一个方法 删除头节点并返回
     */
    public static ListNode returnNode(ListNode list) {
        ListNode n1 = list;
        n1.next = null;
        list = list.next;
        return n1;
    }

}
