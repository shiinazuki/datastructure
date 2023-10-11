package com.iori.datastructure.linkedlist;

/**
 * 合并多个有序链表
 */
public class E07Leetcode23 {


    public static void main(String[] args) {

        ListNode[] lists = {
                ListNode.of(1, 4, 5),
                ListNode.of(1, 3, 4),
                ListNode.of(2, 6),
        };

        System.out.println(mergeLists(lists));

    }

    /**
     * 对外提供这个方法 方便使用者使用
     * @param lists
     * @return
     */
    public static ListNode mergeLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    /**
     * 递归合并多项升序链表
     *
     * @param lists
     * @param i
     * @param j
     * @return
     */
    public static ListNode merge(ListNode[] lists, int i, int j) {
        if (i == j) {
            return lists[i];
        }

        int mid = (i + j) >>> 1;

        ListNode left = merge(lists, i, mid);
        ListNode right = merge(lists, mid + 1, j);
        return mergeTwo(left, right);

    }


    /**
     * 先写合并两个元素的
     */
    public static ListNode mergeTwo(ListNode list1, ListNode list2) {

        //先定义一个哨兵节点
        ListNode cur = new ListNode(-1, null);
        ListNode prev = cur;
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

        prev.next = list1 == null ? list2 : list1;

        return cur.next;

    }

}
