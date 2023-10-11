package com.iori.datastructure.linkedlist;


/**
 * 反转链表
 */
public class E01Leetcode206 {

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        //ListNode n1 = new E01Leetcode206().reverseList1(o1);
        //ListNode n1 = new E01Leetcode206().reverseList2(o1);
        //ListNode n1 = new E01Leetcode206().reverseList3(o1);
        ListNode n1 = new E01Leetcode206().reverseList4(o1);
        //ListNode n1 = new E01Leetcode206().reverseList5(o1);
        System.out.println(n1);
    }




    /**
     * 反转链表 方法5 双指针法
     * @param head
     * @return
     */
    public ListNode reverseList5(ListNode head) {
        //1 空链表 2.链表中只有一个元素
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode tmp = null;
        while (head != null) {
            tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;

    }

    /**
     * 反转链表 方法4 双指针法 提出第二个以及后面的
     * @param head
     * @return
     */
    public ListNode reverseList4(ListNode head) {
        //1 空链表 2.链表中只有一个元素
        if (head == null || head.next == null) {
            return head;
        }

        ListNode n1 = head;
        //拿到后一个节点
        ListNode o2 = head.next;
        while (o2 != null) {
            //将第一个节点指向第三个节点
            head.next = o2.next;
            System.out.println(n1);
            //将提出来的节点放到头部
            o2.next = n1;
            //重新给n1赋值
            n1 = o2;
            //o2往后移动
            o2 = head.next;

        }
        return n1;

    }

    /**
     * 反转链表 方法3 递归 先找最后一个
     *
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = reverseList3(head.next);
        //此时 head是 第4个
        head.next.next = head;
        head.next = null;

        return cur;

    }


    /**
     * 反转链表 方法2 删除头节点 添加头节点
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        List list1 = new List(head);
        List list2 = new List(null);
        while (true) {
            ListNode first = list1.removeFirst();
            if (first == null) {
                break;
            }
            list2.addFirst(first);
        }
        return list2.head;
    }

    static class List {
        ListNode head;

        public List(ListNode head) {
            this.head = head;
        }

        /**
         * 添加头节点
         *
         * @param first
         */
        public void addFirst(ListNode first) {
            first.next = head;
            head = first;

        }

        /**
         * 移除头节点
         *
         * @return
         */
        public ListNode removeFirst() {
            ListNode cur = head;
            if (cur != null) {
                head = cur.next;
            }
            return cur;

        }

    }

    /**
     * 反转链表 方法1  创建新节点
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        ListNode n1 = null;
        ListNode point = head;
        while (point != null) {
            n1 = new ListNode(point.val, n1);
            point = point.next;
        }
        return n1;
    }




}
