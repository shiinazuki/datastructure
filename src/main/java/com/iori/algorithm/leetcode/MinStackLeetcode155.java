package com.iori.algorithm.leetcode;


import java.util.LinkedList;

/**
 * <h3>最小栈</h3>
 */
public class MinStackLeetcode155 {

    public static void main(String[] args) {
        MinStack stack2 = new MinStack();
        stack2.push(-2);
        stack2.push(0);
        stack2.push(-3);
        System.out.println(stack2.getMin()); // -3
        stack2.pop();
        System.out.println(stack2.top()); // 0
        System.out.println(stack2.getMin()); // -2
    }


    static class MinStack {

        LinkedList<Integer> stack = new LinkedList<>();
        LinkedList<Integer> min = new LinkedList<>();

        public MinStack() {
            min.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.push(val);
            min.push(Math.min(min.peek(), val));
        }

        public void pop() {
            if (stack.isEmpty()) {
                return;
            }
            stack.pop();
            min.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min.peek();
        }


    }


    record Data(int val, int min) {

    }

    static class MinStack2 {

        LinkedList<Data> stack = new LinkedList<>();


        public MinStack2() {

        }

        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(new Data(val, val));
            } else {
                stack.push(new Data(val, Math.min(stack.peek().min, val)));
            }
        }

        public void pop() {
            if (stack.isEmpty()) {
                return;
            }
            stack.pop();
        }

        public int top() {
            return stack.peek().val;
        }

        public int getMin() {
            return stack.peek().min;
        }


    }


}
