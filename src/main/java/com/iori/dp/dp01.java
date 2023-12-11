package com.iori.dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 熟悉一下递归
 */
public class dp01 {

    public static void main(String[] args) {
        int n = 3;
        move(n, 'a', 'b', 'c');
        System.out.println("----------------------");
        String s = "abc";
        char[] chars = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        //printStr(chars, 0, ans, path);
        System.out.println(ans);
        System.out.println("----------------------");

        LinkedList<String> stack = new LinkedList<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        stack.push("f1");

        //不申请额外空间 反转栈里的元素
        //reverse(stack);
        System.out.println("栈反转后为 " + stack);
        System.out.println("----------------------");
        LinkedList<String> queue = new LinkedList<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        queue.offer("f1");

        //不申请额外空间 反转队列里的元素
        queueReverse(queue);
        System.out.println("队列反转后为 " + queue);
    }


    //不申请额外空间 反转队列元素
    public static void queueReverse(LinkedList<String> queue) {
        if (queue.isEmpty()) {
            return;
        }
        String poll = queue.poll(); // 使用poll()方法取出队首元素
        queueReverse(queue);
        queue.offer(poll); // 使用offer()方法将取出的元素加入队尾
    }




    //汉诺塔
    public static void move(int n, char a, char b, char c) {
        if (n == 1) {
            System.out.println("from " + a + " to " + c);
            return;
        }
        move(n - 1, a, c, b);
        System.out.println("from " + a + " to " + c);
        move(n - 1, b, a, c);
    }

}
