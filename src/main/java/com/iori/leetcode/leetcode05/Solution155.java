package com.iori.leetcode.leetcode05;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 155. 最小栈
 */
public class Solution155 {


    static class MinStack {
        int val; //存储原本值
        int min; // 存储最小值

        public MinStack() {
        }

        public MinStack(int val, int min) {
            this.val = val;
            this.min = min;
        }

        @Override
        public String toString() {
            return "MinStack{" +
                    "val=" + val +
                    ", min=" + min +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MinStack minStack = (MinStack) o;
            return val == minStack.val && min == minStack.min;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, min);
        }
    }

    LinkedList<MinStack> stack = new LinkedList<>();


    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new MinStack(val, val));
        } else {
            stack.push(new MinStack(val, Math.min(stack.peek().min, val)));
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
