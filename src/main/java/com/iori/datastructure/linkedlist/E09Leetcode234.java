package com.iori.datastructure.linkedlist;

/**
 * 判断回文链表
 */
public class E09Leetcode234 {


    public static void main(String[] args) {

        //ListNode list = ListNode.of(1,2,2,1);
        //ListNode list = ListNode.of(1,2,4,3,2,1);
        ListNode list = ListNode.of(1,0,1);
        //System.out.println(isPalin(list));
        //System.out.println(isPalin1(list));
        System.out.println(isPalin2(list));

    }


    /**
     * 视频讲解的方法 和我想的想法一样 后续进行了优化
     * 这里写视频中优化后的代码
     * 找中间点 并直接反转前半个链表 这样只需要一个循环就做了两件事情
     * 然后将反转后的前半个链表 与中间点开始的后半个链表逐一比较
     * @param head
     * @return
     */
    public static boolean isPalin2(ListNode head) {
        //先判断链表是否为null
        if (head == null && head.next == null) {
            return false;
        }
        //拿到中间的节点
        ListNode slow = head,fast = head;

        //将中间节点反转
        ListNode prev = null;
        ListNode cur = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //反转链表
            cur.next = prev;
            prev = cur;
            cur = slow;
        }
        //System.out.println(prev);
        //System.out.println(slow);
        //如果是奇数节点 会出问题
        //奇数节点的退出条件是 fast的下一个为null  fast不为null
        if (fast != null) {
            slow = slow.next;
        }

        //开始对比
        //然后将反转后的前半个链表 与中间点开始的后半个链表逐一比较
        boolean flag = true;
        while (prev != null) {
            if (prev.val != slow.val) {
                flag = false;
            }
            prev = prev.next;
            slow = slow.next;
        }
        return flag;
    }


    /**
     * 看了题解的思路
     * 先将链表中的元素复制到数组中 然后使用双指针来进行比较
     */
    public static boolean isPalin1(ListNode head) {
        if (head == null && head.next == null) {
            return false;
        }

        int sum = 0;
        ListNode sumNode = head;
        while (sumNode != null) {
            sumNode = sumNode.next;
            sum++;
        }
        int[] array = new int[sum];
        int m = 0;
        while (head != null) {
            array[m++] = head.val;
            head = head.next;
        }
        int i = 0,j = array.length - 1;

        boolean flag = true;
        while (i <= j) {
            if (array[i++] != array[j--]) {
                flag = false;
            }
        }
        return flag;


    }

    /**
     * 以下是自己的想法 解出来的题 效率有点低
     * 开始对比链表
     */
    public static boolean isPalin(ListNode head) {
        //先判断链表是否为null
        if (head == null && head.next == null) {
            return false;
        }
        //拿到中间的节点
        ListNode midNode = searchMid(head);
        //将中间节点反转
        ListNode reMidNode = reverse(midNode);
        //开始对比
        boolean flag = true;
        while (reMidNode != null) {
            if (head.val != reMidNode.val) {
                flag = false;
            }
            reMidNode = reMidNode.next;
            head = head.next;
        }
        return flag;


    }

    /**
     * 将中间的链表反转
     */
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }


    /**
     * 先找到中间的节点
     * 使用快慢指针法
     */
    public static ListNode searchMid(ListNode head) {

        ListNode slow = head,fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;

    }




}
