package com.iori.datastructure.priorityqueue;

import com.iori.datastructure.linkedlist.ListNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 合并多个有序链表
 */
public class E01Leetcode23 {

    public static void main(String[] args) {
        ListNode[] lists = {
                ListNode.of(1, 4, 5),
                ListNode.of(1, 3, 4),
                ListNode.of(2, 6),
                null,
        };
        ListNode m = new E01Leetcode23().mergeKLists(lists);
        System.out.println(m);



    }

    /**
     * 使用小顶堆实现
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        MinHeap heap = new MinHeap(lists.length);
        //将链表的头结点加入到小顶堆
        for (ListNode list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }
        //不断从堆顶移除最小元素 加入新链表
        ListNode sen = new ListNode(-1, null);
        ListNode cur = sen;
        while (!heap.isEmpty()) {
            ListNode min = heap.poll();
            cur.next = min;
            cur = min;
            if (min.next != null) {
                heap.offer(min.next);
            }
        }
        return sen.next;

    }
}