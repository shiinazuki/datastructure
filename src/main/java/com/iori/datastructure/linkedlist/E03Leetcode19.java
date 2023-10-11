package com.iori.datastructure.linkedlist;

/**
 * 删除链表的倒数第 N 个结点
 */
public class E03Leetcode19 {

    public static void main(String[] args) {
        //ListNode o5 = new ListNode(5, null);
        //ListNode o4 = new ListNode(4, o5);
        //ListNode o3 = new ListNode(3, o4);
        //ListNode o2 = new ListNode(2, o3);
        //ListNode o1 = new ListNode(1, o2);
        //System.out.println(remove2(o1, 2));

        //recursion(o1, 2);
        //System.out.println(remove3(o1, 5));
        //System.out.println(remove4(o1, 5));

    }




    /**
     * 快慢指针方法解题
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode remove4(ListNode head, int n) {
        //定义哨兵节点
        ListNode sen = new ListNode(-1, head);
        //分别赋给指针
        ListNode p1 = sen;
        ListNode p2 = sen;
        //将p2往后移动 n + 1次
        for (int i = 0; i <= n; i++) {
            p2 = p2.next;
        }
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return sen.next;

    }

    /**
     * 递归求解
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode remove3(ListNode head, int n) {
        ListNode sen = new ListNode(-1, head);
        recursion(sen, n);
        return sen.next;

    }


    /**
     * 递归寻找当前节点是倒数第几个
     *
     * @param head
     * @param n
     * @return
     */
    private static int recursion(ListNode head, int n) {
        if (head == null) {
            return 0;
        }

        //寻找下一个节点的倒数位置
        int nth = recursion(head.next, n);
        //System.out.println(head.val + " " + nth);
        if (nth == n) {
            head.next = head.next.next;
        }
        return nth + 1;
    }

    //递归过程代码
    /*
    private static int = 5 recursion1(ListNode head = 1, int n) {


        //寻找下一个节点的倒数位置
        int nth =  4
        private static int recursion1(ListNode head = 2, int n) {


            //寻找下一个节点的倒数位置
            int nth = 3
            private static int recursion1(ListNode head = 3, int n) {


                //寻找下一个节点的倒数位置
                int nth = 2;
                private static int recursion1(ListNode head = 4, int n) {


                    //寻找下一个节点的倒数位置
                    int nth = 1;
                    private static int recursion1(ListNode head =5, int n) {
                        //寻找下一个节点的倒数位置
                        int nth = 0;
                        private static int recursion1(ListNode head = null, int n) {

                            if (head == null) {
                                return 0;
                            }
                        }
                        return nth + 1;
                    }
                    return nth + 1;
                }

                if (nth == n) {
                    head.next = head.next.next;
                }
                return nth + 1;
            }

            return nth + 1;
        }

        return nth + 1;
    }
    */


    /**
     * 看的力扣题解
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode remove2(ListNode head, int n) {
        //求出节点个数
        int length = nodeSum(head);
        ListNode node = new ListNode(0, head);
        ListNode cur = node;
        for (int i = 1; i < length - n + 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return  node.next;
    }


    /**
     * 结合学习 没看题解自己做出来的
     * 只适合部分场景 有很多场景照顾不到
     * 很垃圾!!!
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode remove1(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        //先算出节点总个数
        int i = nodeSum(head);

        if (i == 1 && n == 1) {
            return head = head.next;
        }
        //算出需要移除节点的位置
        int index = i - n - 1;
        int j = 0;
        ListNode prev = head;
        ListNode cur = head.next;
        while (j != index) {
            prev = cur;
            cur = cur.next;
            j++;
        }
        prev.next = cur.next;
        return head;


    }


    //先求出节点的总个数
    public static int nodeSum(ListNode head) {
        int i = 0;
        while (head != null) {
            head = head.next;
            i++;
        }
        return i;
    }


}
