package com.iori.algorithm.leetcode;

import java.util.*;

/**
 * <h3>滑动窗口最大值 - 单调队列</h3>
 */
public class MostWaterLeetcode239 {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow0(nums, k)));
    }

    //单调队列
    // 每次向单调递减队列加入元素，队头元素即为滑动窗口最大值
    public static int[] maxSlidingWindow0(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            //检查队列头部元素 超过滑动窗口范围要移除
            if (i >= k && deque.peekFirst() == nums[i - k]) {
                deque.poll();
            }
                int num = nums[i];
            while (!deque.isEmpty() && deque.peekLast() < num) {
                deque.pollLast();
            }
            deque.offerLast(num);
            if (i >= k - 1) {
                list.add(deque.peekFirst());
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    //滑动窗口  解出来了 但是超时
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int left = 0;
        int right = left + k - 1;
        while (right < nums.length) {
            int max = Integer.MIN_VALUE;
            for (int i = left; i <= right; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                }
            }
            nums[left] = max;
            left++;
            right++;
        }
        int[] array = new int[left];
        System.arraycopy(nums, 0, array, 0, left);
        return array;

    }


}
