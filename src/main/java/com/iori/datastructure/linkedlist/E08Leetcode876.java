package com.iori.datastructure.linkedlist;

/**
 * 查找链表的中间节点
 */
public class E08Leetcode876 {


    public static void main(String[] args) {

        ListNode list = ListNode.of(1,2,3,4,5);
        //ListNode list = ListNode.of(1,2,3,4,5,6);
        //System.out.println(searchMid(list));
        System.out.println(searchMid1(list));

    }


    /**
     * 看题解的快慢指针法
     * @param head
     * @return
     */
    public static ListNode searchMid1(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }




    /**
     * 查询中间元素
     * 下面的代码都是自己想的  还算正确
     * 想法是先求出链表元素的总个数  然后拿到中间元素 最后循环进行比较
     * 效率很低了
     */
    public static ListNode searchMid(ListNode head) {
        if (head == null && head.next == null) {
            return head;
        }
        //先拿到总个数
        int sum = linkedSum(head);
        int mid = sum >>> 1;

        int num = 0;
        while(head != null) {
            if (num == mid) {
                break;
            }
            head = head.next;
            ++num;
        }
        return head;
    }

    /**
     * 先求出链表元素的总个数
     */
    public static int linkedSum(ListNode head) {

        ListNode headSum = head;
        int sum = 0;
        while (headSum != null) {
            headSum = headSum.next;
            sum++;
        }
        return sum;

    }

}
