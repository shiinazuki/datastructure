package com.iori.algorithm.leetcode;

import java.util.LinkedList;

/**
 * <h3>单调递减队列</h3>
 */
public class MonotonicQueue {

    private LinkedList<Integer> deque = new LinkedList<>();

    public Integer peek() {
        return deque.peekFirst();
    }

    public void poll() {
        deque.pollFirst();
    }


    public void offer(Integer t) {
        while (!deque.isEmpty() && deque.peekLast() < t) {
            deque.pollLast();
        }
        deque.offerLast(t);
    }

    @Override
    public String toString() {
        return deque.toString();
    }

    public static void main(String[] args) {
        MonotonicQueue q = new MonotonicQueue();
        for (int i : new int[]{1, 3, -1, -3, 5, 3, 6, 7}) {
            q.offer(i);
            System.out.println(q);
        }
    }

}
